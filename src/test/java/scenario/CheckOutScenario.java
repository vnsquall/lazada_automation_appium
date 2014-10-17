package scenario;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import util.AppiumSetupTest;
import org.testng.asserts.Assertion;
import java.util.List;
import java.util.Random;
import java.util.Set;

import static util.Helper.*;

/**
 * Created by lazhcm10136 on 9/30/14.
 */
public class CheckOutScenario extends AppiumSetupTest {

    protected void checkOut(String venture, String menuWiz, String categories, String filterWiz, String prodWiz) throws InterruptedException {
        selectVenture(venture, menuWiz);
//        find(appPackage + ":id/abs__home").click();
        findByUISelector("resourceID","abs__home",appPackage).click();

        Thread.sleep(1000);
        randomSelectProduct(categories, appPackage, filterWiz, prodWiz);
        findByUISelector("resourceID", "shop", appPackage).click(); //Add to Cart button

        //Check for Variant Selection
        Boolean cartConfirm = isElementPresent(By.id(appPackage + ":id/button1"));
        if (cartConfirm) {
            driver.findElement(By.id(appPackage + ":id/button1")).click();
        } else {
            driver.findElement(By.id(appPackage + ":id/product_variant_button")).click();
            randClick(By.id("pt.rocket.lazada.dev:id/item_text"));
            findByUISelector("resourceID", "shop", appPackage).click();
        }

        find(appPackage + ":id/checkout_button").click();

        //Login to CheckOut
        List<WebElement> editTextList = driver.findElements(By.className("android.widget.EditText"));
//        editTextList.get(0).click();
        editTextList.get(0).sendKeys("qa000@mail.com");
//        editTextList.get(1).click();
        editTextList.get(1).sendKeys("a12345");

        driver.findElement(By.className("android.widget.CheckBox")).click();
        find(appPackage + ":id/middle_login_button_signin").click();

        Thread.sleep(5000);
        wait_web(By.id(appPackage + ":id/rocket_app_checkoutweb"));
        driver.findElement(By.id(appPackage + ":id/rocket_app_checkoutweb"));
        Thread.sleep(2000);
        Set<String> contextNames = driver.getContextHandles();
        for (String contextName : contextNames) {
            if (contextName.contains("WEBVIEW")) {
                driver.context(contextName); // set context to WEBVIEW_$
            }
        }
        Thread.sleep(3000);
        //Find the orange-button, name 'submit'
//        driver.findElement(By.xpath("//button[@name='submit']")).click();
        driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/form/div[3]/button")).click();
        Thread.sleep(3000);
    }

    protected void checkOutAndUseTheCoD(String venture, String menuWiz, String categories, String filterWiz, String prodWiz) throws InterruptedException {
        // Perform Check Out steps
        checkOut(venture, menuWiz, categories, filterWiz, prodWiz);
        Thread.sleep(1000);
        // Check the Cash On Delivery is available or not for this CheckOutTest
        if (isElementPresent(By.xpath("//label[@for='cashondelivery']"))) {

            driver.findElement(By.xpath("//label[@for='cashondelivery']")).click();
            driver.findElement(By.xpath("//button[@class='orange-button']")).click();
            Thread.sleep(3000);
            driver.findElement(By.xpath("//input[@name='sendFinish']")).click();

            driver.getContextHandles();
            Thread.sleep(2000);
            driver.context("NATIVE_APP");
            //Venture checking for validation successful text
            if (venture.equals("Singapore")) {
                find("Your order number is").isDisplayed();
            }
            if (venture.equals("Philippines")) {
                find("Your order number is").isDisplayed();
            }
            if (venture.equals("Vietnam")) {
                find("Mã đơn hàng của bạn là").isDisplayed();
            }
        }
    }

