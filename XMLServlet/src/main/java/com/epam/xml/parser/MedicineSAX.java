package com.epam.xml.parser;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import org.apache.log4j.Logger;

public class MedicineSAX extends AbstractMedicineBuilder{
	private MedicineHandler handler;
	private XMLReader reader;
	
	private static final Logger LOGGER = Logger.getRootLogger();
	
	public MedicineSAX() {
		super();
		handler = new MedicineHandler();
		try {
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser parser = factory.newSAXParser();
			reader = parser.getXMLReader();
			reader.setContentHandler(handler);
		}catch(SAXException e) {
			LOGGER.error(e.getMessage());
		}catch(ParserConfigurationException e) {
			LOGGER.error(e.getMessage());
		}
	}
	
	@Override
	public void buildSetMedicines(String fileName) {
		try {
			reader.parse(fileName);
		}catch(SAXException e) {
			LOGGER.error(e.getMessage());
		}catch(IOException e) {
			LOGGER.error(e.getMessage());
		}
		medicines = handler.getMedicines();
	}
}
