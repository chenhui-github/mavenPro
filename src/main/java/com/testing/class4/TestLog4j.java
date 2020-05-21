package com.testing.class4;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TestLog4j {
/*
 * 该类是：创建org.apache.logging.log4j.LogManager类，
 * 然后调用该类LogManager中的方法，由弱到强依次是：
 * trace：是指【 跟踪级别】
 * debug：是指【调试级别】
 * info：是指【信息级别】
 * error：是指【错误级别】
 * fatal：是指【致命级别】
 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//创建log4j的logger对象
		 Logger logger = LogManager.getLogger();

		 	
		 	logger.trace("This is trace");
		    // This request will be disabled since Level.DEBUG < Level.INFO.
		    logger.debug("This is debug.");

		    // These requests will be enabled.
		    logger.info("This is an info.");
		    logger.info("----------------------------测试开始---------------------");
		    logger.warn("This is a warning.");
		    try {
				Integer.parseInt("ss");
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
//				e.printStackTrace();
				logger.error(e,e.fillInStackTrace());
			}
			
		    logger.fatal("This is a fatal error.");
		    return;
	}

}
