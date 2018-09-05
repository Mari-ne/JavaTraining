package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

import entity.SportTeam;
import pool.ConnectionPool;
import pool.ConnectionPoolException;

public class SportTeamDAO extends AbstractDAO<Integer, SportTeam> {

	private static final String SQL_SELECT_ALL_TEAM = "select * from sport_team";
	private static final String SQL_SELECT_TEAM_BY_ID = "select * from sport_team where id = ?";
	private static final String SQL_SELECT_TEAM_BY_SPORT_ID = "select * from sport_team where sport_id = ?";
	private static final String SQL_SELECT_TEAM_BY_SPORT_NAME = "select t.id, t.sport_id, t.wins, t.loses\r\n" + 
			"from sport_team as t inner join language_m2m_sport as l on c.sport_id = l.sport_id\r\n" + 
			"where l.sport = ?";
	private static final String SQL_SELECT_TEAM_BY_NAME = "select t.id, t.sport_id, t.wins, t.loses\r\n" +
			"from sport_team as t inner join language has_sport_team as l on t.id = l.sport_team_id\r\n" +
			"where l.name = ?";
	private static final Logger LOGGER = Logger.getRootLogger();
	
	@Override
	public List<SportTeam> findAll() {
		List<SportTeam> teams = new ArrayList<>();
		try(Connection con = ConnectionPool.getInstance().takeConnection()){
			PreparedStatement stat = con.prepareStatement(SQL_SELECT_ALL_TEAM);
			ResultSet result = stat.executeQuery();
			while(result.next()) {
				SportTeam team = new SportTeam();
				team.setId(result.getInt("id"));
				team.setSportID(result.getInt("sport_id"));
				team.setWins(result.getInt("wins"));
				team.setLoses(result.getInt("loses"));
				teams.add(team);
			}
		}catch(ConnectionPoolException e) {
			LOGGER.error(e.getMessage());
		}catch(SQLException e) {
			LOGGER.error(e.getMessage());
		}
		return teams;
	}

	@Override
	public SportTeam findByID(Integer id) {
		SportTeam team = new SportTeam();
		try(Connection con = ConnectionPool.getInstance().takeConnection()){
			PreparedStatement stat = con.prepareStatement(SQL_SELECT_TEAM_BY_ID);
			stat.setInt(1, id.intValue());
			ResultSet result = stat.executeQuery();
			while(result.next()) {
				team.setId(result.getInt("id"));
				team.setSportID(result.getInt("sport_id"));
				team.setWins(result.getInt("wins"));
				team.setLoses(result.getInt("loses"));
			}
		}catch(ConnectionPoolException e) {
			LOGGER.error(e.getMessage());
		}catch(SQLException e) {
			LOGGER.error(e.getMessage());
		}
		return team;
	}

	public List<SportTeam> findBySportId(int sport_id){
		List<SportTeam> teams = new ArrayList<>();
		try(Connection con = ConnectionPool.getInstance().takeConnection()){
			PreparedStatement stat = con.prepareStatement(SQL_SELECT_TEAM_BY_SPORT_ID);
			stat.setInt(1, sport_id);
			ResultSet result = stat.executeQuery();
			while(result.next()) {
				SportTeam team = new SportTeam();
				team.setId(result.getInt("id"));
				team.setSportID(result.getInt("sport_id"));
				team.setWins(result.getInt("wins"));
				team.setLoses(result.getInt("loses"));
				teams.add(team);
			}
		}catch(ConnectionPoolException e) {
			LOGGER.error(e.getMessage());
		}catch(SQLException e) {
			LOGGER.error(e.getMessage());
		}
		return teams;
	}
	
	public List<SportTeam> findBySportName(String name){
		List<SportTeam> teams = new ArrayList<>();
		try(Connection con = ConnectionPool.getInstance().takeConnection()){
			PreparedStatement stat = con.prepareStatement(SQL_SELECT_TEAM_BY_SPORT_NAME);
			stat.setString(1, name);
			ResultSet result = stat.executeQuery();
			while(result.next()) {
				SportTeam team = new SportTeam();
				team.setId(result.getInt("id"));
				team.setSportID(result.getInt("sport_id"));
				team.setWins(result.getInt("wins"));
				team.setLoses(result.getInt("loses"));
				teams.add(team);
			}
		}catch(ConnectionPoolException e) {
			LOGGER.error(e.getMessage());
		}catch(SQLException e) {
			LOGGER.error(e.getMessage());
		}
		return teams;
	}
	
	public List<SportTeam> findByTeamName(String name){
		List<SportTeam> teams = new ArrayList<>();
		try(Connection con = ConnectionPool.getInstance().takeConnection()){
			PreparedStatement stat = con.prepareStatement(SQL_SELECT_TEAM_BY_NAME);
			stat.setString(1, name);
			ResultSet result = stat.executeQuery();
			while(result.next()) {
				SportTeam team = new SportTeam();
				team.setId(result.getInt("id"));
				team.setSportID(result.getInt("sport_id"));
				team.setWins(result.getInt("wins"));
				team.setLoses(result.getInt("loses"));
				teams.add(team);
			}
		}catch(ConnectionPoolException e) {
			LOGGER.error(e.getMessage());
		}catch(SQLException e) {
			LOGGER.error(e.getMessage());
		}
		return teams;
	}
	
	@Override
	public boolean delete(Integer id) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean delete(SportTeam entity) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean create(SportTeam entity) {
		throw new UnsupportedOperationException();
	}

	@Override
	public SportTeam update(SportTeam entity) {
		throw new UnsupportedOperationException();
	}

}
