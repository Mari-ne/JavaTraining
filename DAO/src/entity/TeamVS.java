package entity;

public class TeamVS extends Entity{

	private int team1ID;
	private int team2ID;
	private int team1Wins;
	private int team2Wins;
	private int quantity;
	
	public TeamVS() {}

	public TeamVS(int team1id, int team2id, int team1Wins, int team2Wins, int quantity) {
		team1ID = team1id;
		team2ID = team2id;
		this.team1Wins = team1Wins;
		this.team2Wins = team2Wins;
		this.quantity = quantity;
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

	public int getTeam1Wins() {
		return team1Wins;
	}

	public void setTeam1Wins(int team1Wins) {
		this.team1Wins = team1Wins;
	}

	public int getTeam2Wins() {
		return team2Wins;
	}

	public void setTeam2Wins(int team2Wins) {
		this.team2Wins = team2Wins;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "TeamVS [team1ID=" + team1ID + ", team2ID=" + team2ID 
				+ ", team1Wins=" + team1Wins + ", team2Wins="
				+ team2Wins + ", quantity=" + quantity + "]";
	}
	
	
}
