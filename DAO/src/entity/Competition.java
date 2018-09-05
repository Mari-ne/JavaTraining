package entity;

import java.sql.Date;

public class Competition extends Entity {

	private int id;
	private int sportID;
	private int team1ID;
	private int team2ID;
	private Date start;
	private Date finish;
	private String state;
	private String result;
	
	public Competition() {}
	
	public Competition(int id, int sportID, int team1ID, int team2ID,
						Date start, Date finish, String state, String result) {
		this.id = id;
		this.sportID = sportID;
		this.team1ID = team1ID;
		this.team2ID = team2ID;
		this.start = start;
		this.finish = finish;
		this.state = state;
		this.result = result;
	}
	
	private static final long serialVersionUID = 1L;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSportID() {
		return sportID;
	}

	public void setSportID(int sportID) {
		this.sportID = sportID;
	}

	public int getTeam1ID() {
		return team1ID;
	}

	public void setTeam1ID(int team1id) {
		team1ID = team1id;
	}

	public int getTeam2ID() {
		return team2ID;
	}

	public void setTeam2ID(int team2id) {
		team2ID = team2id;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getFinish() {
		return finish;
	}

	public void setFinish(Date finish) {
		this.finish = finish;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return "Competition [id=" + id + ", sportID=" + sportID 
				+ ", team1ID=" + team1ID + ", team2ID=" + team2ID
				+ ", start=" + start + ", finish=" + finish 
				+ ", state=" + state + ", result=" + result + "]";
	}
	

}
