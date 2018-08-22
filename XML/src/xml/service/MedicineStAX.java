package xml.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import org.apache.log4j.Logger;

import medicine.Certificate;
import medicine.Certificated;
import medicine.Dosage;
import medicine.MedicineEnum;

public class MedicineStAX extends AbstractMedicineBuilder{
	private XMLInputFactory inputfactory;
	
	private static final Logger LOGGER = Logger.getRootLogger();
	
	public MedicineStAX() {
		super();
		inputfactory = XMLInputFactory.newInstance();
	}
	
	@Override
	public void buildSetMedicines(String fileName) {
		FileInputStream inputStream = null;
		XMLStreamReader reader = null;
		String name;
		try {
			inputStream = new FileInputStream(new File(fileName));
			reader = inputfactory.createXMLStreamReader(inputStream);
			while(reader.hasNext()) {
				int type = reader.next();
				if(type == XMLStreamConstants.START_ELEMENT) {
					name = reader.getLocalName();
					if(MedicineEnum.getEnum(name) == MedicineEnum.CERTIFICATED) {
						Certificated medicine = buildMedicine(reader);
						medicines.add(medicine);
					}
				}	
			}
		}catch(XMLStreamException e) {
			LOGGER.error(e.getMessage());
		}catch(FileNotFoundException e) {
			LOGGER.error(e.getMessage());
		}finally {
			try {
				if(inputStream != null) {
					inputStream.close();
				}
			}catch(IOException e) {
				LOGGER.error(e.getMessage());
			}
		}
	}
	
	private Certificated buildMedicine(XMLStreamReader reader) throws XMLStreamException{
		Certificated medicine = new Certificated();
		
		medicine.setName(reader.getAttributeValue(null, MedicineEnum.NAME.getValue()));
		medicine.setPharm(reader.getAttributeValue(null, MedicineEnum.PHARM.getValue()));
		medicine.setGroup(reader.getAttributeValue(null, MedicineEnum.GROUP.getValue()));
		if(reader.getAttributeValue(null, MedicineEnum.VALID.getValue()) != null) {
			medicine.setValid(new BigInteger(reader.getAttributeValue(null, MedicineEnum.VALID.getValue())));
		}
		
		String name;
		while(reader.hasNext()) {
			int type = reader.next();
			switch(type) {
				case XMLStreamConstants.START_ELEMENT:
					name = reader.getLocalName();
					switch(MedicineEnum.getEnum(name)) {
						case ANALOGS:
							medicine.setAnalogs(getText(reader));
							break;
						case PRICE:
							medicine.setPrice(new Double(getText(reader)).floatValue());
							break;
						case WRAPPED:
							try {
								medicine.setWrapped(DatatypeFactory.newInstance().newXMLGregorianCalendar(getText(reader)));
							} catch (DatatypeConfigurationException e) {
								LOGGER.error(e.getMessage());
							}
							break;
						case FORM:
							medicine.setForm(getText(reader));
							break;
						case DOSAGE:
							medicine.setDosage(getDosage(reader));
							break;
						case CERCIFICATE:
							medicine.setCertificate(getCertificate(reader));
							break;
					}
					break;
				case XMLStreamConstants.END_ELEMENT:
					name = reader.getLocalName();
					if(MedicineEnum.getEnum(name) == MedicineEnum.CERTIFICATED) {
						return medicine;
					}
					break;
			}
		}
		throw new XMLStreamException("Unknown element in Certificated type");
	}
	
	private String getText(XMLStreamReader reader) throws XMLStreamException{
		String text = null;
		if(reader.hasNext()) {
			reader.next();
			text = reader.getText();
		}
		return text;
	}
	
	private Dosage getDosage(XMLStreamReader reader) throws XMLStreamException{
		Dosage dosage = new Dosage();
		int type;
		String name;
		while(reader.hasNext()) {
			type = reader.next();
			switch(type) {
				case XMLStreamConstants.START_ELEMENT:
					name = reader.getLocalName();
					switch(MedicineEnum.getEnum(name)) {
						case AMOUNT:
							dosage.setAmount(new Double(getText(reader)).floatValue());
							break;
						case FREQ:
							dosage.setFrequency(new BigInteger(getText(reader)));
							break;
						case PERIOD:
							dosage.setPeriod(new BigInteger(getText(reader)));
							break;
					}
					break;
				case XMLStreamConstants.END_ELEMENT:
					name = reader.getLocalName();
					if(MedicineEnum.getEnum(name) == MedicineEnum.DOSAGE) {
						return dosage;
					}
					break;
			}
		}
		throw new XMLStreamException("Unknow element in Dosage type");
	}
	
	private Certificate getCertificate(XMLStreamReader reader) throws XMLStreamException{
		Certificate certif = new Certificate();
		int type;
		String name;
		while(reader.hasNext()) {
			type = reader.next();
			switch(type) {
				case XMLStreamConstants.START_ELEMENT:
					name = reader.getLocalName();
					switch(MedicineEnum.getEnum(name)) {
						case NUMBER:
							certif.setNumber(getText(reader));
							break;
						case ISSUED:
							try {
								certif.setIssued(DatatypeFactory.newInstance().newXMLGregorianCalendar(getText(reader)));
							} catch (DatatypeConfigurationException e) {
								LOGGER.error(e.getMessage());
							}
							break;
						case UNTIL:
							try {
								certif.setUntil(DatatypeFactory.newInstance().newXMLGregorianCalendar(getText(reader)));
							} catch (DatatypeConfigurationException e) {
								LOGGER.error(e.getMessage());
							}
							break;
						case REG:
							certif.setRegister(getText(reader));
							break;
					}
					break;
				case XMLStreamConstants.END_ELEMENT:
					name = reader.getLocalName();
					if(MedicineEnum.getEnum(name) == MedicineEnum.CERCIFICATE) {
						return certif;
					}
					break;
			}
		}
		throw new XMLStreamException("Unknow element in Certificate type");
	}
}
