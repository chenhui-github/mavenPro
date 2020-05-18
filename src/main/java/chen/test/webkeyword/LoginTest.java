package chen.test.webkeyword;
//该类是一个使用 selenium写的自动化测试 登陆 的demo
public class LoginTest {
	public static void main(String[] args) {
		WebKeyWord web=new WebKeyWord();
		//打开谷歌浏览器
		web.openBrowser("chrome");
		//打开目标网站
		web.visitWeb("testingedu.com.cn:8000");
		//获取货架类别(并输出到控制台)
//		web.getAllgoodsType("//div[@class='cata-nav-wrap']/a");

		//点击登陆的超链接位置
		web.shopLogin("791077118@qq.com","123456");
		
		//点击  首页
		web.click("//a[@href='/Home/Index/index.html']");
		//手机数码
		web.click("//a[text()='手机数码']");
		//手机数码->手机
//		web.click("//a[@href='/Home/Goods/goodsList/id/62.html']");
		//关闭浏览器 已经驱动进程
		web.closeBrowser();
	}

	
}
