package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import entity.PersonalResult;
import pool.ConnectionPool;
import pool.ConnectionPoolException;

public class PersonalResultDAO extends AbstractDAO<String, PersonalResult> {

	private static final String SQL_SELECT_ALL_PERSONAL_RESULT = "select * from personal_result";
	private static final String SQL_SELECT_BY_LOGIN = "select * from personal_result where user_login = ?";
	//private static final String SQL_SELECT_BY_
	private static final Logger LOGGER = Logger.getRootLogger();
	
	@Override
	public List<PersonalResult> findAll() {
		List<PersonalResult> results = new ArrayList<>();
		try(Connection con = ConnectionPool.getInstance().takeConnection()){
			PreparedStatement stat = con.prepareStatement(SQL_SELECT_ALL_PERSONAL_RESULT);
			ResultSet res = stat.executeQuery();
			while(res.next()) {
				PersonalResult result = new PersonalResult();
				result.setUserLogin(res.getString("user_login"));
				result.setLastBet(res.getDouble("las_bet"));
				result.setLastGain(res.getDouble("last_gain"));
				result.setAllBet(res.getDouble("all_bet"));
				result.setAllGain(res.getDouble("all_gain"));
				results.add(result);
			}
		}catch(ConnectionPoolException e) {
			LOGGER.error(e.getLocalizedMessage());
		}catch(SQLException e) {
			LOGGER.error(e.getMessage());
		}
		return results;
	}

	@Override
	public PersonalResult findByID(String id) {
		PersonalResult result = new PersonalResult();
		try(Connection con = ConnectionPool.getInstance().takeConnection()){
			PreparedStatement stat = con.prepareStatement(SQL_SELECT_BY_LOGIN);
			stat.setString(1, id);
			ResultSet res = stat.executeQuery();
			while(res.next()) {
				result.setUserLogin(res.getString("user_login"));
				result.setLastBet(res.getDouble("las_bet"));
				result.setLastGain(res.getDouble("last_gain"));
				result.setAllBet(res.getDouble("all_bet"));
				result.setAllGain(res.getDouble("all_gain"));
			}
		}catch(ConnectionPoolException e) {
			LOGGER.error(e.getLocalizedMessage());
		}catch(SQLException e) {
			LOGGER.error(e.getMessage());
		}
		return result;
	}

	@Override
	public boolean delete(String id) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean delete(PersonalResult entity) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean create(PersonalResult entity) {
		throw new UnsupportedOperationException();
	}

	@Override
	public PersonalResult update(PersonalResult entity) {
		throw new UnsupportedOperationException();
	}

}
