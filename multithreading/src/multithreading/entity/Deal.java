package multithreading.entity;

import org.apache.log4j.Logger;


public class Deal  {
	
	private final Player player; //save reference to the player, who proposed this deal
	private final DealType type;
	private final double amount;
	private final Currency currency;
	private final double bynPerOne;
	
	private static final Logger LOGGER = Logger.getRootLogger();
	
	public Deal(Player player, DealType type, double amount, Currency currency, double perOne) {
		this.player = player;
		this.type = type;
		this.amount = amount;
		this.currency = currency;
		this.bynPerOne = perOne;
		LOGGER.debug("Create new " + toString());
	}
	
	public Player getPlayer() {
		return player;
	}

	public DealType getType() {
		return type;
	}

	public double getAmount() {
		return amount;
	}

	public Currency getCurrency() {
		return currency;
	}

	public double getBynPerOne() {
		return bynPerOne;
	}

	public boolean isPossible(Deal second) {
		//first, check is deals suitable
		if(player == second.player) {
			return false;
		}
		if(! currency.equals(second.currency)) {
			return false;
		}
		if(type.equals(second.type)) {
			return false;
		}
		if(amount != second.amount) {
			return false;
		}
		if(Math.abs(bynPerOne - second.bynPerOne) > 0.1) {
			return false;
		}
		//second, check is player steel can provide money to it
		Player p = getPlayer();
		if(type.equals(DealType.BUY)) {
			if(p.getByn() < amount * bynPerOne) {
				return false;
			}
		}
		else {
			if(p.getCurrency(currency) < amount) {
				return false;
			}
		}
		p = second.getPlayer();
		if(second.type.equals(DealType.BUY)) {
			if(p.getByn() < amount * second.bynPerOne) {
				return false;
			}			
		}
		else {
			if(p.getCurrency(currency) < amount) {
				return false;
			}
		}
		return true;
	}
	
	@Override
	public String toString() {
		return getClass().getName() + " [player=" + player.toString() + ", type=" 
				+ type + ", amount=" + amount + ", currency=" + currency
				+ ", bynPerOne=" + bynPerOne + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(amount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(bynPerOne);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((currency == null) ? 0 : currency.hashCode());
		result = prime * result + ((player == null) ? 0 : player.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Deal other = (Deal) obj;
		if (Double.doubleToLongBits(amount) != Double.doubleToLongBits(other.amount))
			return false;
		if (Double.doubleToLongBits(bynPerOne) != Double.doubleToLongBits(other.bynPerOne))
			return false;
		if (currency != other.currency)
			return false;
		if (player == null) {
			if (other.player != null)
				return false;
		} else if (player != other.player)
			return false;
		if (type != other.type)
			return false;
		return true;
	}
}
