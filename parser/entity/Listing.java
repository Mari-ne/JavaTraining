package parser.entity;

import org.apache.log4j.Logger;

import parser.service.FileHandler;

import java.util.List;
import java.io.IOException;
import java.util.ArrayList;


/**
 * @author Admin
 *
 */
public class Listing implements TextFragment {
	
	private List<String> listing = new ArrayList<String>();
	
	private static final Logger LOGGER = Logger.getLogger(Listing.class);
	
	public Listing(List<String> listing) {
		this.listing.addAll(listing);
		LOGGER.debug("New object of class Listing was created.");
	}
	
	public List<String> getListing(){
		return listing;
	}
	
	public void setListing(List<String> newListing) {
		LOGGER.debug("Set new listing");
		listing.clear();
		listing = new ArrayList<String>();
		listing.addAll(newListing);
	}
	
	public static boolean isListing(String line) {
		if(line == null) {
			return false;
		}
		line = line.trim();
		if(line.length() == 0) {
			return false;
		}
		if(line.endsWith(";") || line.endsWith("{") || line.endsWith("}") || line.endsWith("*\\") ||
				line.startsWith("/*") || line.startsWith("*")) {
			return true;
		}
		return false;
	}
	
	@Override
	public void write(FileHandler file) throws IOException{
		for(String line : listing) {
			file.write(line + System.getProperty("line.separator"));
		}
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == this) {
			return true;
		}
		if(obj == null) {
			return false;
		}
		if(this.getClass() != obj.getClass()) {
			return false;
		}
		Listing tmp = (Listing)obj;
		if(listing.equals(tmp.listing)) {
			return true;
		}
		return false;
	}
	
	@Override
	public int hashCode(){
		int hash = 0;
		for(String line : listing) {
			hash += line.hashCode();
		}
		return hash * 31;
	}
	
	@Override
	public String toString() {
		LOGGER.info("aaaa\n");
		String string = getClass().getName() + "@";
		for(String line : listing) {
			string += line + "\n";
		}
		return string;
	}

}
