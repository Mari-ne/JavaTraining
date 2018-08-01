package parser.entity;

import org.apache.log4j.Logger;

import parser.service.FileHandler;

import java.util.List;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Admin
 *
 */
public class CompositeTextFragment implements TextFragment {
	private List<TextFragment> children = new ArrayList<>();
	
	private static final Logger LOGGER = Logger.getLogger(CompositeTextFragment.class);
	
	public void add(TextFragment child) {
		LOGGER.debug("Add new child.");
		children.add(child);
	}
	
	public void add(TextFragment... children) {
		LOGGER.debug("Add new children");
		this.children.addAll(Arrays.asList(children));
	}
	
	public List<TextFragment> getChildren(){
		return children;
	}
	
	public boolean remove(TextFragment fragment) {
		LOGGER.debug("Removing one occurrence of fragment."); //THINK HOW TO SHOW VALUE OF fragment
		if(children.contains(fragment)) {
			children.remove(fragment);
			return true;
		}
		return false;
	}
	
	/**Removes all occurrence of fragment
	 * @param fragment 
	 * Fragment of text, which need to delete
	 * @return true, if fragment was at least once
	 * 		   false otherwise.
	*/
	public boolean removeAll(TextFragment fragment) {
		LOGGER.debug("Removing all occurrence of fragment."); //THINK HOW TO SHOW VALUE OF fragment
		if(children.contains(fragment)) {
			while(children.contains(fragment)) {
				children.remove(fragment);
			}
			return true;
		}
		return false;
	}

	@Override
	public void write(FileHandler file) throws IOException{
		for(TextFragment fragment : children) {
			fragment.write(file);
		}
	}
}
