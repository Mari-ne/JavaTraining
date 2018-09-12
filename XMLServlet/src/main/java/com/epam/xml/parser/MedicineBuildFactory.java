package com.epam.xml.parser;

import org.apache.log4j.Logger;

public class MedicineBuildFactory {

	private static final Logger LOGGER = Logger.getRootLogger();
	
	private enum ParserType{
		SAX,
		DOM,
		STAX;
	}
	
	public AbstractMedicineBuilder createBuilder(String name) {
		ParserType type = ParserType.valueOf(name.toUpperCase());
		AbstractMedicineBuilder builder = null;
		switch(type) {
			case SAX:
				builder = new MedicineSAX();
				break;
			case DOM:
				builder = new MedicineDOM();
				break;
			case STAX:
				builder = new MedicineStAX();
				break;
		}
		return builder;
	}
	
	public AbstractMedicineBuilder createBuilder(int name) {
		ParserType[] type = ParserType.values();
		AbstractMedicineBuilder builder = null;
		switch(type[name - 1]) {
			case SAX:
				builder = new MedicineSAX();
				break;
			case DOM:
				builder = new MedicineDOM();
				break;
			case STAX:
				builder = new MedicineStAX();
				break;
		}
		return builder;
	}
}
