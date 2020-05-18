package chen.test.webkeyword;

public class DemoWithKeyWord {
	public static void main(String[] args) {
		WebKeyWord ww=new WebKeyWord();
//		ww.openBrowser("ie");//打开ie浏览器
//		ww.openBrowser("chrome");//打开谷歌浏览器
		ww.openBrowser("firefox");//打开火狐浏览器
		ww.visitWeb("http://www.baidu.com");
		System.out.println("titile1---"+ww.getTitle());
		ww.inputAndSubmitByName("s", "cheese");
		//显式等待
		ww.explicitlyWaitTitle();
		System.out.println("titile2---"+ww.getTitle());
		ww.closeBrowser();
	}
	
}
