package parser.entity;

import java.io.IOException;
import java.security.InvalidParameterException;

import parser.service.FileHandler;

/**
 * @author Admin
 *
 */
public enum PunctuationMark implements TextFragment{
	POINT('.'),
	DOUBLEPOINT(':'),
	POINTCOMMS(';'),
	COMMA(','),
	QESTION('?'),
	EXCLAMATION('!'),
	PARAGRAPH('\n');
	
	private char representation;
	
	private PunctuationMark(char representation) {
		this.representation = representation;
	}
	
	public char getRepresentation() {
		return representation;
	}
	
	public boolean isEndOfSentence() {
		switch(this) {
		case POINT:
		case QESTION:
		case EXCLAMATION:
			return true;
		default:
			return false;
		}
	}
	
	public boolean isQestion() {
		if(this == QESTION) {
			return true;
		}
		return false;
	}
	
	public static PunctuationMark getMark(char mark) throws InvalidParameterException{
		switch(mark) {
		case '.':
			return POINT;
		case ':':
			return DOUBLEPOINT;
		case ';':
			return POINTCOMMS;
		case ',':
			return COMMA;
		case '?':
			return QESTION;
		case '!':
			return EXCLAMATION;
		default:
			throw new InvalidParameterException();
		}
	}
	
	@Override
	public void write(FileHandler file) throws IOException{
		if(this != PARAGRAPH) {
			file.write(representation);
		}
		else {
			file.write(System.getProperty("line.separator"));
		}
	}

}
