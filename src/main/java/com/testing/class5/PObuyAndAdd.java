package com.testing.class5;

import com.testing.class3.ShopBuyTest;
import com.testing.class4.ShopManagerTest;
import com.testing.webKeyword.WebkeyWord;

public class PObuyAndAdd {
	/*
	 * 该类是：采用PageObject模式。
	 * 调用com.testing.webKeyword.WebkeyWord类中封装好的关键字方法。
	 * 打开同一个浏览器（谷歌浏览器）
	 * 先登录商城后台，商城后台添加商品，断言：添加商品是否成功。
	 * 再登陆商城前台，商城前台购买商品，断言：订单是否购买成功。
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//首先实例化关键字类，也就是所有的页面都要用的BasePage
		WebkeyWord web=new WebkeyWord();
		String goodsName="第五课的测试商品2";
		//所有页面都使用同一个浏览器
		web.openBrowser("chrome");
		//测试后台登录功能
		ShopManagerTest.adminLogin(web);
		//测试后台添加商品功能页面。
		ShopManagerTest.addGoods(web, goodsName);
		//断言添加商品是否成功
		web.assertPageContains(goodsName);
		//测试前台页面购买。
		ShopBuyTest.shopLogin(web);
		//前台页面中的购买测试
		ShopBuyTest.buyGoods(web);
		//断言购买是否成功
		web.assertEleContainsText("//div[@class='erhuh']/h3", "提交成功");
		web.closeBrowser();
		
	}

}
