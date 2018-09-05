package entity;

public class Result extends Entity {

	private int correct;
	private int betters;
	private double bets;
	private int poolPart;
	private double pool;
	private double coefficient;
	
	public Result() {}

	public Result(int correct, int betters, double bets, int poolPart, double pool, double coefficient) {
		this.correct = correct;
		this.betters = betters;
		this.bets = bets;
		this.poolPart = poolPart;
		this.pool = pool;
		this.coefficient = coefficient;
	}

	
	public int getCorrect() {
		return correct;
	}

	public void setCorrect(int correct) {
		this.correct = correct;
	}

	public int getBetters() {
		return betters;
	}

	public void setBetters(int betters) {
		this.betters = betters;
	}

	public double getBets() {
		return bets;
	}

	public void setBets(double bets) {
		this.bets = bets;
	}

	public int getPoolPart() {
		return poolPart;
	}

	public void setPoolPart(int poolPart) {
		this.poolPart = poolPart;
	}

	public double getPool() {
		return pool;
	}

	public void setPool(double pool) {
		this.pool = pool;
	}

	public double getCoefficient() {
		return coefficient;
	}

	public void setCoefficient(double coefficient) {
		this.coefficient = coefficient;
	}

	@Override
	public String toString() {
		return "Result [correct=" + correct + ", betters=" + betters 
				+ ", bets=" + bets + ", poolPart=" + poolPart
				+ ", pool=" + pool + ", coefficient=" + coefficient + "]";
	}
	
	
	
	
}
