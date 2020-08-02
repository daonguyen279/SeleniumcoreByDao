package com.logigear.browser.chrome;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.logigear.driver.manager.RemoteDriverManager;

public class RemoteChromeDriver extends RemoteDriverManager {

	@Override
	public void createWebDriver(String key) throws Exception {
		try {
			ChromeOptions ops = new ChromeOptions();
			ops.merge(getCapabilities());
			ops.addArguments(getArguments());
			WebDriver webDriver = new RemoteWebDriver(getRemoteUrl(), ops);
			this.webDrivers.put(key, webDriver);
		} catch (Exception ex) {
			System.out.println("Getting error when creating Web Driver: " + ex.getMessage());
			throw new Exception(ex.getMessage());
		}
	}
}