    protected void checkOutAndUseCreditCard(String venture, String menuWiz, String categories, String filterWiz, String prodWiz) throws InterruptedException {
        // Perform Check Out steps
        checkOut(venture, menuWiz, categories, filterWiz, prodWiz);

        // Check the Credit Cards is available or not for this CheckOutTest
        if (isElementPresent(By.xpath("//label[@class='creditcards']"))) {

            driver.findElement(By.xpath("//label[@class='creditcards']")).click();
            Thread.sleep(1000);
            // waiting & input the CC info
            driver.findElement(By.id("PaymentMethodForm_parameter_cc_number")).click();
            driver.findElement(By.id("PaymentMethodForm_parameter_cc_number")).sendKeys("4400123456784011");
            driver.findElement(By.id("PaymentMethodForm_parameter_cc_holder")).click();
            driver.findElement(By.id("PaymentMethodForm_parameter_cc_holder")).sendKeys("Mr Test");
            driver.findElement(By.id("PaymentMethodForm_parameter_cc_security_code")).click();
            driver.findElement(By.id("PaymentMethodForm_parameter_cc_security_code")).sendKeys("123");

            driver.findElement(By.id("month-selector-mobile")).click();
            driver.findElement(By.xpath("//option[@value='09']")).click();
            driver.findElement(By.id("year-selector-mobile")).click();
            driver.findElement(By.xpath("//option[@value='2016']")).click();

            driver.findElement(By.xpath("//button[@class='orange-button']")).click();
            Thread.sleep(1000);

            driver.findElement(By.xpath("//button[@class='orange-button']")).click(); //Place Order
            driver.findElement(By.xpath("//input[@name='sendFinish']")).click(); //Place Order

            driver.getContextHandles();
            Thread.sleep(2000);
            driver.context("NATIVE_APP");

            //Venture checking for validation successful text
            if (venture.equals("Singapore")) {
                find("Sorry, the Credit Card number you entered is Invalid").isDisplayed();
            }
            if (venture.equals("Philippines")) {
                find("The Credit Card number is not correct").isDisplayed();
            }
            if (venture.equals("Vietnam")) {
                find("Số thẻ tín dụng không đúng").isDisplayed();
            }
        }
    }

    protected void checkOutAndUseBankTransfer(String venture, String menuWiz, String categories, String filterWiz, String prodWiz) throws InterruptedException {
        // Perform Check Out steps
        checkOut(venture, menuWiz, categories, filterWiz, prodWiz);

        // Check the Bank transfer is available or not for this CheckOutTest
        if (isElementPresent(By.xpath("//label[@for='manualbanktransferid']"))) {
            // Choose pay method -> bank transfer
            driver.findElement(By.xpath("//label[@for='manualbanktransferid']")).click();
            Thread.sleep(1000);

            // Select bank name
            Select dropdown = new Select(driver.findElement(By.xpath("//*[@name='PaymentMethodForm[parameter][bankNamePrimary]']")));
            dropdown.selectByIndex(1);

            // Fill information and Submit
            driver.findElement(By.xpath("//*[@id='PaymentMethodForm_parameter_senderName']")).sendKeys("Mr Test");
            Thread.sleep(2000);
            driver.findElement(By.xpath("//input[@class='orange-button']")).click(); //Place Order

        }
    }

    // Only for staging
    protected void checkOutAndUsePaypal(String venture, String menuWiz, String categories, String filterWiz, String prodWiz) throws InterruptedException {
        // Perform Check Out steps
        checkOut(venture, menuWiz, categories, filterWiz, prodWiz);

        // Check the Paypal is available or not for this CheckOutTest
        if (isElementPresent(By.xpath("//label[@for='paypal']"))) {

            driver.findElement(By.xpath("//label[@for='paypal']")).click();
            Thread.sleep(1000);

            // Choose pay method -> Paypal
            driver.findElement(By.xpath("//*[@class='orange-button']")).click();// Click on Continue button
            driver.findElement(By.xpath("//*[@class='orange-button']")).click();// Place your order
            Thread.sleep(10000);


        }
    }


}