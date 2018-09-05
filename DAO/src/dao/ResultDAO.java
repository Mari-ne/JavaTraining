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

import entity.Result;

public class ResultDAO extends AbstractDAO<Integer, Result> {

	private static final String SQL_SELECT_ALL = "select * from result";
	private static final String SQL_SELECT_BY_CORRECT = "select * from result where correct = ?";
	private static final Logger LOGGER = Logger.getRootLogger();

	@Override
	public List<Result> findAll() {
		List<Result> results = new ArrayList<>();
		try(Connection con = ConnectionPool.getInstance().takeConnection()){
			PreparedStatement stat = con.prepareStatement(SQL_SELECT_ALL);
			ResultSet res = stat.executeQuery();
			while(res.next()) {
				Result result = new Result();
				result.setCorrect(res.getInt("correct"));
				result.setBetters(res.getInt("betters"));
				result.setBets(res.getDouble("bets"));
				result.setPoolPart(res.getInt("pool_part"));
				result.setPool(res.getDouble("pool"));
				result.setCoefficient(res.getDouble("coefficient"));
				results.add(result);
			}
		} catch(ConnectionPoolException e) {
			LOGGER.error(e.getMessage());
		} catch(SQLException e) {
			LOGGER.error(e.getMessage());
		}
		return results;
	}

	@Override
	public Result findByID(Integer id) {
		Result result = new Result();
		try(Connection con = ConnectionPool.getInstance().takeConnection()){
			PreparedStatement stat = con.prepareStatement(SQL_SELECT_BY_CORRECT);
			stat.setInt(1, id);
			ResultSet res = stat.executeQuery();
			while(res.next()) {
				result.setCorrect(res.getInt("correct"));
				result.setBetters(res.getInt("betters"));
				result.setBets(res.getDouble("bets"));
				result.setPoolPart(res.getInt("pool_part"));
				result.setPool(res.getDouble("pool"));
				result.setCoefficient(res.getDouble("coefficient"));
			}
		} catch(ConnectionPoolException e) {
			LOGGER.error(e.getMessage());
		} catch(SQLException e) {
			LOGGER.error(e.getMessage());
		}
		return result;
	}

	@Override
	public boolean delete(Integer id) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean delete(Result entity) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean create(Result entity) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Result update(Result entity) {
		throw new UnsupportedOperationException();
	}
}
