package scenario;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import util.AppiumSetupTest;

import java.util.List;
import java.util.Set;

import static util.Helper.*;

/**
 * Created by lazhcm10136 on 9/30/14.
 */
public class CheckOutScenario extends AppiumSetupTest {

    protected void checkOut(String venture, String menuWiz, String categories, String filterWiz, String prodWiz) throws InterruptedException {
        selectVenture(venture, menuWiz);
        find(appPackage + ":id/abs__home").click();

        Thread.sleep(1000);
        randomSelectProduct(categories, appPackage, filterWiz, prodWiz);
        find(appPackage + ":id/shop").click(); //Add to Cart button

        //Check for Variant Selection
        Boolean productVar = isElementPresent(By.id(appPackage + ":id/product_variant_container"));
        if (productVar) {
            driver.findElement(By.id(appPackage + ":id/product_variant_button")).click();
            randClick(By.id("pt.rocket.lazada.dev:id/item_text"));
            find(appPackage + ":id/shop").click();
        }

        find(appPackage + ":id/button1").click();

        //Swipe down
        swipe();

        find(appPackage + ":id/checkout_button").click();

        //Login to CheckOut
        List<WebElement> editTextList = driver.findElements(By.className("android.widget.EditText"));
        editTextList.get(0).click();
        editTextList.get(0).sendKeys("qa000@mail.com");
        editTextList.get(1).click();
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
        Thread.sleep(2000);
        wait_web(By.xpath("//button[@class='orange-button']"));
        driver.findElement(By.xpath("//button[@class='orange-button']")).click();
        Thread.sleep(2000);
    }

    protected void checkOutAndUseTheCoD(String venture, String menuWiz, String categories, String filterWiz, String prodWiz) throws InterruptedException {
        // Perform Check Out steps
        checkOut(venture, menuWiz, categories, filterWiz, prodWiz);

        // Check the Cash On Delivery is available or not for this CheckOutTest
        if (isElementPresent(By.xpath("//label[@for='cashondelivery']"))) {

            driver.findElement(By.xpath("//label[@for='cashondelivery']")).click();
            driver.findElement(By.xpath("//button[@class='orange-button']")).click();
            Thread.sleep(3000);
            driver.findElement(By.xpath("//input[@name='sendPayment']")).click();

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
}