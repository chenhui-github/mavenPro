package com.testing.driverself;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;

//Firefox浏览器驱动类
public class FFDriver {
	private WebDriver driver = null;
	//propath:是指火狐浏览器所安装位置，即火狐浏览器安装根目录
	//driverpath:是指火狐浏览器驱动（geckodriver.exe）所在的路径
	public FFDriver(String propath, String driverpath) {
		// 设置 Firefox驱动的路径
		System.setProperty("webdriver.gecko.driver", driverpath);
		//由于新版本的火狐浏览器不允许指定安装的位置了，所以对于新版本该设置没有用。 
		//设置Firefox的安装目录,在指定的安装位置（propath）可以找到firefox.exe.
		//如果不需要设置，那么参数给一个空字符串
		if (propath != null && propath.length() > 0) {
			System.setProperty("webdriver.firefox.bin", propath);
		}

		// 加载火狐的用户文件（可在火狐浏览器网址栏输入 ： about:support 回车一下就可以查看到用户文件的路径）
		// 用户文件地址可以通过在火狐地址栏中输入about:support找到配置文件路径，复制来即可，但是由于加载配置，启动会花费比较久的时间。
		//加载火狐的用户文件的作用是，可以是有本地的cookie来以登陆状态进行访问。
		//如需要使用火狐用户文件，则需要修改为自己本地火狐的用户文件就可以了。
//		File pro=new File("C:\\Users\\roy08\\AppData\\Roaming\\Mozilla\\Firefox\\Profiles\\p16g0t0u.default-release-1580666043588");
//		FirefoxProfile profile=new FirefoxProfile(pro);
		FirefoxOptions opt=new FirefoxOptions();
//		opt.setProfile(profile);
		

		// 创建一个 Firefox的浏览器实例，赋值给成员变量
		try {
			driver = new FirefoxDriver(opt);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("log--error：创建driver失败！！");
		}

	}

	public WebDriver getdriver() {
		return this.driver;
	}
}