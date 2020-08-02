package com.logigear.control.common.imp;

import com.logigear.control.base.imp.BaseControl;
import com.logigear.control.base.imp.Editable;
import com.logigear.control.common.IRadioButton;
import org.openqa.selenium.By;

public class RadioButton extends Editable implements IRadioButton {

    public RadioButton(By locator) {
        super(locator);
    }

    public RadioButton(String locator) {
        super(locator);
    }

    public RadioButton(String locator, Object... value) {
        super(locator, value);
    }

    public RadioButton(BaseControl parent, String locator) {
        super(parent, locator);
    }

    public RadioButton(BaseControl parent, By locator) {
        super(parent, locator);
    }

    public RadioButton(BaseControl parent, String locator, Object... value) {
        super(parent, locator, value);
    }

    @Override
    public void check() {
        click();
    }

    @Override
    public boolean isChecked() {
        return isSelected();
    }
}
