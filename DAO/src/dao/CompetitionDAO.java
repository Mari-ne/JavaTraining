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

import entity.Competition;

public class CompetitionDAO extends AbstractDAO<Integer, Competition> {

	private static final String SQL_SELECT_ALL_COMPETITION = "select * from competition";
	private static final String SQL_SELECT_COMPETITION_BY_SPORT_NAME = "select c.id, c.sport_id, c.team1_id, c.team2_id, c.start, c.finish, c.state, c.result\r\n" + 
			"from competition as c inner join language_m2m_sport as l on c.sport_id = l.sport_id\r\n" + 
			"where l.sport = ?"; 
	private static final String SQL_SELECT_COMPETITION_BY_SPORT_ID = "select * from competition where sport_id = ?"; 
	private static final String SQL_SELECT_COMPETITION_BY_ID = "select * from competition where id = ?";
	private static final String SQL_SELECT_COMPETITION_BY_TEAM_ID = "select * from competition where team1_id = ? or team2_id = ?";
	private static final String SQL_SELECT_COMPETITON_BY_TEAM_NAME = "select c.id, c.sport_id, c.team1_id, c.team2_id, c.start, c.finish, c.state, c.result\\r\\n" +
			"from competiton as c inner join language_has_sport_team as l1 on c.team1_id = l1.sport_team_id\r\n" +
			" 					  inner join language_has_sport_team as l2 on c.team2_id = l2.sport_team_id\r\n" +
			"where l1.name = ? or l2.name = ?";
	private static final String SQL_SELECT_COMPETITION_BY_COMPETITORS_ID = "select * from competition\r\n" +
			"where (team1_id = ? and team2_id = ?) or (team1_id = ? or team2_id = ?)";
	private static final String SQL_SELECT_COMPETITION_BY_COMPETITORS_NAME = "select c.id, c.sport_id, c.team1_id, c.team2_id, c.start, c.finish, c.state, c.result\\r\\n" +
			"from competiton as c inner join language_has_sport_team as l1 on c.team1_id = l1.sport_team_id\r\n" +
			" 					  inner join language_has_sport_team as l2 on c.team2_id = l2.sport_team_id\r\n" +
			"where (l1.name = ? and l2.name = ?) or (l1.name = ? and l2.name = ?)";
	private static final Logger LOGGER = Logger.getRootLogger();
	
	@Override
	public List<Competition> findAll() {
		List<Competition> competitions = new ArrayList<>();
		try (Connection con = ConnectionPool.getInstance().takeConnection()){
			PreparedStatement stat = null;
			stat = con.prepareStatement(SQL_SELECT_ALL_COMPETITION);
			ResultSet result = stat.executeQuery();
			while(result.next()) {
				Competition comp = new Competition();
				comp.setId(result.getInt("id"));
				comp.setSportID(result.getInt("sport_id"));
				comp.setTeam1ID(result.getInt("team1_id"));
				comp.setTeam2ID(result.getInt("team2_id"));
				comp.setStart(result.getDate("start"));
				comp.setFinish(result.getDate("finish"));
				comp.setState(result.getString("state"));
				comp.setResult(result.getString("result"));
				competitions.add(comp);
			}
		}catch(ConnectionPoolException e) {
			LOGGER.error(e.getMessage());
		}catch(SQLException e) {
			LOGGER.error(e.getMessage());
		}
		return competitions;
	}

	public List<Competition> findBySportName(String name){
		List<Competition> competitions = new ArrayList<>();
		try (Connection con = ConnectionPool.getInstance().takeConnection()){
			PreparedStatement stat = null;
			stat = con.prepareStatement(SQL_SELECT_COMPETITION_BY_SPORT_NAME);
			stat.setString(1, name);
			ResultSet result = stat.executeQuery();
			while(result.next()) {
				Competition comp = new Competition();
				comp.setId(result.getInt("id"));
				comp.setSportID(result.getInt("sport_id"));
				comp.setTeam1ID(result.getInt("team1_id"));
				comp.setTeam2ID(result.getInt("team2_id"));
				comp.setStart(result.getDate("start"));
				comp.setFinish(result.getDate("finish"));
				comp.setState(result.getString("state"));
				comp.setResult(result.getString("result"));
				competitions.add(comp);
			}
		}catch(ConnectionPoolException e) {
			LOGGER.error(e.getMessage());
		}catch(SQLException e) {
			LOGGER.error(e.getMessage());
		}
		return competitions;
	}
	
	public List<Competition> findBySportID(int id){
		List<Competition> competitions = new ArrayList<>();
		try (Connection con = ConnectionPool.getInstance().takeConnection()){
			PreparedStatement stat = null;
			stat = con.prepareStatement(SQL_SELECT_COMPETITION_BY_SPORT_ID);
			stat.setInt(1, id);
			ResultSet result = stat.executeQuery();
			while(result.next()) {
				Competition comp = new Competition();
				comp.setId(result.getInt("id"));
				comp.setSportID(result.getInt("sport_id"));
				comp.setTeam1ID(result.getInt("team1_id"));
				comp.setTeam2ID(result.getInt("team2_id"));
				comp.setStart(result.getDate("start"));
				comp.setFinish(result.getDate("finish"));
				comp.setState(result.getString("state"));
				comp.setResult(result.getString("result"));
				competitions.add(comp);
			}
		}catch(ConnectionPoolException e) {
			LOGGER.error(e.getMessage());
		}catch(SQLException e) {
			LOGGER.error(e.getMessage());
		}
		return competitions;
	}
	
