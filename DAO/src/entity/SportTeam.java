package entity;

public class SportTeam extends Entity {
	
	private int id;
	private int sportID;
	private String name;
	private int wins;
	private int loses;
	
	public SportTeam() {}
	
	public SportTeam(int id, int sportID, String name, int wins, int loses) {
		this.id = id;
		this.sportID = sportID;
		this.name = name;
		this.wins = wins;
		this.loses = loses;
	}

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getWins() {
		return wins;
	}

	public void setWins(int wins) {
		this.wins = wins;
	}

	public int getLoses() {
		return loses;
	}

	public void setLoses(int loses) {
		this.loses = loses;
	}

	@Override
	public String toString() {
		return "SportTeam [id=" + id + ", sportID=" + sportID 
				+ ", name=" + name + ", wins=" + wins + ", loses=" + loses + "]";
	}
	

}
