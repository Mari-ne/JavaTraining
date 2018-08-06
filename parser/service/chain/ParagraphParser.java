package parser.service.chain;

import org.apache.log4j.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import parser.entity.*;

/**
 * @author Admin
 *
 */
public class ParagraphParser extends Parser {

	private static final String PATTERN = "(([^.!?]+)([.!?:\\s]))";
	private static final Logger LOGGER = Logger.getRootLogger();
	
	public ParagraphParser() {}
	
	@Override
	public TextFragment parsing(String data) {
		LOGGER.debug("Paragraph parsing.");
		CompositeTextFragment text = new CompositeTextFragment();
		Pattern expression = Pattern.compile(PATTERN);
		Matcher match = expression.matcher(data);
		while(match.find()) {
			//LOGGER.info("\n????????\n" + match.group() + "\n?????????????\n");
			text.add(parseNext(match.group(2)));
			text.add(new Leaf(match.group(3)));
		}
		
		return text;
	}

}
