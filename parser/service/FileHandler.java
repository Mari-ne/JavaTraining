package parser.service;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import org.apache.log4j.Logger;

/**
 * @author Admin
 *
 */
public class FileHandler {
	private String filePath;
	
	private static final Logger LOGGER = Logger.getRootLogger();
		
	public FileHandler(String filePath) throws IOException, FileNotFoundException{
		this.filePath = filePath;
		File file = new File(filePath);
		if(file.length() == 0) {
			throw new IOException("Empty file.");
		}
	}
	
	public static void create(String filePath) throws IOException, FileAlreadyExistsException{
		Path path = Paths.get(filePath);
        Files.createDirectories(path.getParent());
        Files.createFile(path);
        byte b[] = {' '};
        Files.write(path, b, StandardOpenOption.APPEND);
  	}
	
	public String read() throws IOException {
		LOGGER.debug("Reading data from " + filePath);
		FileReader reader = new FileReader(filePath);
		StringBuilder string = new StringBuilder("");
		int data;	
		while((data = reader.read()) != -1) {
			string.append((char)data);
		}
		reader.close();
		return string.toString();
	}
		
	public void write(String s) throws IOException {
		LOGGER.debug("Writing data to " + filePath);
		FileWriter writer = new FileWriter(filePath, true);
		BufferedWriter bufWriter = new BufferedWriter(writer);
		bufWriter.write(s);
		bufWriter.close();
	}
}
