package service.logger;

import org.apache.log4j.Logger;

/**
 *
 * @author Admin
 */
public class InfoLogger {
    private Logger logger;
    
    public InfoLogger(Class c){
        logger = Logger.getLogger(c);
    }
    
    public void info(String s){
        logger.info(s);
    }
}
