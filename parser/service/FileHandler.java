package parser.service;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
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
	private File file;
	
	private static final Logger LOGGER = Logger.getRootLogger();
		
	public FileHandler(String filePath) throws IOException, FileNotFoundException{
		file = new File(filePath);
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
		LOGGER.debug("Reading data from " + file.getPath().toString());
		byte[] fileData = Files.readAllBytes(file.toPath());
		return new String(fileData, Charset.defaultCharset());
	}
		
	public void write(String s) throws IOException {
		Files.write(file.toPath(), s.getBytes(), StandardOpenOption.APPEND);
	}
	
	public void write(char c) throws IOException {
		byte[] data = {(byte)c};
		Files.write(file.toPath(), data, StandardOpenOption.APPEND);
	}
}
