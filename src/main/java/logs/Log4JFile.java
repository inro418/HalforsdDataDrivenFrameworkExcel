package logs;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log4JFile {
	
    private static Logger logger = LogManager.getLogger();
    
    public static void main (String[] args){
    	
    	System.out.println("\n Hello World........! \n");
    	
        logger.trace("This is a trace message");
        logger.info("This is an info message");
        logger.warn("This is a warning message");
        logger.error("This is an error message");
        logger.fatal("This is a fatal message");
        
        System.out.println("\n Completed");   
    }
}