package com.logigear.browser.safari;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.logigear.driver.manager.LocalDriverManager;

public class LocalSafariDriver extends LocalDriverManager {

	@Override
	public void createWebDriver(String key) throws Exception {
		try {
			//System.setProperty("webdriver.safari.driver", getDriverExecutable());
			//System.setProperty("webdriver.safari.noinstall", "true");
			WebDriver driver = new SafariDriver();
			this.webDrivers.put(key, driver);
		} catch (Exception ex) {
			System.out.println("Getting error when creating Web Driver: " + ex.getMessage());
			throw new Exception(ex.getMessage());
		}
	}
}
