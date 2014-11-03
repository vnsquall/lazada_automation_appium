package scenario;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import util.AppiumSetupTest;

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
            randClick(By.id(appPackage + ":id/item_text"));
            findByUISelector("resourceID", "shop", appPackage).click();
        }

        find(appPackage + ":id/checkout_button").click();

        //Login to CheckOut
        List<WebElement> editTextList = driver.findElements(By.className("android.widget.EditText"));
        editTextList.get(0).click();
        editTextList.get(0).sendKeys("qa000@mail.com");
        editTextList.get(1).click();
        editTextList.get(1).sendKeys("a12345");

        // Random shipping address
        randClick(By.className("android.widget.CheckBox"));
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
        if (!isElementPresent(By.xpath("//*[@class='payment-method-option radio'and@value='CashOnDelivery'and@disabled='disabled']"))
                && isElementPresent(By.xpath("//label[@for='cashondelivery']"))) {

            driver.findElement(By.xpath("//label[@for='cashondelivery']")).click();
            driver.findElement(By.xpath("//button[@class='orange-button']")).click();
            Thread.sleep(3000);
            driver.findElement(By.xpath("//*[@class='orange-button']")).click();

            driver.getContextHandles();
            Thread.sleep(2000);
            driver.context("NATIVE_APP");
            // Venture checking for validation successful text - Thank you page
            if (venture.equals("Singapore")
                    || venture.equals("Philippines")
                    || venture.equals("Malaysia")
                    || venture.equals("Thailand")) {
                find("Your order number is").isDisplayed();
            }

            if (venture.equals("Vietnam")) {
                find("Mã đơn hàng của bạn là").isDisplayed();
            }

            if (venture.equals("Indonesia")) {
                find("Nomor order Anda adalah").isDisplayed();
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

            selector(By.xpath("//*[@id='PaymentMethodForm_parameter_cc_exp_month']"), 9);
            selector(By.xpath("//*[@id='PaymentMethodForm_parameter_cc_exp_year']"), 3);

            driver.findElement(By.xpath("//button[@class='orange-button']")).click();
            Thread.sleep(1000);

            driver.findElement(By.xpath("//button[@class='orange-button']")).click(); //Place Order
            driver.findElement(By.xpath("//*[@class='orange-button']")).click(); //Place Order

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

    protected void checkOutAndUseBankTransfer(String venture, String menuWiz, String categories, String filterWiz, String prodWiz,
                                              int bankIndex, String senderName) throws InterruptedException {
        // Perform Check Out steps
        checkOut(venture, menuWiz, categories, filterWiz, prodWiz);

        // Check the Bank transfer is available or not for this CheckOutTest
        if (isElementPresent(By.xpath("//label[@for='manualbanktransferid']"))) {

            // Choose pay method -> bank transfer
            driver.findElement(By.xpath("//label[@for='manualbanktransferid']")).click();
            Thread.sleep(1000);

            // Select bank name
            selector(By.xpath("//*[@name='PaymentMethodForm[parameter][bankNamePrimary]']"), bankIndex);

            // Fill information and Submit
            driver.findElement(By.xpath("//*[@id='PaymentMethodForm_parameter_senderName']")).sendKeys(senderName);
            Thread.sleep(2000);
            driver.findElement(By.xpath("//*[@class='orange-button']")).click(); //Place Order
            driver.findElement(By.xpath("//*[@class='orange-button']")).click(); //Place Order
            driver.findElement(By.xpath("//*[@class='orange-button']")).click(); //Place Order

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

    /**
     * Select random product -> add to Cart -> Login and No checkout
     * @param venture
     * @param menuWiz
     * @param categories
     * @param filterWiz
     * @param prodWiz
     * @throws InterruptedException
     */
    protected void addRandomProductToCart(String venture, String menuWiz, String categories,
                                          String filterWiz, String prodWiz) throws InterruptedException {
        selectVenture(venture, menuWiz);
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
            randClick(By.id(appPackage + ":id/item_text"));
            findByUISelector("resourceID", "shop", appPackage).click();
        }

        find(appPackage + ":id/checkout_button").click();

        //Login to CheckOut
        List<WebElement> editTextList = driver.findElements(By.className("android.widget.EditText"));
        editTextList.get(0).sendKeys("qa000@mail.com");
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

    }

    protected void checkOutCreateAddress(String venture, String menuWiz, String categories, String filterWiz,
                                         String prodWiz, String name, String address,
                                         String phoneNumber) throws InterruptedException {
        // Perform Check Out steps
        addRandomProductToCart(venture, menuWiz, categories, filterWiz, prodWiz);

        // Create new address
        swipeDown();
        driver.findElement(By.xpath("//*[@id='load-different-billing-mobile']")).click(); // Click New address

        driver.findElement(By.xpath("//*[@id='AddressForm_first_name']")).sendKeys(name);
        driver.findElement(By.xpath("//*[@id='AddressForm_address1']")).sendKeys(address);
        selectorRandom(By.xpath("//*[@id='AddressForm_location_0']")); // select random Region
        selectorRandom(By.xpath("//*[@id='AddressForm_location_1']")); // select random City
        selectorRandom(By.xpath("//*[@id='AddressForm_location_2']")); // select random Postcode
        driver.findElement(By.xpath("//*[@id='AddressForm_phone']")).sendKeys(phoneNumber);

        // Submit
        driver.findElement(By.xpath("//*[@class='orange-button']")).click();

        // Verify new address appears on delivery information page or Not
        String pageSource = driver.getPageSource();
        Assert.assertTrue(pageSource.contains(name));
        Assert.assertTrue(pageSource.contains(address));
        Assert.assertTrue(pageSource.contains(phoneNumber));

    }

    protected void checkOutEditAddress(String venture, String menuWiz, String categories, String filterWiz,
                                         String prodWiz,String editAddSuccess, String name, String address, String phoneNumber) throws InterruptedException {
        // Perform Check Out steps
        addRandomProductToCart(venture, menuWiz, categories, filterWiz, prodWiz);

        // Edit address
        swipeDown();
        List<WebElement> arrEditButton = driver.findElements(By.xpath("//*[@class='change-billing']")); // Click Edit address

        // Random click on Edit button
        Random random = new Random();
        int randomNumber = random.nextInt(arrEditButton.size());
        arrEditButton.get(randomNumber).click();

        // Edit information
        driver.findElement(By.xpath("//*[@id='AddressForm_first_name']")).clear();
        driver.findElement(By.xpath("//*[@id='AddressForm_first_name']")).sendKeys(name);
        driver.findElement(By.xpath("//*[@id='AddressForm_address1']")).clear();
        driver.findElement(By.xpath("//*[@id='AddressForm_address1']")).sendKeys(address);
        selectorRandom(By.xpath("//*[@id='AddressForm_location_0']")); // select random Region
        selectorRandom(By.xpath("//*[@id='AddressForm_location_1']")); // select random City
        selectorRandom(By.xpath("//*[@id='AddressForm_location_2']")); // select random Postcode
        driver.findElement(By.xpath("//*[@id='AddressForm_phone']")).clear();
        driver.findElement(By.xpath("//*[@id='AddressForm_phone']")).sendKeys(phoneNumber);

        // Save address
        driver.findElement(By.xpath("//*[@id='send']")).click();

        // Verify the edited address appears on delivery information page or Not
        String pageSource = driver.getPageSource();
        Assert.assertTrue(pageSource.contains(name));
        Assert.assertTrue(pageSource.contains(address));
        Assert.assertTrue(pageSource.contains(phoneNumber));

    }

    /**
     * Check out and check "Use different billing address"
     * @param venture
     * @param menuWiz
     * @param categories
     * @param filterWiz
     * @param prodWiz
     * @param editAddSuccess
     * @param name
     * @param address
     * @param phoneNumber
     * @throws InterruptedException
     */
    protected void checkOutBillingDifferentAddress(String venture, String menuWiz, String categories, String filterWiz,
                                                   String prodWiz, String editAddSuccess, String name, String address, String phoneNumber) throws InterruptedException {
        // Perform Check Out steps
        addRandomProductToCart(venture, menuWiz, categories, filterWiz, prodWiz);

        // Choose Use different billing address
        driver.findElement(By.xpath("//*[@for='ThreeStepBillingAddressForm_isSameShipping']")).click();
        driver.findElement(By.xpath("//*[@id='ThreeStepBillingAddressForm_first_name']")).sendKeys(name);
        driver.findElement(By.xpath("//*[@id='ThreeStepBillingAddressForm_address1']")).sendKeys(address);
        selectorRandom(By.xpath("//*[@id='ThreeStepBillingAddressForm_location_0']")); // select random Region
        selectorRandom(By.xpath("//*[@id='ThreeStepBillingAddressForm_location_1']")); // select random City
        selectorRandom(By.xpath("//*[@id='ThreeStepBillingAddressForm_location_2']")); // select random Postcode
        driver.findElement(By.xpath("//*[@id='ThreeStepBillingAddressForm_phone']")).sendKeys(phoneNumber);

        // Submit
        driver.findElement(By.xpath("//*[@class='orange-button']")).click();

    }

    protected void checkOutEditBillingAddress(String venture, String menuWiz, String categories, String filterWiz,
                                                   String prodWiz, String editAddSuccess, String name, String address, String phoneNumber) throws InterruptedException {
        // Perform Check Out steps
        checkOut(venture, menuWiz, categories, filterWiz, prodWiz);

        // Check the Cash On Delivery is available or not for this CheckOutTest
        if (!isElementPresent(By.xpath("//*[@class='payment-method-option radio'and@value='CashOnDelivery'and@disabled='disabled']"))
                && isElementPresent(By.xpath("//label[@for='cashondelivery']"))) {

            driver.findElement(By.xpath("//label[@for='cashondelivery']")).click();
            driver.findElement(By.xpath("//*[@class='orange-button']")).click();
            Thread.sleep(3000);
            driver.findElement(By.xpath("//*[@id='change-shipping'][contains(@href, 'billing')]")).click();

            // Edit billing address
            driver.findElement(By.xpath("//*[@id='ThreeStepBillingAddressForm_first_name']")).sendKeys(name);
            driver.findElement(By.xpath("//*[@id='ThreeStepBillingAddressForm_address1']")).sendKeys(address);
            if (venture == "Thailand" || venture == "Philippines"
                    || venture == "Malaysia" || venture == "Indonesia") {

                selectorRandom(By.xpath("//*[@id='ThreeStepBillingAddressForm_location_0']")); // select random Region
                selectorRandom(By.xpath("//*[@id='ThreeStepBillingAddressForm_location_1']")); // select random City
                selectorRandom(By.xpath("//*[@id='ThreeStepBillingAddressForm_location_2']")); // select random Postcode
                driver.findElement(By.xpath("//*[@id='ThreeStepBillingAddressForm_phone']")).sendKeys(phoneNumber);

            }
            if (venture == "Singapore") {
                driver.findElement(By.xpath("//*[@id='ThreeStepBillingAddressForm_postcode']")).sendKeys("759674");
                driver.findElement(By.xpath("//*[@id='ThreeStepBillingAddressForm_phone']")).sendKeys(phoneNumber);
            }
            if (venture == "Vietnam") {
                selectorRandom(By.xpath("//*[@id='ThreeStepBillingAddressForm_location_0']")); // select random Region
                selectorRandom(By.xpath("//*[@id='ThreeStepBillingAddressForm_location_1']")); // select random City
                driver.findElement(By.xpath("//*[@id='ThreeStepBillingAddressForm_phone']")).sendKeys(phoneNumber);
            }

            // Submit
            driver.findElement(By.xpath("//*[@class='orange-button']")).click();

            // Continue checking out
            driver.findElement(By.xpath("//label[@for='cashondelivery']")).click();
            driver.findElement(By.xpath("//*[@class='orange-button']")).click();
            Thread.sleep(4000);

            // Verify billing address has changed
            String pageSource = driver.getPageSource();
            Assert.assertTrue(pageSource.contains(name));
            Assert.assertTrue(pageSource.contains(address));
            Assert.assertTrue(pageSource.contains(phoneNumber));

            // Submit order
            driver.findElement(By.xpath("//*[@class='orange-button']")).click(); // Place your order

        }

    }

}