package com.testing.common;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AutoLogger {
/*
 * 该类是：自动记录器。（自动记录日志的方法）
 * 创建一个公开的、静态的 获取Logger的变量，以供对外调用
 */
	//定义静态变量方便调用。
	public static Logger log=LogManager.getLogger(AutoLogger.class);
	

}
