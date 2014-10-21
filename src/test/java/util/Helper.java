package util;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.NoSuchElementException;
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
        /*if (catNum > 0) {*/
            int randClick = rand.nextInt(catNum);
            we.get(randClick).click();    
            
      /*  } else {
            randClick(locator);
        }*/
        
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

    public static void selectVenture(String venture, String menuWiz) throws InterruptedException {
        driver.findElement(By.xpath(("//android.widget.TextView[contains(@text, '" + venture + "')]"))).click();
        if (venture.equals("Thailand")) {
            driver.findElement(By.xpath("//android.widget.Button[contains(@text, 'English')]")).click();
        }

        Thread.sleep(5000);
        find(menuWiz).click();
        Thread.sleep(6000);
    }

    public static void swipeDown() {
        JavascriptExecutor scrollDown = driver;
        HashMap<String, Double> swipeDown = new HashMap<String, Double>();
        swipeDown.put("startX", 0.95);
        swipeDown.put("startY", 0.95);
        swipeDown.put("endX", 0.95);
        swipeDown.put("endY", 0.5);
        swipeDown.put("duration", 1.8);
        scrollDown.executeScript("mobile: swipe", swipeDown);
    }

    public static void swipeUp() {
        JavascriptExecutor scrollUp = driver;
        HashMap<String, Double> swipeUp = new HashMap<String, Double>();
        swipeUp.put("startX", 0.95);
        swipeUp.put("startY", 0.6);
        swipeUp.put("endX", 0.95);
        swipeUp.put("endY", 0.95);
        swipeUp.put("duration", 1.8);
        scrollUp.executeScript("mobile: swipe", swipeUp);
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
            list.add((element));
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
    public static List<WebElement> waitAll(By locator) {
        return driverWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));


    }

    /**
     * Wait 60 seconds for locator to not find a visible element *
     */
    public static boolean waitInvisible(By locator) {
        return driverWait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    /**
     * find Element by UIAndroidSelector
     *
     * @param selectorTypeStr
     * @param value
     * @param appPackage
     * @return WebElement
     */
    public static WebElement findByUISelector(String selectorTypeStr, String value, String appPackage) {
        WebElement e = null;
        UISelectorType selector = UISelectorType.fromString(selectorTypeStr);

        switch (selector) {
            case RESOURCE_ID:
                e = driver.findElementByAndroidUIAutomator("UiSelector().resourceId(\"" + appPackage + ":id/" + value + "\")");
                break;
            case TEXT:
                e = driver.findElementByAndroidUIAutomator("UiSelector().text(\"" + value + "\")");
                break;
            case TEXT_CONTAINS:
                e = driver.findElementByAndroidUIAutomator("UiSelector().textContains(\"" + value + "\")");
                break;
            case TEXT_START_WITH:
                e = driver.findElementByAndroidUIAutomator("UiSelector().textStartsWith(\"" + appPackage + ":" + value + "\")");
                break;
            case CLASS_NAME:
                e = driver.findElementByAndroidUIAutomator("UiSelector().className(\""+value+"\")");
                break;
        }

        return e;
    }

    /**
     * find Element by UIAndroidSelector
     *
     * @param selectorTypeStr
     * @param value
     * @param appPackage
     * @return List of Element
     */
    public static List<WebElement> findsByUISelector(String selectorTypeStr, String value, String appPackage) {
        List<WebElement> e = null;
        UISelectorType selector = UISelectorType.fromString(selectorTypeStr);

        switch (selector) {
            case RESOURCE_ID:
                e = driver.findElementsByAndroidUIAutomator("UiSelector().resourceId(\"" + appPackage + ":id/" + value + "\")");
                break;
            case TEXT:
                e = driver.findElementsByAndroidUIAutomator("UiSelector().text(\"" + value + "\")");
                break;
            case TEXT_CONTAINS:
                e = driver.findElementsByAndroidUIAutomator("UiSelector().textContains(\"" + value + "\")");
                break;
            case TEXT_START_WITH:
                e = driver.findElementsByAndroidUIAutomator("UiSelector().textStartsWith(\"" + appPackage + ":" + value + "\")");
                break;
            case SELECTED:
                e = driver.findElementsByAndroidUIAutomator("UiSelector().selected(" + value + ")");
                break;
        }

        return e;
    }

    /**
     * take screen shot
     *
     * @return File
     * @throws java.io.IOException
     */
    public static File takeScreenshot(String SCREENSHOT_PATH) {

        DateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
        //get current date time with Date()
        Date date = new Date();
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(scrFile, new File(SCREENSHOT_PATH + dateFormat.format(date).toString() + ".png"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            System.err.println(e);
        }

        return scrFile;
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

    //    /**
//     * Return an element that contains name or text *
//     */
//    public static MobileElement scroll_to(String value) {
//        return driver.scrollTo(value);
//    }
//
//    /**
//     * Return an element that exactly matches name or text *
//     */
//    public static MobileElement scroll_to_exact(String value) {
//        return driver.scrollToExact(value);
//    }
    public WebElement findByUISelectorType(UISelectorType selector, String value, String appPacket) {
        WebElement e = null;

        switch (selector) {
            case RESOURCE_ID:
                e = driver.findElementByAndroidUIAutomator("\"UiSelector().resourceId(\\\"" + appPacket + ":" + value + "\\\")\"");
                break;
            case TEXT:
                e = driver.findElementByAndroidUIAutomator("\"UiSelector().resourceId(\\\"" + appPacket + ":" + value + "\\\")\"");
                break;
            case TEXT_CONTAINS:
                e = driver.findElementByAndroidUIAutomator("\"UiSelector().resourceId(\\\"" + appPacket + ":" + value + "\\\")\"");
                break;
            case TEXT_START_WITH:
                e = driver.findElementByAndroidUIAutomator("\"UiSelector().resourceId(\\\"" + appPacket + ":" + value + "\\\")\"");
                break;

        }

        return e;
    }

    /**
     * Select option from dropdown list
     * @param locator By
     * @param index int
     */
    public static void selector(By locator, int index) {
        Select dropdown = new Select(driver.findElement(locator));
        dropdown.selectByIndex(index);
    }


    /**
     * Select random option from dropdown list
     * @param locator
     */
    public static void selectorRandom(By locator) {
        Random random = new Random();
        Select dropdown = new Select(driver.findElement(locator));
        List<WebElement> arrOption = dropdown.getOptions();
        int randomNumber = 1 + random.nextInt(arrOption.size() - 1);
        dropdown.selectByIndex(randomNumber);
    }
}

