package util;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
import pageObject.ProductList_Screen;
import pageObject.SideMenu_Screen;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.NoSuchElementException;
import static util.Constant.*;

public class Helper {

    private AndroidDriver driver;

    public Helper(AndroidDriver driver) {
        this.driver = driver;
    }

    public void randClick(By locator) {

        Random rand = new Random();
        List<WebElement> we = driver.findElements(locator);
        int catNum = we.size();
        int randClick = rand.nextInt(catNum);
        we.get(randClick).click();
    }

    public void randClick(List<WebElement> elements) {

        Random rand = new Random();
        int catNum = elements.size();
        int randClick = rand.nextInt(catNum);
        elements.get(randClick).click();
    }

    public void randClick(String locator) {

        List<WebElement> elements = findElements(split(locator)[0], split(locator)[1]);
        randClick(elements);

    }

    public Boolean isElementPresent(String locator) {
        Boolean isPresent = Boolean.FALSE;
        try {
            isPresent = findElements(locator).size() > 0;
            return isPresent;
        } catch (NoSuchElementException ex) {
            return isPresent;
        }
    }

    public void swipeDown() {
        JavascriptExecutor scrollDown = driver;
        HashMap<String, Double> swipeDown = new HashMap<String, Double>();
        swipeDown.put("startX", 0.95);
        swipeDown.put("startY", 0.95);
        swipeDown.put("endX", 0.95);
        swipeDown.put("endY", 0.5);
        swipeDown.put("duration", 1.8);
        scrollDown.executeScript("mobile: swipe", swipeDown);
    }

    public void swipeUp() {
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
     * Return an element by locator *
     */
    public MobileElement element(By locator) {
        return (MobileElement)driver.findElement(locator);
    }

    /**
     * Press the back button *
     */
    public void back() {
        driver.navigate().back();
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
    public MobileElement text(String text) {
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
    public MobileElement text_exact(String text) {
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

    public MobileElement find(String value) {
        return element(for_find(value));
    }

//    public WebElement wait_web(By locator) {
//        return driverWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
//
//    }

    /**
     * Find element
     */
    public WebElement findElement(String locator) {

        WebElement e = findElement(split(locator)[0], split(locator)[1]);
        return e;
    }

    /**
     * Find list of element
     */
    public List<WebElement> findElements(String locator) {

        List<WebElement> e = findElements(split(locator)[0], split(locator)[1]);
        return e;
    }

    /**
     * find Element by UIAndroidSelector
     * @param selectorTypeStr
     * @param value
     * @return WebElement
     */
    public WebElement findElement(String selectorTypeStr, String value) {

        WebElement e = null;
        String appPackage = AppiumSetupTest.appPackage;
        UISelectorType selector = UISelectorType.fromString(selectorTypeStr);

        switch (selector) {
            case RESOURCE_ID:
                e = driver.findElement(By.id(appPackage + ":id/" + value));
                break;
            case TEXT:
                e = driver.findElement(By.xpath("//*[@text = '" + value + "']"));
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
    public List<WebElement> findElements(String selectorTypeStr, String value) {

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
    public File takeScreenshot(String SCREENSHOT_PATH) {

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

    public void randomSelectProduct(String categories, String filterWiz, String prodWiz) {
        text_exact(categories).click();

        // Random selection Categories
        randClick(SideMenu_Screen.categories);
        text_exact(filterWiz).click();

        // Random selection sub-Categories
        randClick(SideMenu_Screen.subCategories);

        // Get back to the Main Screen for viewing the product
        ProductList_Screen productList_screen = new ProductList_Screen(driver);
        randClick(productList_screen.containers()); // Go to product list
        randClick(productList_screen.containers()); // Select randomly product
        text_exact(prodWiz).click();
    }

    /**
     * Select option from dropdown list
     * @param locator String
     * @param index int
     */
    public void selector(String locator, int index) {

        Select dropdown = new Select(findElement(locator));
        dropdown.selectByIndex(index);
    }

    /**
     * Select random option from dropdown list
     * @param webElement WebElement
     */
    public void selectorRandom(WebElement webElement) throws InterruptedException {

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
    public void switchToNativeApp () {

        Set<String> contextNames = driver.getContextHandles();
        for (String contextName : contextNames) {
            if (contextName.contains("NATIVE")) {
                System.out.println(">>> Set context to: " + contextName);
                driver.context(contextName);
            }
        }
    }

    /**
     * Set context to WEB_VIEW: true: success, false: No web view
     */
    public boolean switchToWebView () {

        boolean isSwitched = false;
        String webviewContext = "";
        if (AppiumSetupTest.appPackage.equals(APP_PACKAGE_LIVE)) {

            webviewContext = WEBVIEW_LIVE;


        }else {

            webviewContext = WEBVIEW_STAGING;

        }

        Set<String> contextNames = driver.getContextHandles();
        for (String contextName : contextNames) {
            if (contextName.contains(webviewContext)) {
                System.out.println(">>> Set context to: " + contextName);
                driver.context(contextName);
                isSwitched = true;
                return isSwitched;
            }
        }
        return isSwitched;

    }

    /**
     * Generate an email
     * @return email String
     */
    public String generateEmail() {

        String email = generateAlphabet(8) + generateNumber(4);
        email = email + "@mail.com";
        return email;

    }

    /**
     * Generate password
     * @return String
     */
    public String generatePassword() {

        Random random = new Random();
        String password =  generateAlphabet(3 + random.nextInt(5)) + generateNumber(3);
        return password;
    }

    /**
     * Generate array of alphabet
     * @return String
     */
    public String generateAlphabet(int length) {

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
    public String generateNumber(int length) {

        String numbers  = "0123456789";
        String randomNumber = "";
        Random random = new Random();
        for (int i = 0; i < length ; i++) {

            randomNumber = randomNumber + numbers.charAt(random.nextInt(numbers.length()));

        }

        return randomNumber;
    }

    /**
     * Split a string by "::"
     * @return array of String
     */
    public static String[] split(String str) {

        String[] parts = str.split("::");
        return parts;
    }

    public MobileElement find_TextView_Android(String text) {
        MobileElement me = element(MobileBy.xpath("//android.widget.TextView[contains(@text, '" + text + "')]"));
        return me;
    }

    public MobileElement find_ButtonText_Android(String text) {
        MobileElement me = element(MobileBy.xpath("//android.widget.Button[contains(@text, '" + text + "')]"));
        return me;
    }

    /**
     * Check page contains text or Not
     */
    public Boolean isPageContains(String text) {

        String pageSource = "";
        try {
            pageSource = driver.getPageSource();
            return pageSource.contains(text);
        } catch (WebDriverException e) {
            System.out.println(e);
        }
        return pageSource.contains(text);

    }

    /**
     * Check error choose size message
     */
    public boolean hasSizeErrorMgs() throws InterruptedException {

        Thread.sleep(2000);
        return isPageContains("product_variant_choose_error");
//        return isPageContains("product_variant_text");

    }

    /**
     * Tap on an element
     */
    public void tapOnElement(WebElement element) {

        driver.tap(1, element, 2000);
    }

    /**
     * Hide android keyBoard
     */
    public void hideKeyBoard() {

        driver.hideKeyboard();
    }

}

