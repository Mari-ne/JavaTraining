package pool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
//import java.util.Enumeration;
import java.util.concurrent.LinkedBlockingQueue;

import com.mysql.jdbc.Driver;

import pool.db.DBParameter;
import pool.db.DBResourceManager;

import org.apache.log4j.Logger;

public class ConnectionPool {
	
	private static ConnectionPool instance;
	
	LinkedBlockingQueue<Connection> connections;
	
	private String url;
	private String user;
	private String password;
	private int maxPoolSize;
	
	private static int counter; //count, how many connections is busy right now
	
	private static final Logger LOGGER = Logger.getRootLogger();
	
	private ConnectionPool() {
		DBResourceManager manager = DBResourceManager.getInstance();
		url = manager.getValue(DBParameter.DB_URL.getValue());
		user = manager.getValue(DBParameter.DB_USER.getValue());
		password = manager.getValue(DBParameter.DB_PASSWORD.getValue());
		try {
			maxPoolSize = Integer.parseInt(manager.getValue(DBParameter.DB_POOLSIZE.getValue()));
		} catch(NumberFormatException e) {
			LOGGER.error(e.getMessage());
			maxPoolSize = 10;
		}
	}
	
	private void initialization() throws ConnectionPoolException {
		connections = new LinkedBlockingQueue<>(maxPoolSize);
		try {
			DriverManager.registerDriver(new Driver());
			for(int i = 0; i < 3; i ++) {
				try {
					ProxiConnection connect = new ProxiConnection(DriverManager.getConnection(url, user, password));
					connections.add(connect);
				} catch(SQLException e1) {
					LOGGER.error(e1.getMessage());
				}
			}
		} catch (SQLException e) {
			LOGGER.error(e.getMessage());
			throw new ConnectionPoolException("SQL exception in Connection Pool", e);
		}
	}
	
	public static ConnectionPool getInstance() {
		if(instance == null) {
			instance = new ConnectionPool();
			try {
				instance.initialization();
			} catch (ConnectionPoolException e) {
				LOGGER.error(e.getMessage());
			}
		}
		return instance;
	}
	
	public Connection takeConnection() throws ConnectionPoolException {
		Connection con = null;
		try {
			con = connections.take();
		} catch(InterruptedException e) {
			if(connections.isEmpty() && counter != 10) {
				//add new connection
				try {
					con = new ProxiConnection(DriverManager.getConnection(url, user, password));
					LOGGER.info("---" + con.getClass().getName() + "\n");
				} catch (SQLException e1) {
					LOGGER.error(e1.getMessage());
					throw new ConnectionPoolException("SQL exception in Connectoin Pool", e1);
				}
			}
			else {
				LOGGER.error(e.getMessage());
				throw new ConnectionPoolException("Can't connect to the data base", e);
			}
		}
		counter ++;
		return con;
	}
	
	public void closeConnection(Connection con) {
		try {
			con.close();
			counter --;
		} catch(SQLException e) {
			LOGGER.error(e.getMessage());
		}
	}
	
	public void closePool() throws SQLException {
		Connection con;
		while((con = connections.poll()) != null) {
			((ProxiConnection) con).release();
		}
		instance = null;
	}
}
