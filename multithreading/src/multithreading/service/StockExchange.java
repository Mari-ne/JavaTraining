package multithreading.service;

import java.util.List;
import java.util.Locale;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.ReentrantLock;
import org.apache.log4j.Logger;
import java.util.Formatter;

import multithreading.entity.*;

public class StockExchange implements Runnable{

	private double buyIndexUSD;
	private double sellIndexUSD;
	private double buyIndexEUR;
	private double sellIndexEUR;
	private List<Player> players;
	private List<Deal> deals;
	private IndexCalculator calculator;

	private CyclicBarrier barrier;
	private final ReentrantLock lock = new ReentrantLock();
	
	private static StockExchange instance = null;
	
	private static final Logger LOGGER = Logger.getRootLogger();
	
	private StockExchange(double buyUSD, double sellUSD, double buyEUR, double sellEUR) {
		buyIndexUSD = buyUSD;
		sellIndexUSD = sellUSD;
		buyIndexEUR = buyEUR;
		sellIndexEUR = sellEUR;
		players = new ArrayList<>();
		deals = new ArrayList<>();
		calculator = new IndexCalculator();
		barrier = new CyclicBarrier(4, () -> StockExchange.getInstance().run());
		LOGGER.debug("Create new stock exchange.");
	}
	
	public static StockExchange getInstance() {
		if(instance == null) {
			//create object
			double bU = 1.0; // buy index usd
			double sU = 1.0; //sell index usd
			double bE = 1.0; // buy index eur
			double sE = 1.0; //sell index eur
			//initial data locate in file index.txt
			Scanner reader = null;
			try {
				reader = new Scanner(new FileReader("resourse\\index.txt"));
        reader.useLocale(Locale.US);
        reader.useDelimiter(" ");
        bU = reader.nextDouble(); // buy index usd
        sU = reader.nextDouble(); //sell index usd
        bE = reader.nextDouble(); // buy index eur
        sE = reader.nextDouble(); //sell index eur
			} catch (FileNotFoundException e) {
				LOGGER.error(e.getMessage());
			}finally{
			  reader.close();
			}
			instance = new StockExchange(bU, sU, bE, sE);
		}
		return instance;
	}
	
	public void addDeal(Deal newDeal) {
		deals.add(newDeal);		
	}
	
	public void addPlayer(Player newPlayer) {
		players.add(newPlayer);
	}

	public void setBuyIndex(double buyIndex, Currency curr) {
		if(curr.equals(Currency.EUR)) {
			this.buyIndexEUR = buyIndex;
		}
		else {
			this.buyIndexUSD = buyIndex;
		}
	}

	public void setSellIndex(double sellIndex, Currency curr) {
		if(curr.equals(Currency.EUR)) {
			this.sellIndexEUR = sellIndex;
		}
		else {
			this.sellIndexUSD = sellIndex;
		}
	}

	public double getBuyIndex(Currency curr) {
		if(curr.equals(Currency.EUR)) {
			return buyIndexEUR;
		}
		return buyIndexUSD;
	}
	
	public double getSellIndex(Currency curr) {
		if(curr.equals(Currency.EUR)) {
			return sellIndexEUR;
		}
		return sellIndexUSD;
	}

	public CyclicBarrier getBarrier() {
		return barrier;
	}
	
	public List<Deal> findDeal(Player player){
		lock.lock();
		List<Deal> dealPair = new ArrayList<>();
		int i = 0;
		while(i < deals.size() - 1) {
			int j = i + 1 ;
			while(j < deals.size()) {
				if(deals.get(i).isPossible(deals.get(j))) {
					if(deals.get(i).getPlayer() == player || deals.get(j).getPlayer() == player) {
						dealPair.add(deals.get(i));
						dealPair.add(deals.get(j));
						LOGGER.debug("Find matched deals.");
						break;
					}
				}
				j ++;
			}
			i ++;
		}
		if(dealPair.isEmpty()) {
			lock.unlock();
		}
		return dealPair;
	}
	
	public void transaction(Deal first, Deal second) {
		try {
			try {
				LOGGER.debug("Begin transaction. Deal 1: " + first.toString() + " | Deal 2: " + second.toString());
				calculator.addStatistic(first.getCurrency(), first.getType(), first.getAmount(), first.getBynPerOne());
				calculator.addStatistic(second.getCurrency(), second.getType(), second.getAmount(), second.getBynPerOne());
				
				Player p1 = first.getPlayer();
				Player p2 = second.getPlayer();
				Currency curr = first.getCurrency();
				
				if(first.getType().equals(DealType.BUY)) {
					p1.setCuerrency(curr, first.getAmount() + p1.getCurrency(curr));
					p1.setByn(p1.getByn() - first.getAmount() * first.getBynPerOne());
					
					p2.setCuerrency(curr, p2.getCurrency(curr) - second.getAmount());
					p2.setByn(p2.getByn() + second.getAmount() * second.getBynPerOne());
				}
				else {
					p2.setCuerrency(curr, second.getAmount() + p2.getCurrency(curr));
					p2.setByn(p2.getByn() - second.getAmount() * second.getBynPerOne());
					
					p1.setCuerrency(curr, p1.getCurrency(curr) - first.getAmount());
					p1.setByn(p1.getByn() + first.getAmount() * first.getBynPerOne());
				}
				LOGGER.debug("End transaction.");
				//delete deals 
				deals.remove(first);
				deals.remove(second);
			}finally {
				lock.unlock();
			}
		} catch(IllegalMonitorStateException e) {
			LOGGER.error(e.getMessage());
		}/*catch (InterruptedException e) {
			LOGGER.error(e.getMessage());
		}*/
	}
	
	@Override
	public void run() {
		Formatter form = new Formatter();
		int num = 1;
		calculator.changeIndex(Currency.EUR);
		calculator.changeIndex(Currency.USD);
		form.format("№| %10s | %10s | %10s\n", "USD", "EUR", "BYN");
		for(Player player : players) {
			form.format("%d| %10.2f | %10.2f | %10.2f\n", num ++, player.getCurrency(Currency.USD), 
						player.getCurrency(Currency.EUR), player.getByn());
		}
		form.format("Buy USD: %4.4f\tSell USD: %4.4f\nBuy EUR: %4.4f\tSell EUR: %4.4f\n", 
					buyIndexUSD, sellIndexUSD, buyIndexEUR, sellIndexEUR);
		LOGGER.info(form.toString());
		form.close();
	}
	
	public void saveIndex() {
		FileWriter writer = null;
		try {
			writer = new FileWriter("resourse\\index.txt");
			writer.write(Double.toString(buyIndexUSD));
			writer.write(" ");
			writer.write(Double.toString(sellIndexUSD));
			writer.write(" ");
			writer.write(Double.toString(buyIndexEUR));
			writer.write(" ");
			writer.write(Double.toString(sellIndexEUR));
			writer.write(" ");
			writer.close();
			//after saving new index, stock exchange is closing
			endDay();
		} catch (IOException e) {
			LOGGER.error(e.getMessage());
		}finally {
		}
		
	}
	
	public void endDay() {
		for(Player player : players) {
			player.interrupt();
		}
	}
}
