package com.logigear.browser.edge;

import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.logigear.driver.manager.RemoteDriverManager;

public class RemoteEdgeDriver extends RemoteDriverManager {

	@Override
	public void createWebDriver(String key) throws Exception {
		try {
			EdgeOptions ops = new EdgeOptions();
			ops.merge(getCapabilities());
			this.webDrivers.put(key, new RemoteWebDriver(getRemoteUrl(), ops));
		} catch (Exception ex) {
			System.out.println("Getting error when creating Web Driver: " + ex.getMessage());
			throw new Exception(ex.getMessage());
		}
	}
}
