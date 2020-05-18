package chen.test.webkeyword;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import chen.test.driverself.FirefDriver;
import chen.test.driverself.GoogleDriver;
import chen.test.driverself.IeDriver;

public class WebKeyWord {
	
	public WebDriver driver=null;
	//该方法是根据 浏览器的类型（ie、chrome、firefox），打开不同的浏览器
	public void openBrowser(String type) {
		switch(type) {
		case "ie":
			//创建chen.test.driverself.IeDriver.java类的实例
			IeDriver ie=new IeDriver("webDrivers/IEDriverServer.exe");
			//通过IeDriver.java类获取driver实例
			 driver=ie.getDriver();
			 //设置隐式等待，10秒钟
			 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			 break;
		case "chrome":
			//创建chen.test.driverself.GoogleDriver.java类的实例
			GoogleDriver gd=new GoogleDriver("webDrivers/chromedriver.exe");
			driver=gd.getDriver();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			break;
		case "firefox":
			//创建chen.test.driverself.FirefDriver.java类的实例
			FirefDriver ff=new FirefDriver("", "webDrivers/geckodriver.exe");
			driver=ff.getDriver();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			break;
		default:// 默认输入时用chrome进行启动
			//创建chen.test.driverself.GoogleDriver.java类的实例
			GoogleDriver gdd=new GoogleDriver("webDrivers/chromedriver.exe");
			driver=gdd.getDriver();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			break;
		}
	}
	
	
	//打开一个url地址
	public void visitWeb(String url) {
		try {
			driver.get(url);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//基于name属性定位元素，并输入内容，提交
	public void inputAndSubmitByName(String NameAtrr,String InputContent) {
		
		try {
			WebElement el=driver.findElement(By.name(NameAtrr));
			el.sendKeys(InputContent);
			el.submit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//点击 "登陆" 的超链接
	public void click(String xpath) {
		
		try {
			driver.findElement(By.xpath(xpath)).click();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//输入框  的方法（例如：获取登陆用户名、密码、验证码的输入框）
	public void  input(String xpath,String content) {
		WebElement le=driver.findElement(By.xpath(xpath)) ;
		//清理输入框
		le.clear();
		le.sendKeys(content);
	}
	//获取货架上面的所有商品类型
	/*
	 * 通过findelements 方法定位符合条件的所有元素，获取其文本内容。
	 */
	public void getAllgoodsType(String xpath) {
		List<WebElement> listGoods=driver.findElements(By.xpath(xpath)) ;
		for(WebElement e:listGoods) {
			System.out.println(e.getText());
		}
	}
	
	//获取网页标题
	public String getTitle() {
		try {
			return driver.getTitle();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "获取标题失败";
		}
	}
	
	//显式等待explicitly,等待网页标题以 小写 cheese开头
	public void explicitlyWaitTitle() {
		try {
			// 设定等待的事件，多少秒会超时。这里是10秒
			WebDriverWait eWait=new WebDriverWait(driver, 10);
			eWait.until(
					new ExpectedCondition<Boolean>() {
						public Boolean apply(WebDriver d) {
							return d.getTitle().toLowerCase().startsWith("cheese");
						}
					});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	//显示等待，等待元素表达式可以在页面定位到该元素(不常用，一般不与隐式等待同时使用)
	public void explicitlyWaitEle(String xpathExp) {
		try {
			WebDriverWait eWait=new WebDriverWait(driver,10);
			eWait.until(new ExpectedCondition<WebElement>() {
				public WebElement apply(WebDriver d) {
					return d.findElement(By.xpath(xpathExp));
				}
			});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//显示等待，等待条件是指定的元素定位方法能够定位到的元素，出现了
	//显式等待，还可以使用selenium已经预定义好的一些等待条件。 
	//该方法比较常用，足够使用
	public void explicitlyWaitEleLoc(String xpathExp) {
		try {
			WebDriverWait ewait =new WebDriverWait(driver, 10);
			ewait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpathExp)));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//强制等待 
	public void halt(String waitTime) {
		int time=Integer.parseInt(waitTime);
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//关闭整个浏览器以及driver的进程
	public void closeBrowser() {
		
		try {
			driver.quit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//封装一个基于输入的用户名、密码完成商城登陆的方法
	public  void shopLogin(String username,String password) {
		click("//a[text()='登录']");
		//输入
		input("//input[@id='username']", username);
		input("//input[@id='password']", password);
		input("//input[@id='verify_code']", "1");//需要开发那边配合一下，使用万能验证码(随便输都可以通过)
		//点击登陆  按钮
		click("//a[@name='sbtbutton']");
	}
	//将鼠标移动到某个元素上悬停
	public void hover(String xPath) {
		try {
			Actions act=new Actions(driver);
			//记得在act方法编辑完之后，加上.perform()方法调用，让动作执行
			act.moveToElement(driver.findElement(By.xpath(xPath))).perform();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*
	 * 切换浏览器窗口的方法
	 * @param exTitle 预期的浏览器窗口标题
	 */
	public void switchWindowByTitile(String exTitle) {
		Set<String> handles=driver.getWindowHandles();
		System.out.println(handles);
		String targetHandle="";
		//遍历所有的句柄，判断这个句柄对应的浏览器窗口标题是否是预期值
		for(String s:handles) {
			//切换到各个窗口句柄，获取其标题，判断是否等于预期值
			if(driver.switchTo().window(s).getTitle().equals(exTitle)) {
				//如果是预期值，则说明找到了需要切换窗口的句柄
				targetHandle=s;
				break;
			}
		}
		
//切换到找到的目标句柄中
//		driver.switchTo().window(targetHandle);
	}
	
	
	//切换窗口的新版本方法//该方法用不了
	public void switchByName(String exTitile) {
		driver.switchTo().window(exTitile);
	}	
		
	//定位Iframe一般使用id或者是name属性来定位
	public void switchIframe(String FrameName) {
		try {
			driver.switchTo().frame(FrameName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//如果Iframe没有id、name属性，需要使用元素通过xpath来定位
	public void switchIframeAsEle(String xPath) {
		try {
			WebElement frameElemen=driver.findElement(By.xpath(xPath));
			driver.switchTo().frame(frameElemen);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//断言：基于获取到的元素的文本内容，判断用例执行是否成功
	public void assertEleContainsText(String xPath,String exText) {
		String eleText=driver.findElement(By.xpath(xPath)).getText();
		System.out.println("断言元素内容是："+eleText);
		if(eleText.contains(exText)) {
			System.out.println("测试成功");
		}
		else {
			System.out.println("测试失败");
		}
	}
	
}
