package parser.entity;

import java.io.IOException;

import parser.service.FileHandler;

public interface TextFragment {
	
	public void write(FileHandler file) throws IOException;
}
