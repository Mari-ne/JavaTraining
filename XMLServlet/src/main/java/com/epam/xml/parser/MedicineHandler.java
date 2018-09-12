package com.epam.xml.parser;

import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.Attributes;

import java.math.BigInteger;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;

import org.apache.log4j.Logger;

import com.epam.xml.medicine.Certificated;
import com.epam.xml.medicine.MedicineEnum;

public class MedicineHandler extends DefaultHandler {

	private Set<Certificated> medicines;
	private Certificated current;
	private MedicineEnum currEnum = null;
	private EnumSet<MedicineEnum> withText;
	
	private static final Logger LOGGER = Logger.getRootLogger();
	
	public MedicineHandler() {
		medicines = new HashSet<Certificated>();
		withText = EnumSet.range(MedicineEnum.ANALOGS, MedicineEnum.REG);
	}
	
	public Set<Certificated> getMedicines(){
		return medicines;
	}
	
	@Override
	public void startDocument() {
		LOGGER.debug("SAX parser start parsing");
	}
	
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attrs) {
		if("certificated".equals(qName)) {
			current = new Certificated();
			current.setName(attrs.getValue(MedicineEnum.NAME.getValue()));
			current.setPharm(attrs.getValue(MedicineEnum.PHARM.getValue()));
			current.setGroup(attrs.getValue(MedicineEnum.GROUP.getValue()));
			if(attrs.getLength() == 4) {
				current.setValid(new BigInteger(attrs.getValue(MedicineEnum.VALID.getValue())));
			}
		}
		else {
			MedicineEnum tmp = MedicineEnum.getEnum(qName);
			if(withText.contains(tmp)){
				currEnum = tmp;
			}
		}
	}
	
	@Override
	public void characters(char[] ch, int start, int length) {
		String value = new String(ch, start, length);
		if(currEnum == null) {
			return;
		}
		switch(currEnum) {
			case ANALOGS:
				current.setAnalogs(value);
				break;
			case PRICE:
				current.setPrice(new Double(value).floatValue());
				break;
			case WRAPPED:
				try {
					current.setWrapped(DatatypeFactory.newInstance().newXMLGregorianCalendar(value));
				} catch (DatatypeConfigurationException e) {
					LOGGER.error(e.getMessage());
				}
				break;
			case FORM:
				current.setForm(value);
				break;
			case AMOUNT:
				current.getDosage().setAmount(new Double(value).floatValue());
				break;
			case FREQ:
				current.getDosage().setFrequency(new BigInteger(value));
				break;
			case PERIOD:
				current.getDosage().setPeriod(new BigInteger(value));
				break;
			case NUMBER:
				current.getCertificate().setNumber(value);
				break;
			case ISSUED:
				try {
					current.getCertificate().setIssued(DatatypeFactory.newInstance().newXMLGregorianCalendar(value));
				} catch (DatatypeConfigurationException e) {
					LOGGER.error(e.getMessage());
				}
				break;
			case UNTIL:
				try {
					current.getCertificate().setUntil(DatatypeFactory.newInstance().newXMLGregorianCalendar(value));
				} catch (DatatypeConfigurationException e) {
					LOGGER.error(e.getMessage());
				}
				break;
			case REG:
				current.getCertificate().setRegister(value);
				break;
			default:
				throw new EnumConstantNotPresentException(currEnum.getDeclaringClass(), currEnum.name());
		}
		currEnum = null;
	}
	
	@Override
	public void endElement(String uri, String localName, String qName) {
		if("certificated".equals(qName)) {
			medicines.add(current);
		}
	}
	
	@Override
	public void endDocument() {
		LOGGER.debug("SAX parser end parsing");
	}
	
}
