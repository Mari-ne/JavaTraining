package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

import entity.TeamVS;
import pool.ConnectionPool;
import pool.ConnectionPoolException;
import entity.Key;

public class TeamVSDAO extends AbstractDAO<Key<Integer, Integer>, TeamVS> {

	private static final String SQL_SELECT_ALL_TEAM_VS = "select * from team_vs";
	private static final String SQL_SELECT_VS_BY_IDS = "select * from team_vs\r\n" +  
				"where (team1_id = ? and team2_id = ?) or (team1_id = ? and team2_id = ?)";
	private static final String SQL_SELECT_VS_FOR_ONE = "select * from team_vs where team1_id = ? or team2_id = ?";
	private static final String SQL_SELECT_VS_BY_TEAM_NAME = "select vs.team1_id, vs.team2_id, vs.team1_wins, vs.team2_wins, vs.quantity\r\n" +
				"from team_vs as vs inner join language_has_sport_team as l1 on vs.team1_id = l1.sport_team_id\r\n" + 
				" 					inner join language_has_sport_team as l2 on vs.team2_id = l2.sport_team_id\r\n" +
				"where l1.name = ? or l2.name = ?";
	private static final String SQL_SELECT_VS_BY_TEAMS_NAME = "select vs.team1_id, vs.team2_id, vs.team1_wins, vs.team2_wins, vs.quantity\r\n" +
			"from team_vs as vs inner join language_has_sport_team as l1 on vs.team1_id = l1.sport_team_id\r\n" + 
			" 					inner join language_has_sport_team as l2 on vs.team2_id = l2.sport_team_id\r\n" +
			"where (l1.name = ? and l2.name = ?) or (l1.name = ? and l2.name = ?)";
	private static final String SQL_SELECT_VS_BY_SPORT_ID = "select vs.team1_id, vs.team2_id, vs.team1_wins, vs.team2_wins, vs.quantity\r\n" +
			"from team_vs as vs inner join sport_team as t on vs.team1_id = t.id\r\n" +
			"where t.sport_id = ?";
	private static final String SQL_SELECT_VS_BY_SPORT_NAME = "select vs.team1_id, vs.team2_id, vs.team1_wins, vs.team2_wins, vs.quantity\r\n" +
			"from team_vs as vs inner join sport_team as t on vs.team1_id = t.id\r\n" +
			" 					inner join language_m2m_sport as l on t.sport_id = l.sport_id\r\n" +
			"where l.sport = ?";
	private static final Logger LOGGER = Logger.getRootLogger();
	
	@Override
	public List<TeamVS> findAll() {
		List<TeamVS> matches = new ArrayList<>();
		try(Connection con = ConnectionPool.getInstance().takeConnection()){
			PreparedStatement stat = con.prepareStatement(SQL_SELECT_ALL_TEAM_VS);
			ResultSet result = stat.executeQuery();
			while(result.next()) {
				TeamVS match = new TeamVS();
				match.setTeam1ID(result.getInt("team1_id"));
				match.setTeam2ID(result.getInt("team2_id"));
				match.setTeam1Wins(result.getInt("team1_wins"));
				match.setTeam2Wins(result.getInt("team2_wins"));
				match.setQuantity(result.getInt("quantity"));
				matches.add(match);
			}
		}catch(ConnectionPoolException e) {
			LOGGER.error(e.getMessage());
		}catch(SQLException e) {
			LOGGER.error(e.getMessage());
		}		
		return matches;
	}
	
	public List<TeamVS> findByOneID(Integer id){
		List<TeamVS> matches = new ArrayList<>();
		try(Connection con = ConnectionPool.getInstance().takeConnection()){
			PreparedStatement stat = con.prepareStatement(SQL_SELECT_VS_FOR_ONE);
			stat.setInt(1, id);
			stat.setInt(2, id);
			ResultSet result = stat.executeQuery();
			while(result.next()) {
				TeamVS match = new TeamVS();
				match.setTeam1ID(result.getInt("team1_id"));
				match.setTeam2ID(result.getInt("team2_id"));
				match.setTeam1Wins(result.getInt("team1_wins"));
				match.setTeam2Wins(result.getInt("team2_wins"));
				match.setQuantity(result.getInt("quantity"));
				matches.add(match);
			}
		}catch(ConnectionPoolException e) {
			LOGGER.error(e.getMessage());
		}catch(SQLException e) {
			LOGGER.error(e.getMessage());
		}		
		return matches;
	}
	
