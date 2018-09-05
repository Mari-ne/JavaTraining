package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import entity.User;
import pool.ConnectionPool;
import pool.ConnectionPoolException;

public class UserDAO extends AbstractDAO<String, User> {

	private static final String SQL_SELECT_ALL_USER = "select * from user";
	private static final String SQL_SELECT_BY_LOGIN = "select * from user where login = ?";
	private static final String SQL_SELECT_BY_ROLE = "select * from user where role = ?";
	private static final Logger LOGGER = Logger.getRootLogger();
	
	@Override
	public List<User> findAll() {
		List<User> users = new ArrayList<>();
		try(Connection con = ConnectionPool.getInstance().takeConnection()){
			PreparedStatement stat = con.prepareStatement(SQL_SELECT_ALL_USER);
			ResultSet result = stat.executeQuery();
			while(result.next()) {
				User user = new User();
				user.setLogin(result.getString("login"));
				user.setPassword(result.getString("password"));
				user.setE_mail(result.getString("e-mail"));
				user.setRole(result.getString("role"));
				users.add(user);
			}
		}catch(ConnectionPoolException e) {
			LOGGER.error(e.getMessage());
		}catch(SQLException e) {
			LOGGER.error(e.getMessage());
		}
		return users;
	}

	@Override
	public User findByID(String id) {
		User user = new User();
		try(Connection con = ConnectionPool.getInstance().takeConnection()){
			PreparedStatement stat = con.prepareStatement(SQL_SELECT_BY_LOGIN);
			stat.setString(1, id);
			ResultSet result = stat.executeQuery();
			while(result.next()) {
				user.setLogin(result.getString("login"));
				user.setPassword(result.getString("password"));
				user.setE_mail(result.getString("e-mail"));
				user.setRole(result.getString("role"));
			}
		}catch(ConnectionPoolException e) {
			LOGGER.error(e.getMessage());
		}catch(SQLException e) {
			LOGGER.error(e.getMessage());
		}
		return user;
	}
	
	public List<User> findByRole(String role){
		List<User> users = new ArrayList<>();
		try(Connection con = ConnectionPool.getInstance().takeConnection()){
			PreparedStatement stat = con.prepareStatement(SQL_SELECT_BY_ROLE);
			stat.setString(1, role);
			ResultSet result = stat.executeQuery();
			while(result.next()) {
				User user = new User();
				user.setLogin(result.getString("login"));
				user.setPassword(result.getString("password"));
				user.setE_mail(result.getString("e-mail"));
				user.setRole(result.getString("role"));
				users.add(user);
			}
		}catch(ConnectionPoolException e) {
			LOGGER.error(e.getMessage());
		}catch(SQLException e) {
			LOGGER.error(e.getMessage());
		}
		return users;
	}

	@Override
	public boolean delete(String id) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean delete(User entity) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean create(User entity) {
		throw new UnsupportedOperationException();
	}

	@Override
	public User update(User entity) {
		throw new UnsupportedOperationException();
	}

}
