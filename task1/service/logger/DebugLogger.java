package service.logger;

import org.apache.log4j.Logger;

/**
 *
 * @author Admin
 */
public class DebugLogger {
    private Logger logger;
    
    public DebugLogger(Class c){
        logger = Logger.getLogger(c);
    }
    
    public Logger getLogger(){
        return logger;
    }
}