	public List<TeamVS> findByTeamName(String name){
		List<TeamVS> matches = new ArrayList<>();
		try(Connection con = ConnectionPool.getInstance().takeConnection()){
			PreparedStatement stat = con.prepareStatement(SQL_SELECT_VS_BY_TEAM_NAME);
			stat.setString(1, name);
			stat.setString(2, name);
			ResultSet result = stat.executeQuery();
			while(result.next()) {
				TeamVS match = new TeamVS();
				match.setTeam1ID(result.getInt("team1_id"));
				match.setTeam2ID(result.getInt("team2_id"));
				match.setTeam1Wins(result.getInt("team1_wins"));
				match.setTeam2Wins(result.getInt("team2_wins"));
				match.setQuantity(result.getInt("quantity"));
				matches.add(match);
			}
		}catch(ConnectionPoolException e) {
			LOGGER.error(e.getMessage());
		}catch(SQLException e) {
			LOGGER.error(e.getMessage());
		}		
		return matches;
	}
	
	public TeamVS findByTeamsName(String name1, String name2) {
		TeamVS match = new TeamVS();
		try(Connection con = ConnectionPool.getInstance().takeConnection()){
			PreparedStatement stat = con.prepareStatement(SQL_SELECT_VS_BY_TEAMS_NAME);
			stat.setString(1, name1);
			stat.setString(2, name2);
			stat.setString(3, name2);
			stat.setString(4, name1);
			ResultSet result = stat.executeQuery();
			while(result.next()) {
				match.setTeam1ID(result.getInt("team1_id"));
				match.setTeam2ID(result.getInt("team2_id"));
				match.setTeam1Wins(result.getInt("team1_wins"));
				match.setTeam2Wins(result.getInt("team2_wins"));
				match.setQuantity(result.getInt("quantity"));
			}
		}catch(ConnectionPoolException e) {
			LOGGER.error(e.getMessage());
		}catch(SQLException e) {
			LOGGER.error(e.getMessage());
		}		
		return match;
	}
	
	public List<TeamVS> findBySportID(int id){
		List<TeamVS> matches = new ArrayList<>();
		try(Connection con = ConnectionPool.getInstance().takeConnection()){
			PreparedStatement stat = con.prepareStatement(SQL_SELECT_VS_BY_SPORT_ID);
			stat.setInt(1, id);
			ResultSet result = stat.executeQuery();
			while(result.next()) {
				TeamVS match = new TeamVS();
				match.setTeam1ID(result.getInt("team1_id"));
				match.setTeam2ID(result.getInt("team2_id"));
				match.setTeam1Wins(result.getInt("team1_wins"));
				match.setTeam2Wins(result.getInt("team2_wins"));
				match.setQuantity(result.getInt("quantity"));
				matches.add(match);
			}
		}catch(ConnectionPoolException e) {
			LOGGER.error(e.getMessage());
		}catch(SQLException e) {
			LOGGER.error(e.getMessage());
		}		
		return matches;
	}
	
	public List<TeamVS> findBySportName(String name){
		List<TeamVS> matches = new ArrayList<>();
		try(Connection con = ConnectionPool.getInstance().takeConnection()){
			PreparedStatement stat = con.prepareStatement(SQL_SELECT_VS_BY_SPORT_NAME);
			stat.setString(1, name);
			ResultSet result = stat.executeQuery();
			while(result.next()) {
				TeamVS match = new TeamVS();
				match.setTeam1ID(result.getInt("team1_id"));
				match.setTeam2ID(result.getInt("team2_id"));
				match.setTeam1Wins(result.getInt("team1_wins"));
				match.setTeam2Wins(result.getInt("team2_wins"));
				match.setQuantity(result.getInt("quantity"));
				matches.add(match);
			}
		}catch(ConnectionPoolException e) {
			LOGGER.error(e.getMessage());
		}catch(SQLException e) {
			LOGGER.error(e.getMessage());
		}		
		return matches;
	}
	
	@Override
	public TeamVS findByID(Key<Integer, Integer> id) {
		TeamVS match = new TeamVS();
		try(Connection con = ConnectionPool.getInstance().takeConnection()){
			PreparedStatement stat = con.prepareStatement(SQL_SELECT_VS_BY_IDS);
			stat.setInt(1, id.getKey1());
			stat.setInt(2, id.getKey2());
			stat.setInt(3, id.getKey2());
			stat.setInt(4, id.getKey1());
			ResultSet result = stat.executeQuery();
			while(result.next()) {
				match.setTeam1ID(result.getInt("team1_id"));
				match.setTeam2ID(result.getInt("team2_id"));
				match.setTeam1Wins(result.getInt("team1_wins"));
				match.setTeam2Wins(result.getInt("team2_wins"));
				match.setQuantity(result.getInt("quantity"));
			}
		}catch(ConnectionPoolException e) {
			LOGGER.error(e.getMessage());
		}catch(SQLException e) {
			LOGGER.error(e.getMessage());
		}		
		return match;
	}
	
	@Override
	public boolean delete(Key<Integer, Integer> id) {
		throw new UnsupportedOperationException();
	}
	@Override
	public boolean delete(TeamVS entity) {
		throw new UnsupportedOperationException();
	}
	@Override
	public boolean create(TeamVS entity) {
		throw new UnsupportedOperationException();
	}
	@Override
	public TeamVS update(TeamVS entity) {
		throw new UnsupportedOperationException();
	}
	
	

}
