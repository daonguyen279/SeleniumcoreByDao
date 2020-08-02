package com.logigear.control.base.imp;

import com.logigear.control.base.IClickable;
import com.logigear.driver.DriverUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Clickable extends BaseControl implements IClickable {

    private Logger logger = Logger.getLogger(Clickable.class);

    public Clickable(String locator) {
        super(locator);
    }

    public Clickable(By locator) {
        super(locator);
    }

    public Clickable(String locator, Object... value) {
        super(locator, value);
    }

    public Clickable(BaseControl parent, String locator) {
        super(parent, locator);
    }

    public Clickable(BaseControl parent, By locator) {
        super(parent, locator);
    }

    public Clickable(BaseControl parent, String locator, Object... value) {
        super(parent, locator, value);
    }

    @Override
    public void click() {
        click(1);
    }

    @Override
    public void click(int times) {
        if (times > 0)
            try {
                logger.debug(String.format("Click on %s", getLocator().toString()));
                getElement().click();
            } catch (Exception e) {
                if (e.getMessage().split("\n")[0].contains("Other element would receive the click")) {
                    times--;
                    DriverUtils.delay(1);
                    if (times == 1) {
                        clickByJs();
                    } else {
                        click(times);
                    }
                } else {
                    logger.error(String.format("Error occurred while clicking on control '%s': %s",
                            getLocator().toString(), e.getMessage().split("\n")[0]));
                    throw e;
                }
            }
    }

    @Override
    public void click(int x, int y) {
        try {
            logger.debug(String.format("Click on %s", getLocator().toString()));
            new Actions(getDriver()).moveToElement(getElement(), x, y).click().build().perform();
        } catch (Exception e) {
            logger.error(String.format("Has error with control '%s': %s", getLocator().toString(), e.getMessage().split("\n")[0]));
            throw e;
        }
    }

    @Override
    public void clickByJs() {
        try {
            logger.debug(String.format("Click by js on %s", getLocator().toString()));
            jsExecutor().executeScript("arguments[0].click();", getElement());
        } catch (Exception e) {
            logger.error(String.format("Has error with control '%s': %s", getLocator().toString(), e.getMessage().split("\n")[0]));
            throw e;
        }
    }

    @Override
    public void doubleClick() {
        try {
            logger.debug(String.format("Double click on %s", getLocator().toString()));
            new Actions(getDriver()).doubleClick(getElement()).build().perform();
        } catch (Exception e) {
            logger.error(String.format("Has error with control '%s': %s", getLocator().toString(), e.getMessage().split("\n")[0]));
            throw e;
        }

    }

    @Override
    public void waitForElementClickable() {
        waitForElementClickable(DriverUtils.getTimeOut());
    }

    @Override
    public void waitForElementClickable(int timeOutInSecond) {
        try {
            if (!isVisible())
                waitForDisplay(timeOutInSecond);

            logger.info(String.format("Wait for element click able %s", getLocator().toString()));
            WebDriverWait wait = new WebDriverWait(getDriver(), timeOutInSecond);
            wait.until(ExpectedConditions.elementToBeClickable(getElement()));
        } catch (Exception e) {
            logger.error(String.format("WaitForElementClickable: Has error with control '%s': %s",
                    getLocator().toString(), e.getMessage().split("\n")[0]));
        }
    }
}
