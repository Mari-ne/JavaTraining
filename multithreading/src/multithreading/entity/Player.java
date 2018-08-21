package multithreading.entity;

import java.lang.Thread;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import multithreading.service.StockExchange;

public class Player extends Thread {

	private double byn;
	private double usd;
	private double eur;
	
	private CyclicBarrier barrier;
	
	private static final Logger LOGGER = Logger.getRootLogger(); 
	
	public Player(double byn, double usd, double eur) {
		this.byn = byn;
		this.usd = usd;
		this.eur = eur;
		barrier = StockExchange.getInstance().getBarrier();
		LOGGER.debug("Created new " + toString());
	}
	
	public double getByn() {
		return byn;
	}

	public void setByn(double byn) {
		this.byn = byn;
	}

	public double getCurrency(Currency curr) {
		if(curr.equals(Currency.EUR)) {
			return eur;
		}
		return usd;
	}

	public void setCuerrency(Currency curr, double amount) {
		if(curr.equals(Currency.EUR)) {
			eur = amount;
		}
		else {
			usd = amount;
		}
	}
	
	public void run() {
		//firstly, player will create 3 deal
		LOGGER.debug("Thread " + this.getName() + " start.");
		for(int i = 0; i < 3; i ++) {
			createDial();
		}
		try {
			for(int j = 0; j < 5; j ++) {
				for(int i = 0; i < 5; i ++) {
					TimeUnit.SECONDS.sleep(1); //wait some time
					
					//check if there suitable deals
					List<Deal> pair = StockExchange.getInstance().findDeal(this);
					if(!pair.isEmpty()) {
						StockExchange.getInstance().transaction(pair.get(0), pair.get(1));
					}
					TimeUnit.SECONDS.sleep(1);
					//make new deal
					createDial();
				}
				barrier.await();
			}
			StockExchange.getInstance().saveIndex();
		}catch(InterruptedException e) {
			LOGGER.error(e.getMessage());
			Thread.currentThread().interrupt();
		}catch(BrokenBarrierException e) {
			LOGGER.error(e.getMessage());
		}
		
	}
	
	public void createDial() {
		Random random = new Random();
		
		double amount;
		double index;
		DealType type = DealType.values()[random.nextInt(2)]; //buy or sell
		Currency curr = Currency.values()[random.nextInt(2)]; //usd or eur
		if(type.equals(DealType.BUY)) {
			amount = Math.floor(random.nextDouble() * byn); //how much spend
			
			index = StockExchange.getInstance().getBuyIndex(curr);
			index = index *(1 + (random.nextDouble() / 8.0) - (random.nextDouble() / 8.0));
			index = new BigDecimal(index).setScale(2, RoundingMode.HALF_UP).doubleValue();
			
			amount = Math.floor(amount / index);
		}
		else {
			if(curr.equals(Currency.EUR)) {
				amount = Math.floor(random.nextDouble() * eur); // how much sell
			}
			else {
				amount = Math.floor(random.nextDouble() * usd); // how much sell
			}
			
			index = StockExchange.getInstance().getSellIndex(curr);
			index = index *(1 + (random.nextDouble() / 8.0) - (random.nextDouble() / 8.0));
			index = new BigDecimal(index).setScale(2, RoundingMode.HALF_UP).doubleValue();
		}
		amount = Math.ceil(amount / 100) * 100;
		StockExchange.getInstance().addDeal(new Deal(this, type, amount, curr, index));
	}
	
	@Override
	public String toString() {
		return getClass().getName() + " [byn=" + byn + ", usd=" + usd + ", eur=" + eur + "]";
	}
}
