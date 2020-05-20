package com.testing.class5.chen;

import com.testing.class3.chen.ShopByTest;
import com.testing.class4.chen.ShopManagerTest;

import chen.test.webkeyword.WebKeyWord;

public class POBuyAndAdd {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//首先实例化关键字类，也就是所有的页面都要用的BasePage
		WebKeyWord web=new WebKeyWord();
		//所有页面都使用同一个浏览器
		web.openBrowser("chrome");
		
		String goodsName="第五课测试商品3";
		//测试商城后台登陆功能页面。
		ShopManagerTest.adminLogin(web);
		//测试商城后台添加商品功能。
		ShopManagerTest.addGoods(web, goodsName);
		//断言，添加商品是否成功。
		web.assertPageContains(goodsName);
		
		//测试前台页面登陆功能。
		ShopByTest.shopLogin(web);
		//测试前台页面中的购买测试
		ShopByTest.buyGoods(web);
		//断言 订单是否购买成功。
		web.assertEleContainsText("//div[@class='erhuh']/h3", "提交成功");
	}

}
