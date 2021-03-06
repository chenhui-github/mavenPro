package com.testing.class1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class IEDemo {
	public static void main(String[] args) {
		// 设置临时的系统变量，让代码能够在系统中找到geckodriver的位置。
		System.setProperty("webdriver.ie.driver", "webDrivers/IEDriverServer.exe");
		// Create a new instance of the Firefox driver
		// Notice that the remainder of the code relies on the interface,
		// not the implementation.
		// 通过webdriver对象的实例化，完成浏览器的启动过程和启动端口作为服务端监听脚本命令的过程。
		WebDriver driver = new InternetExplorerDriver();

		// And now use this to visit Google
		// get方法访问url地址
		driver.get("http://www.baidu.com");
		// Alternatively the same thing can be done like this
		// driver.navigate().to("http://www.google.com");

		// Find the text input element by its name
		// 基于输入框的name属性值为wd，来查找元素，实例化一个element对象。
		WebElement element = driver.findElement(By.name("wd"));

		// Enter something to search for
		element.sendKeys("Cheese!");

		// Now submit the form. WebDriver will find the form for us from the element
		element.submit();

		// Check the title of the page
		// getTitle方法获取到页面的标题。
		System.out.println("Page title is: " + driver.getTitle());

		// Google's search is rendered dynamically with JavaScript.
		// Wait for the page to load, timeout after 10 seconds
		// 显式等待，等待页面标题变成以cheese开头，如果超过10秒，依然没有符合条件，则报错。
		(new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return d.getTitle().toLowerCase().startsWith("cheese!");
			}
		});

		// Should see: "cheese! - Google Search"
		System.out.println("Page title is: " + driver.getTitle());

		// Close the browser
		// 关闭浏览器以及driver的进程。
		driver.quit();
	}
}
