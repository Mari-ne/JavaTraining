package parser.entity;

import java.io.IOException;

import org.apache.log4j.Logger;

import parser.service.FileHandler;

/**
 * @author Admin
 *
 */

public class Word implements TextFragment {
	
	private String word;
	
	private static final Logger LOGGER = Logger.getLogger(Word.class);
	
	public Word(String word) {
		this.word = word;
		LOGGER.debug("New object of class Word was created.");
		
	}
	
	public String getWord() {
		return word;
	}
	
	public void setWord(String newWord) {
		LOGGER.debug("Set word: " + word + " -> " + newWord);		
		word = newWord;
	}
	
	public int size() {
		return word.length();
	}
	
	public static boolean isWord(String word) {
		if(word == null) {
			return false;
		}
		word = word.trim();
		if(word.length() == 0) {
			return false;
		}
		if(word.indexOf(" ") != -1) {
			return false;
		}
		return true;
	}
	
	@Override
	public void write(FileHandler file) throws IOException {
		file.write(" " + word);
	}
	
	@Override
	public boolean equals(Object obj) {
		if(this == obj) {
			return true;
		}
		if(obj == null) {
			return false;
		}
		if(this.getClass() != obj.getClass()) {
			return false;
		}
		Word tmp = (Word)obj;
		if(word.equals(tmp.word)) {
			return true;
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return word.hashCode() * 31;
	}
	
	@Override
	public String toString() {
		return getClass().getName() + "@" + word;
	}

}
