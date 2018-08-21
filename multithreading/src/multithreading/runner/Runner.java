package multithreading.runner;

import org.apache.log4j.Logger;

import multithreading.entity.Player;
import multithreading.service.StockExchange;


public class Runner {

	private static final Logger LOGGER = Logger.getRootLogger();
	
	public static void main(String[] args) throws InterruptedException {
		LOGGER.debug("App start.");
		Player p1 = new Player(1000, 1000, 1000);
		Player p2 = new Player(1000, 1000, 1000);
		Player p3 = new Player(1000, 1000, 1000);
		Player p4 = new Player(1000, 1000, 1000);
		StockExchange.getInstance().addPlayer(p1);
		StockExchange.getInstance().addPlayer(p2);
		StockExchange.getInstance().addPlayer(p3);
		StockExchange.getInstance().addPlayer(p4);
		StockExchange.getInstance().run(); //initial state of stock exchange
		p1.start();
		p2.start();
		p3.start();
		p4.start();
	}

}
