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
		List<String> sizeWords = new ArrayList<>();
		//List<TextFragment> fragments = text.getChildren();
		for(TextFragment fragment : text.getChildren()) {
			if(fragment instanceof Listing || fragment instanceof PunctuationMark) {
				//if fragment is listing it can't have question sentences
				continue;
			}
			CompositeTextFragment paragraph = (CompositeTextFragment)fragment;
			List<TextFragment> sentences = paragraph.getChildren();
			for(int i = 1; i < sentences.size(); i += 2) {
				//every second element in paragraph - mark of end of sentence
				if(((PunctuationMark)sentences.get(i)).isQestion()) {
					CompositeTextFragment words = (CompositeTextFragment)sentences.get(i -1);
					for(TextFragment word : words.getChildren()) {
						Word w = (Word)word;
						if(w.size() == size && !sizeWords.contains(w.getWord())) {
							LOGGER.debug("Add new suit word - " + w.getWord());
							sizeWords.add(w.getWord());
						}
					}
				}
			}
		}
		
		return sizeWords;
	}
}
