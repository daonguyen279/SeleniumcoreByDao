package com.logigear.control.common.imp;

import com.logigear.control.base.imp.BaseControl;
import com.logigear.control.base.imp.Editable;
import com.logigear.control.common.ITextBox;
import org.openqa.selenium.By;

public class TextBox extends Editable implements ITextBox {

	public TextBox(String locator) {
		super(locator);
	}

	public TextBox(By locator) {
		super(locator);
	}

	public TextBox(String locator, Object... value) {
		super(locator, value);
	}

	public TextBox(BaseControl parent, String locator) {
		super(parent, locator);
	}

	public TextBox(BaseControl parent, By locator) {
		super(parent, locator);
	}

	public TextBox(BaseControl parent, String locator, Object... value) {
		super(parent, locator, value);
	}
}
