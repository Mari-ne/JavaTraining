/**
 * 
 */
package parser.service.chain;

import org.apache.log4j.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import parser.entity.*;

/**
 * @author Admin
 *
 */
public class SentenceParser extends Parser {

	private static final String PATTERN = "([^.!?\\s]+)\\s*";
	
	private static final Logger LOGGER = Logger.getRootLogger();
	
	public SentenceParser() {}
	
	@Override
	public TextFragment parsing(String data) {
		LOGGER.debug("Sentence parsing.");
		CompositeTextFragment sentence = new CompositeTextFragment();
		
		Pattern expresion = Pattern.compile(PATTERN);
		Matcher match = expresion.matcher(data);
		
		while(match.find()) {
			sentence.add(new Leaf(match.group()));
			//LOGGER.info("\n!!!!!!!\n" + match.group() + "\n!!!!!!!!!!!!!\n");
		}
		
		return sentence;
	}

}
