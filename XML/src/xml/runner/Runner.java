package xml.runner;

import java.util.Scanner;

import org.apache.log4j.Logger;

import xml.service.AbstractMedicineBuilder;
import xml.service.MedicineBuildFactory;

public class Runner {

	private static final Logger LOGGER = Logger.getRootLogger();
	
	public static void main(String[] args) {
		MedicineBuildFactory factory = new MedicineBuildFactory();
		Scanner input = new Scanner(System.in);
		LOGGER.info("1.SAX\n2.DOM\n3.StAX\n");
		int i = input.nextInt();
		AbstractMedicineBuilder builder = factory.createBilder(i);
		builder.buildSetMedicines("xml\\medicine.xml");
		LOGGER.info(builder.getMedicines());
		input.close();
	}

}
