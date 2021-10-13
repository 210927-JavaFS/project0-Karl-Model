package com.revature;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

public class Driver {
	
	// start: excerpt from revature demo: HelloLogger
	private static Logger log = LoggerFactory.getLogger(Driver.class);
	// end: excerpt from revature demo: HelloLogger
	
	public static void main(String[] args) {
		
		// start: excerpt from revature demo: HelloLogger
		
		log.debug("Fun Times are about to start!");
		
		MDC.put("Tim", "1");
		
		log.info("This is a really awesome application");
		
		log.error("OH NO! Is something going wrong?");
		
		log.warn("Program ending");
		
		// end: excerpt from revature demo: HelloLogger
		
	}

}
