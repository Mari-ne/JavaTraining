/**
 * 
 */
package parser.service;

import org.apache.log4j.Logger;
import java.util.List;
import java.util.ArrayList;

import parser.entity.*;

/**
 * @author Admin
 *
 */
public class Extruder {

	private static final Logger LOGGER = Logger.getRootLogger();
	
	public static TextFragment divider(String data) {
		if(Word.isWord(data)) {
			//data is word
			return new Word(data);
		}

		CompositeTextFragment fragment = new CompositeTextFragment();
		if(data.indexOf('\n') != -1) {
			LOGGER.debug("Text analyze.");
			// data is full text
			List<String> listing = new ArrayList<>();
			for(String part : data.split("\n")) {
				part = part.trim();
				if(Listing.isListing(part)) {
					listing.add(part);
				}
				else {
					if(!listing.isEmpty()) {
						//listing has some data
						fragment.add(new Listing(listing));
						listing.clear();
					}
					fragment.add(divider(part));
					fragment.add(PunctuationMark.PARAGRAPH);
				}
			}
		}
		else{
			if(data.indexOf('.') != -1 || 
					data.indexOf('!') != -1 ||
					data.indexOf('?') != -1) {
				LOGGER.debug("Paragraph analyze.");
				//data is paragraph
				char end;
				for(String pointSentence : data.split("\\.")) {
					for(String questionSentence : pointSentence.split("\\?")) {
						for(String sentence : questionSentence.split("!")) {
							int i = data.indexOf(sentence) + sentence.length();
							end = data.charAt(i);
							sentence = sentence.trim();
							fragment.add(divider(sentence));
							fragment.add(PunctuationMark.getMark(end)); //add punctuation mark that ends sentence
						}
					}
				}
			}
			else {
				//data is sentence
				for(String part : data.split(" ")) {
					LOGGER.debug("Sentence analyze.");
					fragment.add(divider(part));
				}
			}
		}
		return fragment;
	}
	
}
