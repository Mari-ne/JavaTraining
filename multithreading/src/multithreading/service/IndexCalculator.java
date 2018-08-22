package multithreading.service;

import org.apache.log4j.Logger;
import java.math.BigDecimal;
import java.math.RoundingMode;

import multithreading.entity.Currency;
import multithreading.entity.DealType;

public class IndexCalculator {

	private static final Logger LOGGER = Logger.getRootLogger();
	
	private double[][] statistic; 
	/*
	 * Structure of statistic:
	 * buy; buy in BYN; sell; sell in BYN
	 * first - for USD,
	 * second - for EUR
	 */
	
	public IndexCalculator() {
		statistic = new double[2][4];
		for(int i = 0; i < 2; i ++) {
			for(int j = 0; j < 4; j ++) {
				statistic[i][j] = 0.0;
			}
		}
	}
	
	public void addStatistic(Currency curr, DealType type, double amount, double index) {
		LOGGER.debug("Update statistic.");
		
		int i = curr.ordinal();
		int j = 0; //usd and buy - default
		if(type.equals(DealType.SELL)) {
			j = 2;
		}
		statistic[i][j] += amount;
		statistic[i][j + 1] += amount * index;
		
	}
	
	public void changeIndex(Currency curr) {
		double newIndex = 0.0;
		int i = curr.ordinal();
		if(statistic[i][0] != 0.0) {
			//was some deals
			newIndex = statistic[i][1] / statistic[i][0];
			statistic[i][0] = 0.0;
			statistic[i][1] = 0.0;
			newIndex = (StockExchange.getInstance().getBuyIndex(curr) + newIndex) / 2;
			newIndex = new BigDecimal(newIndex).setScale(2, RoundingMode.HALF_UP).doubleValue();
			StockExchange.getInstance().setBuyIndex(newIndex, curr);
			LOGGER.debug("Change buy index for " + curr.name() + " to " + newIndex);
		}
		if(statistic[i][2] != 0.0) {
			newIndex = statistic[i][3] / statistic[i][2];
			statistic[i][2] = 0.0;
			statistic[i][3] = 0.0;
			newIndex = (StockExchange.getInstance().getSellIndex(curr) + newIndex) / 2;
			newIndex = new BigDecimal(newIndex).setScale(2, RoundingMode.HALF_UP).doubleValue();
			StockExchange.getInstance().setSellIndex(newIndex, curr);
			LOGGER.debug("Change sell index for " + curr.name() + " to " + newIndex);
		}
	}
}
