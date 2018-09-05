package entity;

public class PersonalResult extends Entity {

	private String userLogin;
	private Double lastBet;
	private Double lastGain;
	private Double allBet;
	private Double allGain;
	
	public PersonalResult() {}
	
	public PersonalResult(String userLogin, Double lastBet, Double lastGain, Double allBet, Double allGain) {
		this.userLogin = userLogin;
		this.lastBet = lastBet;
		this.lastGain = lastGain;
		this.allBet = allBet;
		this.allGain = allGain;
	}

	public String getUserLogin() {
		return userLogin;
	}

	public void setUserLogin(String userLogin) {
		this.userLogin = userLogin;
	}

	public Double getLastBet() {
		return lastBet;
	}

	public void setLastBet(Double lastBet) {
		this.lastBet = lastBet;
	}

	public Double getLastGain() {
		return lastGain;
	}

	public void setLastGain(Double lastGain) {
		this.lastGain = lastGain;
	}

	public Double getAllBet() {
		return allBet;
	}

	public void setAllBet(Double allBet) {
		this.allBet = allBet;
	}

	public Double getAllGain() {
		return allGain;
	}

	public void setAllGain(Double allGain) {
		this.allGain = allGain;
	}

	@Override
	public String toString() {
		return "PersonalResult [userLogin=" + userLogin + ", lastBet=" + lastBet 
				+ ", lastGain=" + lastGain + ", allBet=" + allBet + ", allGain=" + allGain + "]";
	}
	
	
	
}
