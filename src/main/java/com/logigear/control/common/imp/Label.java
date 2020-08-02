package com.logigear.control.common.imp;

import com.logigear.control.base.imp.BaseControl;
import com.logigear.control.base.imp.Clickable;
import com.logigear.control.common.ILabel;
import org.openqa.selenium.By;

public class Label extends Clickable implements ILabel {

	public Label(By locator) {
		super(locator);
	}

	public Label(String locator) {
		super(locator);
	}

	public Label(String locator, Object... value) {
		super(locator, value);
	}

	public Label(BaseControl parent, String locator) {
		super(parent, locator);
	}

	public Label(BaseControl parent, By locator) {
		super(parent, locator);
	}

	public Label(BaseControl parent, String locator, Object... value) {
		super(parent, locator, value);
	}
}
