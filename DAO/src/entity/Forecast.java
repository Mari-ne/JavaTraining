package entity;

public class Forecast extends Entity {

	private String userLogin;
	private int competitionID;
	private char result;
	
	public Forecast() {}

	public Forecast(String userLogin, int competitionID, char result) {
		this.userLogin = userLogin;
		this.competitionID = competitionID;
		this.result = result;
	}

	public String getUserLogin() {
		return userLogin;
	}

	public void setUserLogin(String userLogin) {
		this.userLogin = userLogin;
	}

	public int getCompetitionID() {
		return competitionID;
	}

	public void setCompetitionID(int competitionID) {
		this.competitionID = competitionID;
	}

	public char getResult() {
		return result;
	}

	public void setResult(char result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return "Forecast [userLogin=" + userLogin + ", competitionID=" 
				+ competitionID + ", result=" + result + "]";
	}
	
	
}
