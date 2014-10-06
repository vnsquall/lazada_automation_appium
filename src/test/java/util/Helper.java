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
import java.util.NoSuchElementException;
import java.util.Random;
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
        driverWait = new WebDriverWait(webDriver, timeoutInSeconds);
    }

    public static void randClick(By locator) {
        Random rand = new Random();
        List<WebElement> we = driver.findElements(locator);
        int catNum = we.size();
        int randClick = rand.nextInt((catNum - 1) + 1);
        if (randClick > catNum || randClick < 1) {
            randClick(locator);
        } else {
            if (!we.get(randClick).getText().isEmpty()) {
                we.get(randClick).click();
            } else {
                randClick(locator);
            }
        }
    }

    public static Boolean isElementPresent(By locator) {
        Boolean isPresent = Boolean.FALSE;
        try {
            isPresent = driver.findElements(locator).size() > 0;
            return isPresent;
        } catch (NoSuchElementException ex) {
            return isPresent;
        }
    }

    public static WebElement find_xpath_forText(String appPackage, String id, String text) {
        WebElement we = driver.findElement(By.xpath("//android.widget.TextView[@resource-id='" + appPackage + id + "'and @text='" + text + "']"));
        return we;
    }

    public static void randomSelectProduct(String categories, String appPackage, String filterWiz, String prodWiz) {
        text_exact(categories).click();

        // Random selection Categories
        randClick(By.id(appPackage + ":id/category_name"));

        text_exact(filterWiz).click();

        // Random selection sub-Categories
        randClick(By.id(appPackage + ":id/text"));

        // Get back to the Main Screen for viewing the product
        find(appPackage + ":id/general_container").click();
        find(appPackage + ":id/general_container").click();

        text_exact(prodWiz).click();
    }

    public static void selectVenture(String venture, String menuWiz) throws InterruptedException {
        driver.findElement(By.xpath(("//android.widget.TextView[contains(@text, '" + venture + "')]"))).click();
        Thread.sleep(3000);
//        wait_web(By.xpath(("//android.widget.TextView[contains(@text, '" + menuWiz + "')]"))).click();
        find(menuWiz).click();
        Thread.sleep(2000);
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

    public static By for_edit_text(String text) {
        return By.xpath("//android.widget.EditText[@text='" + text + "']");
    }

    public static MobileElement find_edit_text(String value) {
        return element(for_edit_text(value));
    }

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

