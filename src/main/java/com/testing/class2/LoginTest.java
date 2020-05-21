package com.testing.class2;

import com.testing.webKeyword.WebkeyWord;

public class LoginTest {
	/*
	 * 该类是：调用com.testing.webKeyword.WebkeyWord.java类中封装的关键字类，
	 * 打开谷歌浏览器，
	 * 输入url：http://www.testingedu.com.cn:8000/
	 * 获取商城首页的商品列表，并在控制台输出打印商品列表
	 * 然后调用封装的登陆方法
	 * 关闭浏览器
	 */

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WebkeyWord web=new WebkeyWord();
		web.openBrowser("chrome");
		web.visitWeb("http://www.testingedu.com.cn:8000/");
		//在控制台打印输出所有的商品列表中的内容
		web.getAllgoodsType("//div[@class='cata-nav-wrap']/a");
		shopLogin(web);
		web.closeBrowser();
	}

	public static void shopLogin(WebkeyWord web) {
		web.click("//a[text()='登录']");
		web.input("//input[@id='username']", "13800138006");
		web.input("//input[@id='password']", "123456");
		web.input("//input[@id='verify_code']", "1");
		web.click("//a[@name='sbtbutton']");
	}

}
