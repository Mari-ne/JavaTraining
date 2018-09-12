package com.epam.xml.parser;

import java.io.IOException;
import java.math.BigInteger;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.apache.log4j.Logger;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.epam.xml.medicine.Certificated;
import com.epam.xml.medicine.MedicineEnum;

public class MedicineDOM extends AbstractMedicineBuilder{
	private DocumentBuilder docBuilder;
	
	private static final Logger LOGGER = Logger.getRootLogger();
	
	public MedicineDOM() {
		super();
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			docBuilder = factory.newDocumentBuilder();
		}catch(ParserConfigurationException e) {
			LOGGER.error(e.getMessage());
		}
	}
	
	@Override
	public void buildSetMedicines(String fileName) {
		Document doc = null;
		try {
			doc = docBuilder.parse(fileName);
			Element root = doc.getDocumentElement();
			NodeList medicineList = root.getElementsByTagName("certificated");
			for(int i = 0; i < medicineList.getLength(); i ++) {
				Element medicineElem = 	(Element) medicineList.item(i);
				Certificated medicine = buildMedicine(medicineElem);
				medicines.add(medicine);
			}
		}catch(IOException e) {
			LOGGER.error(e.getMessage());
		}catch(SAXException e) {
			LOGGER.error(e.getMessage());
		}
	}
	
	private Certificated buildMedicine(Element medicineElem) {
		Certificated medicine = new Certificated();
		
		medicine.setName(medicineElem.getAttribute(MedicineEnum.NAME.getValue()));
		medicine.setPharm(medicineElem.getAttribute(MedicineEnum.PHARM.getValue()));
		medicine.setGroup(medicineElem.getAttribute(MedicineEnum.GROUP.getValue()));
		
		if(! medicineElem.getAttribute(MedicineEnum.VALID.getValue()).equals("")) {
			medicine.setValid(new BigInteger(medicineElem.getAttribute(MedicineEnum.VALID.getValue())));
		}
		
		medicine.setAnalogs(getElemContent(medicineElem, MedicineEnum.ANALOGS.getValue()));
		medicine.setPrice(new Double(getElemContent(medicineElem, MedicineEnum.PRICE.getValue())).floatValue());
		
		String value = getElemContent(medicineElem, MedicineEnum.WRAPPED.getValue());
		try {
			medicine.setWrapped(DatatypeFactory.newInstance().newXMLGregorianCalendar(value));
		} catch (DatatypeConfigurationException e) {
			LOGGER.error(e.getMessage());
		}
		
		medicine.setForm(getElemContent(medicineElem, MedicineEnum.FORM.getValue()));
		
		Element dosage = (Element) medicineElem.getElementsByTagName(MedicineEnum.DOSAGE.getValue()).item(0);
		medicine.getDosage().setAmount(new Double(getElemContent(dosage, MedicineEnum.AMOUNT.getValue())).floatValue());
		medicine.getDosage().setFrequency(new BigInteger(getElemContent(dosage, MedicineEnum.FREQ.getValue())));
		medicine.getDosage().setPeriod(new BigInteger(getElemContent(dosage, MedicineEnum.PERIOD.getValue())));
		
		Element certificate = (Element) medicineElem.getElementsByTagName(MedicineEnum.CERCIFICATE.getValue()).item(0);
		medicine.getCertificate().setNumber(getElemContent(certificate, MedicineEnum.NUMBER.getValue()));
		try {
			value = getElemContent(certificate, MedicineEnum.ISSUED.getValue());
			medicine.getCertificate().setIssued(DatatypeFactory.newInstance().newXMLGregorianCalendar(value));
			value = getElemContent(certificate, MedicineEnum.UNTIL.getValue());
			medicine.getCertificate().setUntil(DatatypeFactory.newInstance().newXMLGregorianCalendar(value));
		} catch (DatatypeConfigurationException e) {
			LOGGER.error(e.getMessage());
		}
		medicine.getCertificate().setRegister(getElemContent(certificate, MedicineEnum.REG.getValue()));
		
		return medicine;
	}
	
	private static String getElemContent(Element elem, String name) {
		NodeList list = elem.getElementsByTagName(name);
		Node node = list.item(0);
		return node.getTextContent();
	}
}
