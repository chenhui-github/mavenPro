package com.testing.webkeyword;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.testing.driverself.GoogleDriver;
import com.testing.driverself.IEDriver;

public class WebkeyWord {

	// 成员变量webdriver，让每一个方法需要使用时都用同一个driver对象来进行操作，都在同一个浏览器中进行操作。
	// 避免外部程序对driver进行修改，可以声明为私有对象。
	public  WebDriver driver = null;
//	private  WebDriver driver = null;

	/**
	 * 不同的浏览器类型都需要完成driver的实例化操作，因此在封装浏览器启动方法时，可以用一个变量来进行浏览器driver对象实例化的类型选择
	 * 每次执行自动化的时候，必然首先要完成openBrowser方法的调用。
	 * 
	 * @param type 浏览器的类型
	 */
	public void openBrowser(String type) {
		switch (type) {
		case "ie":
			IEDriver ie = new IEDriver("webDrivers/IEDriverServer.exe");
			// 注意对成员变量进行实例化操作，之后的方法才可以复用这个实例化好的浏览器
			// 不要再声明一个webdriver局部变量。
			driver = ie.getdriver();
			//隐式等待，设定最长的等待时间为10秒，在10秒内如果进行driver.findelement操作，则会等待元素能够被定位，如果10秒内能被定位了，继续下一步操作
			//如果超过10秒，超时报错。
			//隐式等待的缺点，不能定义更多的条件
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			break;
		case "chrome":
			GoogleDriver gg = new GoogleDriver("webDrivers/chromedriver.exe");
			driver = gg.getdriver();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			break;
		// 使用自己封装的driver类和使用selenium原始的实例化方法，等价，只是调用时用到的一些配置的加载。
		case "firefox":
			System.setProperty("webdriver.gecko.driver", "webDrivers/geckodriver.exe");
			driver = new FirefoxDriver();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			break;
		// 默认输入时用chrome进行启动
		default:
			GoogleDriver ggdefault = new GoogleDriver("webDrivers/chromedriver.exe");
			driver = ggdefault.getdriver();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			break;
		}

	}

	/**
	 * 通过调用成员变量，完成get方法的调用，达到访问某个网站的目的
	 * 
	 * @param url 访问网站的url地址
	 */
	public void visitWeb(String url) {
		try {
			driver.get(url);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 基于name属性定位元素并且输入内容,然后提交。
	 * 
	 * @param NameAttr     待定位元素的name属性
	 * @param inputContent 需要输入的内容
	 */
	public void inputAndSubmitByName(String NameAttr, String inputContent) {
		try {
			WebElement element = driver.findElement(By.name(NameAttr));
			element.sendKeys(inputContent);
			element.submit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void click(String xpath) {
		try {
			driver.findElement(By.xpath(xpath)).click();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//通过Xpath定位元素
	public void input(String xpath,String content) {
		WebElement ele=driver.findElement(By.xpath(xpath));
		//通过clear方法完成输入框中的清理
		ele.clear();
		ele.sendKeys(content);
	}
	/**
	 * 通过findelements方法定位符合条件的所有元素，获取其文本内容。
	 * @param xpath
	 */
	public void getAllgoodsType(String xpath) {
		List<WebElement> goodslist= driver.findElements(By.xpath(xpath));
		//遍历每一个元素
		for(WebElement e:goodslist) {
			System.out.println(e.getText());
		}
		
	}
	
	//获取网页标题
	public String getTitle() {
		try {
			String title = driver.getTitle();
			return title;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "获取标题失败";
		}
	}

	/**
	 * 显式等待，指定一个最长的等待时间，在这个时间内反复地确认预期的事件是否发生了，如果发生了，则结束等待，继续执行，如果超时还未发生，则报错。
	 * 这个方法的实际用途是等待标题编程以cheese开头。  
	 * 该方法用的不太多
	 * ExpectedCondition
	 */
	public void explicitlyWaitTitle() {
		// 设定等待的事件，多少秒会超时。这里是10秒
		try {
			WebDriverWait ewait = new WebDriverWait(driver, 10);
			// 设定等待的事件是什么
			// 匿名内部类的声明
			// expectedcondition就是期望的等待事件，<Boolean>中的Boolean表示预期事件的类型。
			ewait.until(
					// 完成等待事件的定义
					new ExpectedCondition<Boolean>() {
						// apply方法真正指定等待的条件。在等待过程中，会以一定的周期反复地观测事件是否达成。
						// 如果返回为true，则等待成功，结束等待，如果为false，一直等满10秒之后，报错。
						//这里的webdriver参数实际上由webdriverwait对象把webdriverwait实例化时用到的driver参数，作为实参传给形参roy。
						public Boolean apply(WebDriver roy) {
							// 返回一个Boolean类型的数据
							// Boolean这个类型，在expectedcondition<>的尖括号里面定义的类型，同时也是apply方法要返回的类型。
							return roy.getTitle().toLowerCase().startsWith("cheese!");
						}
					});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 显式等待，等待某个元素定位表达式能够在页面中定位到一个元素  
	 * @param xpathExp 等待的元素的定位表达式。
	 * new ExpectedCondition 方式可以编写自己等待的事件
	 * 该方法不建议使用，可能会与隐式等待 产生重复等待或者冲突。
	 * 注意：在进行显式等待的设置时，一般只对元素定位事件以外一些特殊事件进行定义，尽量不要在使用隐式等待的同时，
	 * 再加上一个等待元素能够被定位的显式等待（因为没意义），即使一定要用，最好时长设为一致。
	 */
	//等待都是为了让页面能够加载完，以便进行下一步操作，所以等待是有必要的
	//例如需要点击Cheese翻译，但是如果打开页面，页面还没有加载完就去点击Cheese翻译，就会报错的，所以等待元素能被定位到是有必要的
	public void expllicitlyWaitEle(String xpathExp) {
		try {
			WebDriverWait ewait=new WebDriverWait(driver, 10);
			ewait.until(new ExpectedCondition<WebElement>() {
				//等待某一个元素是否出现了
				public WebElement apply(WebDriver d) {
					return d.findElement(By.xpath(xpathExp));
				}
			});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 显式等待，还可以使用selenium已经预定义好的一些等待条件。 
	 * @param xpathExp
	 * ExpectedConditions类中已经预定义好的静态方法指定等待事件
	 * 该方法比较常用，也足够使用了。
	 */
	public void explicitlyWaitEleLoc(String xpathExp) {
		try {
			WebDriverWait ewait =new WebDriverWait(driver, 10);
			//等待条件是指定的元素定位方法能够定位到的元素，出现了
			ewait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpathExp)));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	


	
	/**
	 * 强制等待：线程休眠，最死板的等待，没有结束等待的条件，固定等待指定的时长。通常用于一些不确定原因的等待。
	 */
	public void halt(String waitTime) {
		try {
			int t=Integer.parseInt(waitTime);
			//线程休眠，让程序停止一段时间，这个时间是固定的，没有任何条件来解除等待。
			Thread.sleep(t);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	/**
	 * 关闭浏览器以及driver进程。
	 */
	public void closeBrowser() {
		try {
			driver.quit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	
	
}
