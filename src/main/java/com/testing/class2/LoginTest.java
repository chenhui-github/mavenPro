package com.testing.class2;

import com.testing.webkeyword.WebkeyWord;

public class LoginTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WebkeyWord web=new WebkeyWord();
		web.openBrowser("chrome");
		web.visitWeb("http://www.testingedu.com.cn:8000/");
		//输出所有的商品列表中的内容
		web.getAllgoodsType("//div[@class='cata-nav-wrap']/a");
		web.click("//a[text()='登录']");
		web.input("//input[@id='username']", "13800138006");
		web.input("//input[@id='password']", "123456");
		web.input("//input[@id='verify_code']", "1");
		web.click("//a[@name='sbtbutton']");
		web.closeBrowser();
	}

}
