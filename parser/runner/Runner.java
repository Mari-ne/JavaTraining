package parser.runner;

import org.apache.log4j.Logger;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import parser.entity.CompositeTextFragment;
import parser.service.FileHandler;
import parser.service.MainLogic;
import parser.service.chain.*;

public class Runner {

	private static final Logger LOGGER = Logger.getRootLogger();
	
	public static void main(String[] args) {
		
		LOGGER.debug("Start application.");
		Scanner input = new Scanner(System.in);
		String filePath = "";
		FileHandler file = null;
		while(true) {
			try{	
				LOGGER.info("Enter path to file: ");
				filePath = input.nextLine();
				if(filePath.equals("")) {
					//if user just pressed Enter
					LOGGER.error("Was entered empty string.");
					LOGGER.info("Please, enter path to file, which you will use!\n");
					continue;
				}
				//if user entered something (not necessary correct path)
				file = new FileHandler(filePath);
				break;
			}catch(FileNotFoundException e) {
				//this exception can throw constructor of FileReader
				LOGGER.info("File " + filePath + " doesn't exist. Please, enter path to file again.\n");
				LOGGER.error(e.getMessage());
			}catch(IOException e) {
				LOGGER.info("File " + filePath + " is empty. Please, enter path to file again.\n");
				LOGGER.error(e.getMessage());
			}
		}
		
		CompositeTextFragment text = new CompositeTextFragment();
		Parser parser = new ListingParser();
		parser.linkWith(new ParagraphParser()).linkWith(new SentenceParser());
		try {
			String data = file.read();
			text = (CompositeTextFragment) parser.parsing(data);
		} catch (IOException e) {
			LOGGER.info(e.getMessage());
		}
		
		while(true) {
			try{
				LOGGER.info("Enter path to save file: ");
				filePath = input.nextLine();
				if(filePath.equals("")) {
					//if user just pressed Enter
					LOGGER.error("Was entered empty string.");
					LOGGER.info("Please, enter path to file, which you will use!\n");
					continue;
				}
				//if user entered something (not necessary correct path)
				FileHandler.create(filePath);
				file = new FileHandler(filePath);
				break;
			}catch(FileAlreadyExistsException e) {
				//this exception can throw constructor of FileReader
				LOGGER.info("File " + filePath + " exist. Please, enter path to file again.\n");				
				LOGGER.error(e.getMessage());
			}catch(Exception e) {
				LOGGER.error(e.getMessage());
				LOGGER.info(e.getMessage());
			}
		}
		
		try {
			text.write(file);
		} catch (IOException e) {
			LOGGER.info(e.getMessage());
		}
		
		
		int size = 0;
		while(true) {
			try{
				LOGGER.info("Enter size of words: ");
				size = input.nextInt();
				break;
			}catch(InputMismatchException e) {
				//this exception can throw constructor of FileReader
				LOGGER.info("You should enter only numbers!\n");				
				LOGGER.error(e.getMessage());
			}
		}
		
		List<String> result = MainLogic.wordSearch(text, size);
		if(result.isEmpty()) {
			LOGGER.info("Text hasn't words in qestion sentences with length " + size);
		}
		else {
			LOGGER.info("Text has " + result.size() + " words with size of ");
			LOGGER.info(size + " letters in question sentenses\n");
			for(String s : result) {
				LOGGER.info(s + "\n");
			}
		}
		
		input.close();
		LOGGER.debug("Finish application.");

	}

}
