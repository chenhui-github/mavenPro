package com.testing.class3.chen;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import chen.test.webkeyword.WebKeyWord;

public class ShowByTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WebKeyWord web=new WebKeyWord();
		//选择谷歌浏览器
		web.openBrowser("chrome");
		//打开网址
		web.visitWeb("http://testingedu.com.cn:8000");
		//登陆
		web.shopLogin("791077118@qq.com", "123456");
		//点击返回商城首页
		web.click("//a[text()='返回商城首页']");
		//强制等待1秒钟
		web.halt("1");
		//鼠标悬停到‘全部商品分类’
		web.hover("//a[text()='全部商品分类']");
		//强制等待1秒钟
		web.halt("1");
		//鼠标悬停到‘手机数码’
		web.hover("//a[text()='手机数码']");
		//点击手机
		web.click("//a[text()='手机' and not(@class)]");
		//切换窗口
		web.switchWindowByTitile("商品列表");
		//点击第二个商品
		web.click("//div[@class='shop-list-splb p']/ul/li[2]//div[@class='shop_name2");
		//点击 加入购物车
		web.click("//a[@id='join_cart']");
		//点击去购物车结算
		web.click("//a[text()='去购物车结算']");
	}



}
