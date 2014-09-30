package util;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by lazhcm10136 on 9/30/14.
 */
public class Helper {
    public static AppiumDriver driver;
    public static URL serverAddress;
    private static WebDriverWait driverWait;

    /**
     * Initialize the webdriver. Must be called before using any helper methods. *
     */
    public static void init(AppiumDriver webDriver, URL driverServerAddress) {
        driver = webDriver;
        serverAddress = driverServerAddress;
        int timeoutInSeconds = 60;
        // must wait at least 60 seconds for running on Sauce.
        // waiting for 30 seconds works locally however it fails on Sauce.
        driverWait = new WebDriverWait(webDriver, timeoutInSeconds);
    }

    /**
     * Wrap WebElement in MobileElement *
     */
    private static MobileElement w(WebElement element) {
        return new MobileElement((RemoteWebElement) element, driver);
    }

    /**
     * Wrap WebElement in MobileElement *
     */
    private static List<MobileElement> w(List<WebElement> elements) {
        List list = new ArrayList(elements.size());
        for (WebElement element : elements) {
            list.add(w(element));
        }

        return list;
    }

    /**
     * Set implicit wait in seconds *
     */
    public static void setWait(int seconds) {
        driver.manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
    }

    /**
     * Return an element by locator *
     */
    public static MobileElement element(By locator) {
        return w(driver.findElement(locator));
    }

    /**
     * Return a list of elements by locator *
     */
    public static List<MobileElement> elements(By locator) {
        return w(driver.findElements(locator));
    }

    /**
     * Press the back button *
     */
    public static void back() {
        driver.navigate().back();
    }

    /**
     * Return a list of elements by tag name *
     */
    public static List<MobileElement> tags(String tagName) {
        return elements(for_tags(tagName));
    }

    /**
     * Return a tag name locator *
     */
    public static By for_tags(String tagName) {
        return By.className(tagName);
    }

    /**
     * Return a static text element by xpath index *
     */
    public static MobileElement s_text(int xpathIndex) {
        return element(for_text(xpathIndex));
    }

    /**
     * Return a static text locator by xpath index *
     */
    public static By for_text(int xpathIndex) {
        return By.xpath("//android.widget.TextView[" + xpathIndex + "]");
    }

    /**
     * Return a static text element that contains text *
     */
    public static MobileElement text(String text) {
        return element(for_text(text));
    }

    /**
     * Return a static text locator that contains text *
     */
    public static By for_text(String text) {
        return By.xpath("//android.widget.TextView[contains(@text, '" + text + "')]");
    }

    /**
     * Return a static text element by exact text *
     */
    public static MobileElement text_exact(String text) {
        return element(for_text_exact(text));
    }

    /**
     * Return a static text locator by exact text *
     */
    public static By for_text_exact(String text) {
        return By.xpath("//android.widget.TextView[@text='" + text + "']");
    }

    public static By for_find(String value) {
        return By.xpath("//*[@content-desc=\"" + value + "\" or @resource-id=\"" + value +
                "\" or @text=\"" + value + "\"] | //*[contains(translate(@content-desc,\"" + value +
                "\",\"" + value + "\"), \"" + value + "\") or contains(translate(@text,\"" + value +
                "\",\"" + value + "\"), \"" + value + "\") or @resource-id=\"" + value + "\"]");
    }

    public static MobileElement find(String value) {
        return element(for_find(value));
    }

    public static By for_edit_text(String text) { return By.xpath("//android.widget.EditText[@text='" + text + "']"); }

    public static MobileElement find_edit_text(String value) { return  element(for_edit_text(value)); }

    /**
     * Wait 30 seconds for locator to find an element *
     */
    public static MobileElement wait(By locator) {
        return w(driverWait.until(ExpectedConditions.visibilityOfElementLocated(locator)));
    }

    public static WebElement wait_web(By locator) {
        return driverWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    /**
     * Wait 60 seconds for locator to find all elements *
     */
    public static List<MobileElement> waitAll(By locator) {
        return w(driverWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator)));
    }

    /**
     * Wait 60 seconds for locator to not find a visible element *
     */
    public static boolean waitInvisible(By locator) {
        return driverWait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    /**
     * Return an element that contains name or text *
     */
    public static MobileElement scroll_to(String value) {
        return driver.scrollTo(value);
    }

    /**
     * Return an element that exactly matches name or text *
     */
    public static MobileElement scroll_to_exact(String value) {
        return driver.scrollToExact(value);
    }
}

