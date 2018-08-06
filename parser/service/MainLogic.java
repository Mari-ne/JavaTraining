package parser.service;

import java.util.List;
import org.apache.log4j.Logger;
import java.util.ArrayList;

import parser.entity.*;


/**
 * @author Admin
 *
 */
public class MainLogic {
	
	private static final Logger LOGGER = Logger.getRootLogger();

	public static List<String> wordSearch (CompositeTextFragment text, int size){
		LOGGER.debug("Search of words with length " + size + " in text.");
		List<String> sizeWords = new ArrayList<>();
		for(TextFragment paragraph : text.getChildren()) {
			if(paragraph instanceof Leaf) {
				//listing & end of paragraph
				continue;
			}
			List<TextFragment> parag = ((CompositeTextFragment)paragraph).getChildren();
			for(int i = 1; i < parag.size(); i +=2) {
				Leaf endMark = (Leaf)parag.get(i);
				if(endMark.getData().equals("?")) {
					for(TextFragment word : ((CompositeTextFragment)parag.get(i -1)).getChildren()) {
						Leaf w = (Leaf)word;
						if(w.getData().trim().length() == size && !sizeWords.contains(w.getData())) {
							sizeWords.add(w.getData());
						}
					}
				}
			}
		}
		return sizeWords;
	}
}
