package com.epam.xml.parser;

import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;

import com.epam.xml.medicine.Certificated;

public abstract class AbstractMedicineBuilder {
	protected Set<Certificated> medicines;
	
	private static final Logger LOGGER = Logger.getRootLogger();
	
	public AbstractMedicineBuilder() {
		medicines = new HashSet<Certificated>();
	}
	
	public Set<Certificated> getMedicines(){
		return medicines;
	}
	
	public abstract void buildSetMedicines(String fileName);
}
