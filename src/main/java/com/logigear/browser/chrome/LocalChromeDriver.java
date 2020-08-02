package com.logigear.browser.chrome;

import com.logigear.driver.manager.LocalDriverManager;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class LocalChromeDriver extends LocalDriverManager {

	@Override
	public void createWebDriver(String key) throws Exception {
		try {
			WebDriverManager.chromedriver().setup();
			ChromeOptions ops = new ChromeOptions();
			ops.merge(getCapabilities());
			ops.addArguments(getArguments());
			WebDriver webDriver = new ChromeDriver(ops);
			this.webDrivers.put(key, webDriver);
		} catch (Exception ex) {
			System.out.println("Getting error when creating Web Driver: " + ex.getMessage());
			throw new Exception(ex.getMessage());
		}
	}
}
