package com.epam.xml.medicine;

public enum MedicineEnum {
	MEDICINES("medicines"),
	NAME("name"),
	PHARM("pharm"),
	GROUP("group"),
	VALID("valid"),
	CERTIFICATED("certificated"),
	ANALOGS("analogs"),
	PRICE("price"),
	WRAPPED("wrapped"),
	FORM("form"),
	AMOUNT("amount"),
	FREQ("frequency"),
	PERIOD("period"),
	NUMBER("number"),
	ISSUED("issued"),
	UNTIL("until"),
	REG("register"),
	DOSAGE("dosage"),
	CERCIFICATE("certificate");
	
	private String value;
	
	private MedicineEnum(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
	
	public static MedicineEnum getEnum(String value) {
		for(MedicineEnum elem : MedicineEnum.values()) {
			if(elem.getValue().equals(value)) {
				return elem;
			}
		}
		return null;
	}
}
