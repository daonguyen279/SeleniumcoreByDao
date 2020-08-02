package com.logigear.driver;

import com.logigear.driver.manager.DriverManagerFactory;
import com.logigear.exception.DriverCreationException;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.io.File;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class DriverUtils extends DriverManagerFactory {

    private static Logger logger = Logger.getLogger(DriverUtils.class);

    public static Actions actions() {
        return new Actions(getWebDriver());
    }

    public static void backToMainWindow() {
        getDriver().switchTo().window(DRIVERS.get().getMainWindow());
    }

    public static String captureScreenshot(String filename, String filepath) {
        logger.info("Capturing screenshot");
        String path = "";
        try {
            // Storing the image in the local system.
            Screenshot screenshot = new AShot()
                    .shootingStrategy(ShootingStrategies.viewportPasting(1000))
                    .takeScreenshot(getWebDriver());
            File dest = new File(filepath + File.separator + filename);
            ImageIO.write(screenshot.getImage(), "PNG", dest);
            path = dest.getAbsolutePath();
        } catch (Exception e) {
            logger.error("An error occurred when capturing screen shot: " + e.getMessage());
        }
        return path;
    }

    public static void close() {
        getDriver().close();
    }

    public static void delay(double timeInSecond) {
        try {
            Thread.sleep((long) (timeInSecond * 1000));
        } catch (Exception e) {
            logger.error("An error occurred when delay: " + e.getMessage());
        }
    }

    public static void deleteCookie() {
        getDriver().manage().deleteAllCookies();
    }

    public static Object execJavaScript(String script, Object... objs) {
        return ((JavascriptExecutor) getDriver()).executeScript(script, objs);
    }

    public static String getAppiumCapability(String key) {
        return ((RemoteWebDriver) getDriver()).getCapabilities().getCapability(key).toString();
    }

    public static String getCurrentUrl() {
        return getDriver().getCurrentUrl();
    }

    public static void getDriver(DriverProperty property) throws DriverCreationException {
        getDriver("default", property);
    }

    public static void getDriver(String driverKey, DriverProperty property) throws DriverCreationException {
        logger.debug(String.format("Creating the %s driver", property.getDriverType().name()));
        createWebDriver(driverKey, property);
        switchToDriver(driverKey);
    }

    public static String getDriverType() {
        return getCurrentDriverType().toLowerCase();
    }

    public static int getNumberOfWindows() {
        return getWebDriver().getWindowHandles().size();
    }

    public static String getSessionId() {
        String sessionId = null;
        try {
            sessionId = ((RemoteWebDriver) getDriver()).getSessionId().toString();
        } catch (Exception ex) {
            logger.error("An error occurred when getting session Id " + ex.getMessage());
        }
        return sessionId;
    }

    public static int getShortTimeOut() {
        return DriverManagerFactory.getShortTimeOut();
    }

    public static void setShortTimeOut(int timeoutSec) {
        DriverManagerFactory.setShortTimeOut(timeoutSec);
    }

    public static int getTimeOut() {
        return DriverManagerFactory.getTimeOut();
    }

    public static void setTimeOut(int timeoutSec) {
        DriverManagerFactory.setTimeOut(timeoutSec);
    }

    public static WebDriver getWebDriver() {
        return getDriver();
    }

    public static String getWindowHandle() {
        return getDriver().getWindowHandle();
    }

    public static List<String> getWindowHandles() {
        return new ArrayList<String>(getDriver().getWindowHandles());
    }

    public static boolean isUrlStable(String url, StringBuilder msg) {
        try {
            if (msg == null)
                msg = new StringBuilder();

            HttpURLConnection.setFollowRedirects(false);
            HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();
            con.setRequestMethod("HEAD");

            int statusCode = con.getResponseCode();
            msg.append("Status code: ").append(statusCode);
            msg.append(" - Error: ").append(con.getResponseMessage());

            return (statusCode == HttpURLConnection.HTTP_OK);
        } catch (Exception e) {
            logger.error("An error occurred when check URL: " + e.getStackTrace().toString());
            return false;
        }
    }

    public static void maximizeBrowser() {
        try {
            logger.debug("Maximize browser");
            DriverManagerFactory.getDriver().manage().window().maximize();
        } catch (Exception e) {
            logger.error("An error occurred when maximizing browser" + e.getMessage());
        }
    }

    public static void navigate(String url) {
        navigate(url, false);
    }

    public static void navigate(String url, boolean isCheckUrlStable) {
        logger.debug("Navigate to " + url);
        StringBuilder msg = new StringBuilder();

        if (isCheckUrlStable) {
            if (!isUrlStable(url, msg))
                throw new RuntimeException("Can't connect to URL. Details: " + msg.toString());
        }
        getDriver().get(url);
    }

    public static void openNewTab() {
        ((JavascriptExecutor) getDriver()).executeScript("window.open('about:blank','_blank');");
    }

    public static void quitBrowser() {
        try {
            logger.debug("Quit browser");
            DriverManagerFactory.DRIVERS.get().quitAll();

        } catch (Exception e) {
            logger.error("An error occurred when quiting browser" + e.getMessage());
        }
    }

    public static void quitBrowser(boolean result) {
        quitBrowser();
    }

    public static void refresh() {
        getDriver().navigate().refresh();
    }

    public static void switchDriver(String key) {
        switchToDriver(key);
    }

    public static void switchTo(String windowHandle) {
        getDriver().switchTo().window(windowHandle);
    }

    public static void switchToNewWindow() {
        for (String winHandle : getDriver().getWindowHandles()) {
            getDriver().switchTo().window(winHandle);
        }
    }

    public static void waitForAngularReady() {
        try {
            WebDriverWait wait = new WebDriverWait(getWebDriver(), getTimeOut());

            wait.until(driver -> {
                JavascriptExecutor executor = (JavascriptExecutor) driver;
                return (Boolean) (executor
                        .executeScript("return angular.element(document).injector().get('$http').pendingRequests.length === 0;"));
            });
        } catch (Exception e) {
            logger.error("waitForAngularReady: An error occurred when waitForAngularReady" + e.getMessage());
        }
    }

    public static boolean waitForCondition(Callable<Boolean> conditionEvaluator, Duration interval, Duration timeout) {
        Wait<WebDriver> wait = new FluentWait<>(getDriver()).withTimeout(timeout).pollingEvery(interval);
        try {
            return wait.until(driver -> {
                try {
                    return conditionEvaluator.call();
                } catch (Exception e) {
                    System.out.println("DriverUtils::waitForCondition() -> " + e.getMessage());
                }
                return false;
            });
        } catch (TimeoutException e) {
            System.out.println("DriverUtils::waitForCondition() -> " + e.getMessage());
            return false;
        }
    }

    public static void waitForJavaScriptIdle() {
        try {
            WebDriverWait wait = new WebDriverWait(getWebDriver(), getTimeOut());

            wait.until(driver -> {
                JavascriptExecutor executor = (JavascriptExecutor) driver;
                /* Uncomment this once jquery issue on Portal is solved.
                Boolean ajaxIsComplete = (Boolean) (executor.executeScript(
                        "if (typeof jQuery != 'undefined') { return jQuery.active == 0; } else {  return true; }"));
                 */

                Boolean domIsComplete = (Boolean) (executor
                        .executeScript("return document.readyState == 'complete';"));
                return domIsComplete;
            });
        } catch (Exception e) {
            logger.error("waitForJavaScriptIdle: An error occurred when waitForJavaScriptIdle" + e.getMessage());
        }
    }

    public static void waitForNewWindowOpened(int expectedNumberOfWindows) {
        WebDriverWait wait = new WebDriverWait(getWebDriver(), getTimeOut());
        wait.until(ExpectedConditions.numberOfWindowsToBe(expectedNumberOfWindows));
    }
}
