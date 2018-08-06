package parser.service.chain;

import parser.entity.TextFragment;
import parser.entity.Leaf;

/**
 * @author Admin
 *
 */
public abstract class Parser {

	private Parser next;
	
	public Parser linkWith(Parser next) {
		this.next = next;
		return next;
	}
	
	public abstract TextFragment parsing(String data);
	
	protected TextFragment parseNext(String data) {
		if(next == null) {
			return new Leaf(data);
		}
		return next.parsing(data);
	}
	
}
