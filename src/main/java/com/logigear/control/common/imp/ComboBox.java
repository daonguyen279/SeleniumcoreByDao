package com.logigear.control.common.imp;

import com.logigear.control.base.imp.BaseControl;
import com.logigear.control.base.imp.Clickable;
import com.logigear.control.common.IComboBox;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class ComboBox extends Clickable implements IComboBox {

    private Logger logger = Logger.getLogger(Element.class);

    public ComboBox(By locator) {
        super(locator);
    }

    public ComboBox(String locator) {
        super(locator);
    }

    public ComboBox(String locator, Object... value) {
        super(locator, value);
    }

    public ComboBox(BaseControl parent, String locator) {
        super(parent, locator);
    }

    public ComboBox(BaseControl parent, By locator) {
        super(parent, locator);
    }

    public ComboBox(BaseControl parent, String locator, Object... value) {
        super(parent, locator, value);
    }

    @Override
    public List<String> getOptions() {
        List<String> ops = new ArrayList<String>();
        Select select = new Select(getElement());
        List<WebElement> options = select.getOptions();
        for (WebElement option : options) {
            ops.add(option.getText());
        }
        return ops;
    }

    @Override
    public int totalOptions() {
        Select select = new Select(getElement());
        List<WebElement> options = select.getOptions();
        return options.size();
    }

    @Override
    public String getSelected() {
        Select select = new Select(getElement());
        return select.getFirstSelectedOption().getText();
    }

    @Override
    public void select(String text) {
        Select select = new Select(getElement());
        select.selectByVisibleText(text);
    }

    @Override
    public void select(int index) {
        Select select = new Select(getElement());
        select.selectByIndex(index);
    }
}
