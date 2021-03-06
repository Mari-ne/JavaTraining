package parser.entity;

import java.io.IOException;

import org.apache.log4j.Logger;


import parser.service.FileHandler;

/**
 * @author Admin
 *
 */
public class Leaf implements TextFragment {

	private String data;
	
	private static final Logger LOGGER = Logger.getRootLogger();
	
	public Leaf(String data) {
		this.data = data;
		LOGGER.debug("Create new Leaf object");
	}
	
	public String getData() {
		return data;
	}
	
	public void setData(String newData) {
		data = newData;
	}
	
	@Override
	public void write(FileHandler file) throws IOException {
		file.write(data);
	}
	
	@Override
	public boolean equals(Object obj) {
		if(this == obj) {
			return true;
		}
		if(obj == null) {
			return false;
		}
		if(obj.getClass() != getClass()) {
			return false;
		}
		Leaf tmp = (Leaf)obj;
		if(data.equals(tmp.data)) {
			return true;
		}
		return false;
		
	}
	
	@Override
	public int hashCode() {
		return data.hashCode() * 31;
	}
	
	@Override
	public String toString() {
		return getClass().getName() + "@" + data;
	}
}
