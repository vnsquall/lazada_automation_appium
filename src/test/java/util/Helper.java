package util;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
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
import static util.Constant.*;

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

    public static void randClick(String locator) {
        Random rand = new Random();
        List<WebElement> we = findElements(split(locator)[0], split(locator)[1]);
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

    public static Boolean isElementPresent(String locator) {
        Boolean isPresent = Boolean.FALSE;
        try {
            isPresent = findElements(split(locator)[0], split(locator)[1]).size() > 0;
            return isPresent;
        } catch (NoSuchElementException ex) {
            return isPresent;
        }
    }

    public static WebElement find_xpath_forText(String appPackage, String id, String text) {
        WebElement we = driver.findElement(By.xpath("//android.widget.TextView[@resource-id='" + appPackage + id + "'and @text='" + text + "']"));
        return we;
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
        return (MobileElement)driver.findElement(locator);
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
//    public static MobileElement wait(By locator) {
//        return w(driverWait.until(ExpectedConditions.visibilityOfElementLocated(locator)));
//
//    }

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
     * Find element
     */
    public static WebElement findElement(String locator) {

        WebElement e = findElement(split(locator)[0], split(locator)[1]);
        return e;
    }

    /**
     * find Element by UIAndroidSelector
     *
     * @param selectorTypeStr
     * @param value
     * @return WebElement
     */
    public static WebElement findElement(String selectorTypeStr, String value) {

        WebElement e = null;
        String appPackage = AppiumSetupTest.appPackage;
        UISelectorType selector = UISelectorType.fromString(selectorTypeStr);

        switch (selector) {
            case RESOURCE_ID:
                e = driver.findElement(By.id(appPackage + ":id/" + value));
                break;
            case TEXT:
                e = driver.findElement(By.xpath("//*[@text = '"+ value +"']"));
                break;
            case TEXT_CONTAINS:
                e = driver.findElement(By.xpath("//*[contains(@text, '" + value + "')]"));
                break;
            case TEXT_START_WITH:
                e = driver.findElement(By.xpath("//*[contains(@text, '" + value + "')]"));
                break;
            case CLASS_NAME:
                e = driver.findElement(By.className(value));
                break;
            case XPATH:
                e = driver.findElement(By.xpath(value));
                break;
        }

        return e;
    }

    /**
     * find Element by UIAndroidSelector
     * @param selectorTypeStr
     * @param value
     * @return List of Element
     */
    public static List<WebElement> findElements(String selectorTypeStr, String value) {

        List<WebElement> e = null;
        String appPackage = AppiumSetupTest.appPackage;
        UISelectorType selector = UISelectorType.fromString(selectorTypeStr);

        switch (selector) {
            case RESOURCE_ID:
                e = driver.findElements(By.id(appPackage + ":id/" + value));
                break;
            case TEXT:
                e = driver.findElements(By.xpath("//*[@text = '" + value + "']"));
                break;
            case TEXT_CONTAINS:
                e = driver.findElements(By.xpath("//*[contains(@text, '" + value + "')]"));
                break;
            case TEXT_START_WITH:
                e = driver.findElements(By.xpath("//*[contains(@text, '" + value + "')]"));
                break;
            case CLASS_NAME:
                e = driver.findElements(By.className(value));
                break;
            case XPATH:
                e = driver.findElements(By.xpath(value));
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
     * Select option from dropdown list
     * @param locator String
     * @param index int
     */
    public static void selector(String locator, int index) {

        Select dropdown = new Select(findElement(split(locator)[0], split(locator)[1]));
        dropdown.selectByIndex(index);
    }


    /**
     * Select random option from dropdown list
     * @param locator
     */
    public static void selectorRandom(By locator) {

        if (driver.getContext().contains("WEBVIEW")) {
            Random random = new Random();
            Select dropdown = new Select(driver.findElement(locator));
            List<WebElement> arrOption = dropdown.getOptions();
            int randomNumber = 1 + random.nextInt(arrOption.size() - 1);
            dropdown.selectByIndex(randomNumber);

        } else { // NATIVE APP

            driver.findElement(locator).click();
            randClick(By.xpath("//*[@class='android.widget.TextView']"));

        }
    }

    /**
     * Select random option from dropdown list
     * @param webElement WebElement
     */
    public static void selectorRandom(WebElement webElement) throws InterruptedException {

        if (driver.getContext().contains("WEBVIEW")) {

            Random random = new Random();
            Select dropdown = new Select(webElement);
            List<WebElement> arrOption = dropdown.getOptions();
            int randomNumber = 1 + random.nextInt(arrOption.size() - 1);
            dropdown.selectByIndex(randomNumber);
        } else { // NATIVE APP
            webElement.click();
            Thread.sleep(3000);
            randClick(By.xpath("//*[@class='android.widget.TextView']"));
        }
    }

    /**
     * Set context to NATIVE_APP
     */
    public static void switchToNativeApp () {

        Set<String> contextNames = driver.getContextHandles();
        for (String contextName : contextNames) {
            if (contextName.contains("NATIVE")) {
                System.out.println(">>> Set context to NATIVE APP!!!");
                driver.context(contextName);
            }
        }
    }

    /**
     * Set context to WEB_VIEW
     */
    public static void switchToWebView () {

        String webviewContext = "";
        if (AppiumSetupTest.appPackage.equals(APP_PACKAGE_LIVE)) {

            webviewContext = WEBVIEW_LIVE;


        }else {

            webviewContext = WEBVIEW_STAGING;

        }

        Set<String> contextNames = driver.getContextHandles();
        for (String contextName : contextNames) {
            if (contextName.contains(webviewContext)) {
                System.out.println(">>> Set context to WEBVIEW: " + contextName);
                driver.context(contextName);
            }
        }

    }

    /**
     * Generate an email
     * @return email String
     */
    public static String generateEmail() {

        String email = generateAlphabet(8) + generateNumber(4);
        email = email + "@mail.com";
        return email;

    }

    /**
     * Generate password
     * @return String
     */
    public static String generatePassword() {

        Random random = new Random();
        String password =  generateAlphabet(3 + random.nextInt(5)) + generateNumber(3);
        return password;
    }

    /**
     * Generate array of alphabet
     * @return String
     */
    public static String generateAlphabet(int length) {

        String alphabet  = "abcdefghijklmnopqrstuvwxyz";
        String randomAlphabet = "";
        Random random = new Random();
        for (int i = 0; i < length ; i++) {

            randomAlphabet = randomAlphabet + alphabet.charAt(random.nextInt(alphabet.length()));

        }

        return randomAlphabet;
    }

    /**
     * Generate array of Number
     * @param length int
     * @return String
     */
    public static String generateNumber(int length) {

        String numbers  = "0123456789";
        String randomNumber = "";
        Random random = new Random();
        for (int i = 0; i < length ; i++) {

            randomNumber = randomNumber + numbers.charAt(random.nextInt(numbers.length()));

        }

        return randomNumber;
    }

    public static String[] split(String str) {

        String[] parts = str.split("::");
        return parts;
    }

    public static MobileElement find_TextView_Android(String text) {
        MobileElement me = element(MobileBy.xpath("//android.widget.TextView[contains(@text, '" + text + "')]"));
        return me;
    }

    public static MobileElement find_ButtonText_Android(String text) {
        MobileElement me = element(MobileBy.xpath("//android.widget.Button[contains(@text, '" + text + "')]"));
        return me;
    }



}

