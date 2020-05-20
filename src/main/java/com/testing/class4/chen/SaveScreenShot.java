package com.testing.class4.chen;

import com.testing.common.chen.AutoLogger;

import chen.test.webkeyword.WebKeyWord;

public class SaveScreenShot {
	public static void main(String[] args) {
		WebKeyWord web=new WebKeyWord();
		AutoLogger.log.info("+++++++++++测试开始++++++1++++++");
		web.openBrowser("chrome");
		web.visitWeb("http://www.baidu.com");
		web.saveScrShot("自己想截图");
		web.input("wd", "chen");
		web.halt("8");
		web.closeBrowser();
	}
}
