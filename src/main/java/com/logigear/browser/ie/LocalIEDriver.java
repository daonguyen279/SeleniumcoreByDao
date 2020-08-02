package com.logigear.browser.ie;

import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;

import com.logigear.driver.manager.LocalDriverManager;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LocalIEDriver extends LocalDriverManager {

	@Override
	public void createWebDriver(String key) throws Exception {
		try {
			WebDriverManager.iedriver().arch32().setup();
			InternetExplorerOptions ops = new InternetExplorerOptions();
			ops.merge(getCapabilities());
			this.webDrivers.put(key, new InternetExplorerDriver(ops));
		} catch (Exception ex) {
			System.out.println("Getting error when creating Web Driver: " + ex.getMessage());
			throw new Exception(ex.getMessage());
		}
	}
}
