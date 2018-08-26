package xml.service;

import org.testng.annotations.Test;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.Assert;

import java.util.HashSet;
import java.util.Set;

import medicine.Certificate;
import medicine.Certificated;
import medicine.Dosage;

public class XMLParserTest {
  
	private MedicineBuildFactory factory = new MedicineBuildFactory();
	private AbstractMedicineBuilder builder;
	
	private Set<Certificated> expected;
	
	@BeforeClass
	public void expectedSetUp() {
		expected = new HashSet<Certificated>();
		expected.add(new Certificated("Novonorm", "Novo Nordisk", "chemicals", "60",
				"-", 30.10f, "2015-11-30T11:37:00", "pills", new Dosage(0.5f, "1", "14"),
				new Certificate("M-3155", "2012-05-25", "2022-05-25", "EMA")));
		expected.add(new Certificated("Cardiomagnil", "Takeda", "chemicals", "36",
				"-", 25.0f, "2018-03-13T11:37:00", "pills", new Dosage(1.0f, "1", "10"),
				new Certificate("M-913", "2010-05-25", "2030-05-25", "EMA")));
		expected.add(new Certificated("Calcium-D3 Nikomed", "Takeda", "vitamins", "30",
				"-", 23.30f, "2018-03-07T16:33:00", "pills", new Dosage(1.0f, "2", "30"),
				new Certificate("M-9643", "2010-05-25", "2030-05-25", "EMA")));
		expected.add(new Certificated("Leptaden", "Alarsin", "chemicals", "36",
				"-", 16.90f, "2017-10-30T17:41:00", "pills", new Dosage(2.0f, "2", "30"),
				new Certificate("M-808", "2009-01-15", "2019-01-15", "EMA")));
		expected.add(new Certificated("Fortege", "Alarsin", "chemicals", "36",
				"-", 14.55f, "2018-02-27T19:11:00", "pills", new Dosage(2.0f, "2", "30"),
				new Certificate("M-825", "2009-01-15", "2019-01-15", "EMA")));
		expected.add(new Certificated("Miron", "Alarsin", "chemicals", "60",
				"-", 13.15f, "2016-12-27T19:11:00", "pills", new Dosage(2.0f, "2", "30"),
				new Certificate("M-8265", "2009-01-15", "2019-01-15", "EMA")));
		expected.add(new Certificated("Roztiran", "Vishpha", "chemicals", "36",
				"-", 13.15f, "2017-12-12T10:30:00", "salve", new Dosage(20.0f, "4", "3"),
				new Certificate("M-775", "2017-02-20", "2027-02-20", "EMA")));
		expected.add(new Certificated("Gederin", "Vishpha", "chemicals", "60",
				"-", 11.10f, "2017-12-22T09:38:00", "syrup", new Dosage(7.5f, "3", "7"),
				new Certificate("M-7856", "2012-02-29", "2024-02-29", "EMA")));
		expected.add(new Certificated("Bi-tol", "Vishpha", "chemicals", "36",
				"-", 15.0f, "2017-11-11T07:12:00", "syrup", new Dosage(20.0f, "2", "10"),
				new Certificate("M-712", "2012-02-29", "2024-02-29", "EMA")));
		expected.add(new Certificated("Hartil", "Egis", "chemicals", "24",
				"-", 20.0f, "2018-05-09T11:21:00", "pills", new Dosage(1.0f, "2", "21"),
				new Certificate("M-4591", "2013-01-30", "2023-01-30", "EMA")));
		expected.add(new Certificated("Suprastin", "Egis", "chemicals", "60",
				"-", 17.50f, "2018-06-23T10:59:00", "pills", new Dosage(1.5f, "3", "14"),
				new Certificate("M-431", "2018-01-21", "2028-01-21", "EMA")));
		expected.add(new Certificated("Celebreks", "Pfizer", "chemicals", "36",
				"-", 10.15f, "2018-08-07T17:19:00", "capsule", new Dosage(1.0f, "2", "3"),
				new Certificate("M-2761", "2012-05-15", "2022-05-15", "EMA")));
		expected.add(new Certificated("Akkuzid", "Pfizer", "chemicals",
				"-", 15.45f, "2018-07-12T13:09:00", "pills", new Dosage(1.0f, "1", "5"),
				new Certificate("M-2871", "2010-05-13", "2020-05-13", "EMA")));
		expected.add(new Certificated("Cycledinon", "Pfizer", "homeopatic", "24",
				"-", 11.30f, "2018-04-10T12:42:00", "pills", new Dosage(1f, "1", "180"),
				new Certificate("M-2113", "2005-02-19", "2025-02-19", "EMA")));
		expected.add(new Certificated("Bepanten", "Bayer", "chemicals", 
				"Avelocks", 10.9f, "2018-02-18T18:09:00", "pills", new Dosage(3.0f, "1", "7"), 
				new Certificate("M-1321", "2010-08-14", "2020-08-14", "EMA")));
		expected.add(new Certificated("Avelocks", "Bayer", "chemicals", "10", 
				"Bepanten", 12.9f, "2018-01-10T15:42:00", "pills", new Dosage(2.0f, "2", "7"), 
				new Certificate("M-123", "2012-12-10", "2022-12-10", "EMA")));
	}
	
	@Test
	public void SAXParserTest() {
		builder = factory.createBuilder("sax");
		builder.buildSetMedicines("xml\\medicine.xml");
		Set<Certificated> actual = builder.getMedicines();
		Assert.assertEquals(actual, expected);
	}
	
	@Test
	public void DOMParserTest() {
		builder = factory.createBuilder("dom");
		builder.buildSetMedicines("xml\\medicine.xml");
		Set<Certificated> actual = builder.getMedicines();
		Assert.assertEquals(actual, expected);
	}
	
	@Test
	public void StAXParserTest() {
		builder = factory.createBuilder("stax");
		builder.buildSetMedicines("xml\\medicine.xml");
		Set<Certificated> actual = builder.getMedicines();
		Assert.assertEquals(actual, expected);
	}
	
	@AfterMethod
	public void tearDown() {
		  builder = null;
	}

	@AfterClass
	public void tearDownExpected() {
		expected.clear();
	}
	
}
