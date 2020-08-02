package com.logigear.control.base.imp;

import com.logigear.control.base.IEditable;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

public class Editable extends Clickable implements IEditable {

	private Logger logger = Logger.getLogger(Editable.class);

	public Editable(String locator) {
		super(locator);
	}

	public Editable(By locator) {
		super(locator);
	}

	public Editable(String locator, Object... value) {
		super(locator, value);
	}

	public Editable(BaseControl parent, String locator) {
		super(parent, locator);
	}

	public Editable(BaseControl parent, By locator) {
		super(parent, locator);
	}

	public Editable(BaseControl parent, String locator, Object... value) {
		super(parent, locator, value);
	}

	@Override
	public void enter(CharSequence... value) {
		try {
			logger.debug(String.format("Enter '%s' for %s", value, getLocator().toString()));
			getElement().sendKeys(value);
		} catch (Exception e) {
			logger.error(String.format("Has error with control '%s': %s", getLocator().toString(), e.getMessage().split("\n")[0]));
			throw e;
		}
	}

	@Override
	public void setValue(String value) {
		try {
			String js = String.format("arguments[0].value='%s';", value);
			logger.debug(String.format("Set value '%s' for %s", value, getLocator().toString()));
			jsExecutor().executeScript(js, getElement());
		} catch (Exception e) {
			logger.error(String.format("Has error with control '%s': %s", getLocator().toString(), e.getMessage().split("\n")[0]));
			throw e;
		}
	}

	@Override
	public void clear() {
		try {
			logger.debug(String.format("Clean text for %s", getLocator().toString()));
			getElement().clear();
		} catch (Exception e) {
			logger.error(String.format("Has error with control '%s': %s", getLocator().toString(), e.getMessage().split("\n")[0]));
			throw e;
		}
	}
	
}
