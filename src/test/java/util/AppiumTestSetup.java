package util;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import static util.Helper.*;

/**
 * Created by lazhcm10136 on 9/30/14.
 */
public class AppiumTestSetup {


    public void init() throws MalformedURLException {
        DesiredCapabilities capabilities = getAndroid4_4_capabilities();
        URL serverAddress = new URL("http://127.0.0.1:4723/wd/hub");
        driver = new AppiumDriver(serverAddress, capabilities);

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        Helper.init(driver, serverAddress);
    }

    private DesiredCapabilities getAndroid4_4_capabilities() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
        capabilities.setCapability("automationName", "Selendroid");
        capabilities.setCapability("appium-version", "1.2.3");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("platformVersion", "4.4");
        capabilities.setCapability("deviceName", "Android Emulator");
//        capabilities.setCapability("appPackage", "pt.rocket.lazada.dev");
//        capabilities.setCapability("appActivity", "pt.rocket.view.SplashScreenActivity");
        capabilities.setCapability("automationName", "Appium");

//        capabilities.setCapability("platformVersion", "4.3");
        String userDir = System.getProperty("user.dir");

        URL serverAddress;
        String localApp = "lazada-android-dev.apk";

        String appPath = Paths.get(userDir, localApp).toAbsolutePath().toString();
        capabilities.setCapability("app", appPath);
        return capabilities;
    }

    protected void checkOutAndUsingTheCashDelivery(String venture, String categoryName, String subCategoryName) throws InterruptedException {
        driver.findElement(By.xpath(("//android.widget.TextView[contains(@text, '" + venture + "')]"))).click();

        Thread.sleep(2000);
        text("Tap to open the menu").click();
        find("pt.rocket.lazada.dev:id/abs__home").click();

        Thread.sleep(1000);
        text_exact(categoryName).click();
        text_exact(subCategoryName).click();
        text_exact("Select the filters you want and tap apply").click();
//        text("Tablets").click(); // Only using for "Mobile & Tablets" category

        /*
        Get size of sub-category and randomly click on it
         */
        List<WebElement> meCatText = driver.findElements(By.id("pt.rocket.lazada.dev:id/text"));
        int catNum = meCatText.size();
        Random rand = new Random();
        int randNum = rand.nextInt((catNum - 1) + 1) + 1;
        meCatText.get(randNum).click();
        /*
        Get back to the Main Screen
         */
        find("pt.rocket.lazada.dev:id/general_container").click();
        find("pt.rocket.lazada.dev:id/general_container").click();

        text_exact("Tap to open the product gallery").click();

//        if(ExpectedConditions.visibilityOf(By.xpath(""))){
//
//        }

        find("pt.rocket.lazada.dev:id/shop").click();
        find("pt.rocket.lazada.dev:id/button1").click();
        find("pt.rocket.lazada.dev:id/checkout_button").click();

        //Login to CheckOut

        List<WebElement> editTextList = driver.findElements(By.className("android.widget.EditText"));
        editTextList.get(0).click();
        editTextList.get(0).sendKeys("qa000@mail.com");
//        driver.hideKeyboard(); // Need on read devices
        editTextList.get(1).click();
        editTextList.get(1).sendKeys("a12345");
//        driver.hideKeyboard(); // Need on read devices

        find("Show Password").click();
        find("pt.rocket.lazada.dev:id/middle_login_button_signin").click();

        Thread.sleep(4000);
        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("pt.rocket.lazada.dev:id/rocket_app_checkoutweb")));
        driver.findElement(By.id("pt.rocket.lazada.dev:id/rocket_app_checkoutweb"));

        driver.getContextHandles();
        Thread.sleep(2000);
        driver.getContext();
        driver.context("WEBVIEW_pt.rocket.lazada.dev");
        driver.findElement(By.className("submit_btn_text")).click();
        Thread.sleep(2000);
        /*
        ** Only for testing the element, not need to be executed
        driver.findElement(By.id("form-account-payment")).click();
        driver.findElement(By.xpath("//li[@class='payment-methods for-cashondelivery']")).click();
        */
        driver.findElement(By.xpath("//label[@for='cashondelivery']")).click();
        driver.findElement(By.xpath("//button[@class='orange-button']")).click();

        wait_web(By.xpath("//input[@name='sendPayment']"));

        driver.findElement(By.xpath("//input[@name='sendPayment']")).click();

        driver.getContextHandles();
        Thread.sleep(2000);
        driver.context("NATIVE_APP");
//        driver.findElement(By.xpath("//div[@class='description-order' and ./p[contains(., 'Your order with the number')]]")).isDisplayed();
        find("Your order number is").isDisplayed();
    }

}
