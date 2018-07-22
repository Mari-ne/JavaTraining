package service.logger;

import org.apache.log4j.Logger;

/**
 *
 * @author Admin
 */
public class ErrorLogger {
    private Logger logger;
    
    public ErrorLogger(Class c){
        logger = Logger.getLogger(c);
    }
    
    public Logger getLogger(){
        return logger;
    }
}