	public List<Competition> findByTeamID(int id){
		List<Competition> competitions = new ArrayList<>();
		try (Connection con = ConnectionPool.getInstance().takeConnection()){
			PreparedStatement stat = null;
			stat = con.prepareStatement(SQL_SELECT_COMPETITION_BY_TEAM_ID);
			stat.setInt(1, id);
			stat.setInt(2, id);
			ResultSet result = stat.executeQuery();
			while(result.next()) {
				Competition comp = new Competition();
				comp.setId(result.getInt("id"));
				comp.setSportID(result.getInt("sport_id"));
				comp.setTeam1ID(result.getInt("team1_id"));
				comp.setTeam2ID(result.getInt("team2_id"));
				comp.setStart(result.getDate("start"));
				comp.setFinish(result.getDate("finish"));
				comp.setState(result.getString("state"));
				comp.setResult(result.getString("result"));
				competitions.add(comp);
			}
		}catch(ConnectionPoolException e) {
			LOGGER.error(e.getMessage());
		}catch(SQLException e) {
			LOGGER.error(e.getMessage());
		}
		return competitions;
	}
	
	public List<Competition> findByTeamName(String name){
		List<Competition> competitions = new ArrayList<>();
		try (Connection con = ConnectionPool.getInstance().takeConnection()){
			PreparedStatement stat = null;
			stat = con.prepareStatement(SQL_SELECT_COMPETITON_BY_TEAM_NAME);
			stat.setString(1, name);
			stat.setString(2, name);
			ResultSet result = stat.executeQuery();
			while(result.next()) {
				Competition comp = new Competition();
				comp.setId(result.getInt("id"));
				comp.setSportID(result.getInt("sport_id"));
				comp.setTeam1ID(result.getInt("team1_id"));
				comp.setTeam2ID(result.getInt("team2_id"));
				comp.setStart(result.getDate("start"));
				comp.setFinish(result.getDate("finish"));
				comp.setState(result.getString("state"));
				comp.setResult(result.getString("result"));
				competitions.add(comp);
			}
		}catch(ConnectionPoolException e) {
			LOGGER.error(e.getMessage());
		}catch(SQLException e) {
			LOGGER.error(e.getMessage());
		}
		return competitions;
	}
	
	public List<Competition> findByCompetitorsID(int id1, int id2){
		List<Competition> competitions = new ArrayList<>();
		try (Connection con = ConnectionPool.getInstance().takeConnection()){
			PreparedStatement stat = null;
			stat = con.prepareStatement(SQL_SELECT_COMPETITION_BY_COMPETITORS_ID);
			stat.setInt(1, id1);
			stat.setInt(2, id2);
			stat.setInt(3, id2);
			stat.setInt(4, id1);
			ResultSet result = stat.executeQuery();
			while(result.next()) {
				Competition comp = new Competition();
				comp.setId(result.getInt("id"));
				comp.setSportID(result.getInt("sport_id"));
				comp.setTeam1ID(result.getInt("team1_id"));
				comp.setTeam2ID(result.getInt("team2_id"));
				comp.setStart(result.getDate("start"));
				comp.setFinish(result.getDate("finish"));
				comp.setState(result.getString("state"));
				comp.setResult(result.getString("result"));
				competitions.add(comp);
			}
		}catch(ConnectionPoolException e) {
			LOGGER.error(e.getMessage());
		}catch(SQLException e) {
			LOGGER.error(e.getMessage());
		}
		return competitions;
	}
	
	public List<Competition> findByCompetitorsName(String name1, String name2){
		List<Competition> competitions = new ArrayList<>();
		try (Connection con = ConnectionPool.getInstance().takeConnection()){
			PreparedStatement stat = null;
			stat = con.prepareStatement(SQL_SELECT_COMPETITION_BY_COMPETITORS_NAME);
			stat.setString(1, name1);
			stat.setString(2, name2);
			stat.setString(3, name2);
			stat.setString(4, name1);
			ResultSet result = stat.executeQuery();
			while(result.next()) {
				Competition comp = new Competition();
				comp.setId(result.getInt("id"));
				comp.setSportID(result.getInt("sport_id"));
				comp.setTeam1ID(result.getInt("team1_id"));
				comp.setTeam2ID(result.getInt("team2_id"));
				comp.setStart(result.getDate("start"));
				comp.setFinish(result.getDate("finish"));
				comp.setState(result.getString("state"));
				comp.setResult(result.getString("result"));
				competitions.add(comp);
			}
		}catch(ConnectionPoolException e) {
			LOGGER.error(e.getMessage());
		}catch(SQLException e) {
			LOGGER.error(e.getMessage());
		}
		return competitions;
	}
	
	@Override
	public Competition findByID(Integer id) {
		Competition comp = new Competition();
		try (Connection con = ConnectionPool.getInstance().takeConnection()){
			PreparedStatement stat = null;
			stat = con.prepareStatement(SQL_SELECT_COMPETITION_BY_ID);
			stat.setInt(1, id.intValue());
			ResultSet result = stat.executeQuery();
			while(result.next()) {
				comp.setId(result.getInt("id"));
				comp.setSportID(result.getInt("sport_id"));
				comp.setTeam1ID(result.getInt("team1_id"));
				comp.setTeam2ID(result.getInt("team2_id"));
				comp.setStart(result.getDate("start"));
				comp.setFinish(result.getDate("finish"));
				comp.setState(result.getString("state"));
				comp.setResult(result.getString("result"));
			}
		}catch(ConnectionPoolException e) {
			LOGGER.error(e.getMessage());
		}catch(SQLException e) {
			LOGGER.error(e.getMessage());
		}
		return comp;
	}

	@Override
	public boolean delete(Integer id) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean delete(Competition entity) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean create(Competition entity) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Competition update(Competition entity) {
		throw new UnsupportedOperationException();
	}

}
