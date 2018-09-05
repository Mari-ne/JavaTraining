package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import pool.ConnectionPool;
import pool.ConnectionPoolException;

import entity.Forecast;
import entity.Key;

public class ForecastDAO extends AbstractDAO<Key<String, Integer>, Forecast> {

	private static final String SQL_SELECT_ALL = "select * from competition_m2m_user";
	private static final String SQL_SELECT_BY_ID = "select * from competition_m2m_user where user_login = ? and competition_id = ?";
	private static final String SQL_SELECT_BY_LOGIN = "select * from competition_m2m_user where user_login = ?";
	private static final String SQL_SELECT_BY_COMPETITION_ID = "select * from competition_m2m_user where competition_id = ?";
	private static final Logger LOGGER = Logger.getRootLogger();
	
	@Override
	public List<Forecast> findAll() {
		List<Forecast> forecasts = new ArrayList<>();
		try(Connection con = ConnectionPool.getInstance().takeConnection()){
			PreparedStatement stat = con.prepareStatement(SQL_SELECT_ALL);
			ResultSet result = stat.executeQuery();
			while(result.next()) {
				Forecast forecast = new Forecast();
				forecast.setUserLogin(result.getString("user_login"));
				forecast.setCompetitionID(result.getInt("competition_id"));
				forecast.setResult(result.getString("result").charAt(0));
				forecasts.add(forecast);
			}
		} catch(ConnectionPoolException e) {
			LOGGER.error(e.getMessage());
		} catch(SQLException e) {
			LOGGER.error(e.getMessage());
		}
		return forecasts;
	}

	@Override
	public Forecast findByID(Key<String, Integer> id) {
		Forecast forecast = new Forecast();
		try(Connection con = ConnectionPool.getInstance().takeConnection()){
			PreparedStatement stat = con.prepareStatement(SQL_SELECT_BY_ID);
			stat.setString(1, id.getKey1());
			stat.setInt(2, id.getKey2());
			ResultSet result = stat.executeQuery();
			while(result.next()) {
				forecast.setUserLogin(result.getString("user_login"));
				forecast.setCompetitionID(result.getInt("competition_id"));
				forecast.setResult(result.getString("result").charAt(0));
			}
		} catch(ConnectionPoolException e) {
			LOGGER.error(e.getMessage());
		} catch(SQLException e) {
			LOGGER.error(e.getMessage());
		}
		return forecast;
	}

	public List<Forecast> findByLogin(String login){
		List<Forecast> forecasts = new ArrayList<>();
		try(Connection con = ConnectionPool.getInstance().takeConnection()){
			PreparedStatement stat = con.prepareStatement(SQL_SELECT_BY_LOGIN);
			stat.setString(1, login);
			ResultSet result = stat.executeQuery();
			while(result.next()) {
				Forecast forecast = new Forecast();
				forecast.setUserLogin(result.getString("user_login"));
				forecast.setCompetitionID(result.getInt("competition_id"));
				forecast.setResult(result.getString("result").charAt(0));
				forecasts.add(forecast);
			}
		} catch(ConnectionPoolException e) {
			LOGGER.error(e.getMessage());
		} catch(SQLException e) {
			LOGGER.error(e.getMessage());
		}
		return forecasts;
	}
	
	public List<Forecast> findByCompetitionID(int id){
		List<Forecast> forecasts = new ArrayList<>();
		try(Connection con = ConnectionPool.getInstance().takeConnection()){
			PreparedStatement stat = con.prepareStatement(SQL_SELECT_BY_COMPETITION_ID);
			stat.setInt(1, id);
			ResultSet result = stat.executeQuery();
			while(result.next()) {
				Forecast forecast = new Forecast();
				forecast.setUserLogin(result.getString("user_login"));
				forecast.setCompetitionID(result.getInt("competition_id"));
				forecast.setResult(result.getString("result").charAt(0));
				forecasts.add(forecast);
			}
		} catch(ConnectionPoolException e) {
			LOGGER.error(e.getMessage());
		} catch(SQLException e) {
			LOGGER.error(e.getMessage());
		}
		return forecasts;
	}
	
	@Override
	public boolean delete(Key<String, Integer> id) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean delete(Forecast entity) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean create(Forecast entity) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Forecast update(Forecast entity) {
		throw new UnsupportedOperationException();
	}

}
