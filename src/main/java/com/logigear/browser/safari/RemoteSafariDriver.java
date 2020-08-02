package com.logigear.browser.safari;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.logigear.driver.manager.RemoteDriverManager;

public class RemoteSafariDriver extends RemoteDriverManager {

	@Override
	public void createWebDriver(String key) throws Exception {
		try {
			DesiredCapabilities caps = DesiredCapabilities.safari();
			caps.merge(getCapabilities());
			// SafariOptions ops = new SafariOptions();
			// ops.merge(getCapabilities());
			WebDriver driver = new RemoteWebDriver(getRemoteUrl(), caps);
			this.webDrivers.put(key, driver);
		} catch (Exception ex) {
			System.out.println("Getting error: " + ex.getMessage());
			throw new Exception(ex.getMessage());
		}
		
	}
}
