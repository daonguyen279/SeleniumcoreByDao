package com.logigear.control.common.imp;

import com.logigear.control.base.imp.BaseControl;
import com.logigear.control.common.IFrame;
import org.openqa.selenium.By;

public class Frame extends Element implements IFrame {
	
	public Frame(By locator) {
		super(locator);
	}

	public Frame(String locator) {
		super(locator);
	}

	public Frame(String locator, Object... value) {
		super(locator, value);
	}
	
	public Frame(BaseControl parent, String locator) {
		super(parent, locator);
	}

	public Frame(BaseControl parent, By locator) {
		super(parent, locator);
	}

	public Frame(BaseControl parent, String locator, Object... value) {
		super(parent, locator, value);
	}

	@Override
	public void switchTo() {
		getDriver().switchTo().frame(getElement());
	}

	@Override
	public void switchToMainDocument() {
		getDriver().switchTo().defaultContent();
	}

}
