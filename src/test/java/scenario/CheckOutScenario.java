package scenario;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import util.AppiumSetupTest;
import static util.Constant.*;
import java.util.List;
import java.util.Random;

import static util.Helper.*;

/**
 * Created by lazhcm10136 on 9/30/14.
 */
public class CheckOutScenario extends AppiumSetupTest {

    protected void checkOut(String categories, String filterWiz, String prodWiz) throws InterruptedException {

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
            Thread.sleep(2000);
        }

        find(appPackage + ":id/checkout_button").click();

        // Login as default account
        loginAs(USERNAME, PASSWORD);
        driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/form/div[3]/button")).click();
        Thread.sleep(3000);
    }

    protected void checkOutUseTheCoD(String venture, String menuWiz, String categories, String filterWiz, String prodWiz) throws InterruptedException {
        // Perform Check Out steps
        selectVenture(venture, menuWiz);
        checkOut(categories, filterWiz, prodWiz);
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

    protected void checkOutUseCreditCardInvalid(String venture, String menuWiz, String categories, String filterWiz,
                                                String prodWiz, String creditNumber, String customerName, String securityCode) throws InterruptedException {
        // Perform Check Out steps
        selectVenture(venture, menuWiz);
        checkOut(categories, filterWiz, prodWiz);

        // Check the Credit Cards is available or not for this CheckOutTest
        if (isElementPresent(By.xpath("//label[@class='creditcards']"))) {

            driver.findElement(By.xpath("//label[@class='creditcards']")).click();
            Thread.sleep(1000);

            // waiting & input the CC info
            driver.findElement(By.id("PaymentMethodForm_parameter_cc_number")).click();
            driver.findElement(By.id("PaymentMethodForm_parameter_cc_number")).sendKeys(creditNumber);
            driver.findElement(By.id("PaymentMethodForm_parameter_cc_holder")).click();
            driver.findElement(By.id("PaymentMethodForm_parameter_cc_holder")).sendKeys(customerName);
            driver.findElement(By.id("PaymentMethodForm_parameter_cc_security_code")).click();
            driver.findElement(By.id("PaymentMethodForm_parameter_cc_security_code")).sendKeys(securityCode);
            selectorRandom(By.xpath("//*[@id='PaymentMethodForm_parameter_cc_exp_month']"));
            selectorRandom(By.xpath("//*[@id='PaymentMethodForm_parameter_cc_exp_year']"));
            driver.findElement(By.xpath("//button[@class='orange-button']")).click();
            Thread.sleep(1000);
            driver.findElement(By.xpath("//button[@class='orange-button']")).click(); //Place Order
            driver.findElement(By.xpath("//*[@class='orange-button']")).click(); //Place Order
            Thread.sleep(2000);
            switchToNativeApp();

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

    protected void checkOutUseBankTransfer(String venture, String menuWiz, String categories, String filterWiz, String prodWiz,
                                           int bankIndex, String senderName) throws InterruptedException {
        // Perform Check Out steps
        selectVenture(venture, menuWiz);
        checkOut(categories, filterWiz, prodWiz);
        bankTransfer(bankIndex, senderName);
    }

    /**
     * Choose bank transfer and place the order
     */
    protected void bankTransfer (int bankIndex, String senderName) throws InterruptedException {

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
        selectVenture(venture, menuWiz);
        checkOut(categories, filterWiz, prodWiz);

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
     *
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

    }

    /**
     * Login as
     */
    protected void loginAs( String userName, String password) throws InterruptedException {
        //Login to CheckOut
        List<WebElement> editTextList = driver.findElements(By.className("android.widget.EditText"));
        editTextList.get(0).sendKeys(userName);
        editTextList.get(1).sendKeys(password);

        driver.findElement(By.className("android.widget.CheckBox")).click();
        find(appPackage + ":id/middle_login_button_signin").click();

        Thread.sleep(5000);
        wait_web(By.id(appPackage + ":id/rocket_app_checkoutweb"));
        driver.findElement(By.id(appPackage + ":id/rocket_app_checkoutweb"));
        Thread.sleep(2000);
        switchToWebView();
        Thread.sleep(3000);

    }

    protected void checkOutCreateAddress(String venture, String menuWiz, String categories, String filterWiz,
                                         String prodWiz, String name, String address,
                                         String phoneNumber) throws InterruptedException {
        // Perform Check Out steps
        addRandomProductToCart(venture, menuWiz, categories, filterWiz, prodWiz);
        loginAs(USERNAME, PASSWORD);

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
                                       String prodWiz, String name, String address, String phoneNumber) throws InterruptedException {
        // Perform Check Out steps
        addRandomProductToCart(venture, menuWiz, categories, filterWiz, prodWiz);
        loginAs(USERNAME, PASSWORD);

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
     *
     */
    protected void checkOutBillingDifferentAddress(String venture, String menuWiz, String categories, String filterWiz,
                                                   String prodWiz, String name, String address, String phoneNumber) throws InterruptedException {
        // Perform Check Out steps
        addRandomProductToCart(venture, menuWiz, categories, filterWiz, prodWiz);
        loginAs(USERNAME, PASSWORD);

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
                                              String prodWiz, String name, String address, String phoneNumber) throws InterruptedException {
        // Perform Check Out steps
        selectVenture(venture, menuWiz);
        checkOut(categories, filterWiz, prodWiz);

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
            if (venture.equals("Thailand") || venture.equals("Philippines")
                    || venture.equals("Malaysia") || venture.equals("Indonesia")) {

                selectorRandom(By.xpath("//*[@id='ThreeStepBillingAddressForm_location_0']")); // select random Region
                selectorRandom(By.xpath("//*[@id='ThreeStepBillingAddressForm_location_1']")); // select random City
                selectorRandom(By.xpath("//*[@id='ThreeStepBillingAddressForm_location_2']")); // select random Postcode
                driver.findElement(By.xpath("//*[@id='ThreeStepBillingAddressForm_phone']")).sendKeys(phoneNumber);

            }
            if (venture.equals("Singapore")) {
                driver.findElement(By.xpath("//*[@id='ThreeStepBillingAddressForm_postcode']")).sendKeys("759674");
                driver.findElement(By.xpath("//*[@id='ThreeStepBillingAddressForm_phone']")).sendKeys(phoneNumber);
            }
            if (venture.equals("Vietnam")) {
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

    protected void checkOutEditShippingAddress(String venture, String menuWiz, String categories, String filterWiz,
                                               String prodWiz, String name, String address, String phoneNumber) throws InterruptedException {
        // Perform Check Out steps
        selectVenture(venture, menuWiz);
        checkOut(categories, filterWiz, prodWiz);

        // Check the Cash On Delivery is available or not for this CheckOutTest
        if (!isElementPresent(By.xpath("//*[@class='payment-method-option radio'and@value='CashOnDelivery'and@disabled='disabled']"))
                && isElementPresent(By.xpath("//label[@for='cashondelivery']"))) {

            driver.findElement(By.xpath("//label[@for='cashondelivery']")).click();
            driver.findElement(By.xpath("//*[@class='orange-button']")).click();
            Thread.sleep(3000);
            driver.findElement(By.xpath("//*[@id='change-shipping'][contains(@href, 'shipping')]")).click();

            // Random choose address to edit
            randClick(By.xpath("//*[@class='change-billing']"));

            // Edit billing address
            driver.findElement(By.xpath("//*[@id='ThreeStepBillingAddressForm_first_name']")).sendKeys(name);
            driver.findElement(By.xpath("//*[@id='ThreeStepBillingAddressForm_address1']")).sendKeys(address);
            if (venture.equals("Thailand") || venture.equals("Philippines")
                    || venture.equals("Malaysia") || venture.equals("Indonesia")) {

                selectorRandom(By.xpath("//*[@id='ThreeStepBillingAddressForm_location_0']")); // select random Region
                selectorRandom(By.xpath("//*[@id='ThreeStepBillingAddressForm_location_1']")); // select random City
                selectorRandom(By.xpath("//*[@id='ThreeStepBillingAddressForm_location_2']")); // select random Postcode
                driver.findElement(By.xpath("//*[@id='ThreeStepBillingAddressForm_phone']")).sendKeys(phoneNumber);

            }
            if (venture.equals("Singapore")) {
                driver.findElement(By.xpath("//*[@id='ThreeStepBillingAddressForm_postcode']")).sendKeys("759674");
                driver.findElement(By.xpath("//*[@id='ThreeStepBillingAddressForm_phone']")).sendKeys(phoneNumber);
            }
            if (venture.equals("Vietnam")) {
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

    /**
     * Remove all product from myCart
     * @param venture String
     * @param menuWiz String
     * @param categories String
     * @param filterWiz String
     * @param prodWiz String
     * @throws InterruptedException
     */
    protected void checkOutRemoveFromCart(String venture, String menuWiz, String categories, String filterWiz,
                                          String prodWiz) throws InterruptedException {


        // Perform Check Out steps
        addRandomProductToCart(venture, menuWiz, categories, filterWiz, prodWiz);
        loginAs(USERNAME, PASSWORD);
        switchToNativeApp();

        // Go to myCart and remove all product
        driver.findElement(By.xpath("//*[contains(@resource-id, 'id/cart_count')]")).click();
        List<WebElement> arrDelete = null;
        do {
            arrDelete = findsByUISelector("resourceID","delete_button", appPackage);
            if (arrDelete.size() > 0) {

                arrDelete.get(0).click();
                findByUISelector("resourceID" , "button1", appPackage).click(); // Click on Remove button

            }
            arrDelete = findsByUISelector("resourceID","delete_button", appPackage);
        } while (arrDelete.size() > 0);

    }

    protected void checkOutEditPaymentMethod(String venture, String menuWiz, String categories, String filterWiz,
                                             String prodWiz) throws InterruptedException {


        // Perform Check Out steps
        selectVenture(venture, menuWiz);
        checkOut(categories, filterWiz, prodWiz);

        // Check out by COD then edit payment method
        if (!isElementPresent(By.xpath("//*[@class='payment-method-option radio'and@value='CashOnDelivery'and@disabled='disabled']"))
                && isElementPresent(By.xpath("//label[@for='cashondelivery']"))) {

            driver.findElement(By.xpath("//label[@for='cashondelivery']")).click();
            driver.findElement(By.xpath("//button[@class='orange-button']")).click();
            Thread.sleep(3000);

            // Change payment method
            driver.findElement(By.xpath("//*[@id='change-payment']")).click();
            randClick(By.xpath("//*[starts-with(@class,'payment-methods')]"));
            driver.findElement(By.xpath("//*[@class='orange-button']")).click();
            Thread.sleep(2000);

      }

    }

    protected void checkOutCoupon(String venture, String menuWiz, String categories, String filterWiz,
                                  String prodWiz, String coupon) throws InterruptedException {

        // Perform Check Out steps
        selectVenture(venture, menuWiz);
        checkOut(categories, filterWiz, prodWiz);

        // Check out by COD
        if (!isElementPresent(By.xpath("//*[@class='payment-method-option radio'and@value='CashOnDelivery'and@disabled='disabled']"))
                && isElementPresent(By.xpath("//label[@for='cashondelivery']"))) {

            driver.findElement(By.xpath("//label[@for='cashondelivery']")).click();
            driver.findElement(By.xpath("//button[@class='orange-button']")).click();
            Thread.sleep(3000);

            // Input coupon code
            driver.findElement(By.xpath("//*[@id='addCoupon']")).click();
            driver.findElement(By.xpath("//*[@id='coupon']")).sendKeys(coupon);
            driver.findElement(By.xpath("//*[@id='couponSend']")).click();
            driver.findElement(By.xpath("//*[@class='orange-button']")).click(); // Place your oder
            Thread.sleep(2000);

      }
    }

    protected void checkOutShareOrder(String venture, String menuWiz, String categories, String filterWiz,
                                      String prodWiz) throws InterruptedException {

        // Perform Check Out steps
        selectVenture(venture, menuWiz);
        checkOut(categories, filterWiz, prodWiz);
        Thread.sleep(1000);

        // Check the Cash On Delivery is available or not for this CheckOutTest
        if (!isElementPresent(By.xpath("//*[@class='payment-method-option radio'and@value='CashOnDelivery'and@disabled='disabled']"))
                && isElementPresent(By.xpath("//label[@for='cashondelivery']"))) {

            driver.findElement(By.xpath("//label[@for='cashondelivery']")).click();
            driver.findElement(By.xpath("//button[@class='orange-button']")).click();
            Thread.sleep(3000);
            driver.findElement(By.xpath("//*[@class='orange-button']")).click();
            Thread.sleep(2000);
            switchToNativeApp();

            // Share order
            findByUISelector("resourceID", "btn_checkout_share", appPackage).click();
            randClick(By.xpath("//*[@resource-id='android:id/text1']"));

        }
    }

    protected void checkOutSavedCreditCard(String venture, String menuWiz, String categories, String filterWiz,
                                           String prodWiz) throws InterruptedException {
        // Perform Check Out steps
        selectVenture(venture, menuWiz);
        checkOut(categories, filterWiz, prodWiz);

        // Check the Saved Credit Cards is available or not
        if (isElementPresent(By.xpath("//*[starts-with(@for,'card_id')]"))) {

            randClick(By.xpath("//*[starts-with(@for,'card_id')]"));
            driver.findElement(By.xpath("//*[@class='orange-button']")).click();
            swipeDown();
            driver.findElement(By.xpath("//*[@class='orange-button']")).click(); // Place your order
            Thread.sleep(2000);

        }

    }

    protected void checkOutNewAccount(String venture, String menuWiz, String categories, String filterWiz,
                                      String prodWiz) throws InterruptedException {
        // Perform Check Out steps
        addRandomProductToCart(venture, menuWiz, categories, filterWiz, prodWiz);

        // Create new account
        findByUISelector("resourceID", "middle_login_link_register", appPackage).click();
        registerAccount();

        Thread.sleep(1000);
        switchToWebView();

        // Create new shipping address
        String name = generateAlphabet(10);
        String address = generateAlphabet(10);
        String phoneNumber = generateNumber(10);
        createAddress(venture, name, address, phoneNumber);



    }

    /**
     * Register new account with random email, password, name ...
     */
    protected void registerAccount() throws InterruptedException {

        // Random email, password, name
        String email = generateEmail();
        String password = generatePassword();
        String name = generateAlphabet(5);

        // Input email, password, name
        List<WebElement> editTexts = driver.findElements(By.xpath("//*[contains(@class,'android.widget.EditText')]"));
        editTexts.get(0).sendKeys(email);
        editTexts.get(1).sendKeys(password);
        editTexts.get(2).sendKeys(password);
        editTexts.get(3).sendKeys(name);
        driver.findElement(By.className("android.widget.CheckBox")).click(); // Show password

        // Submit
        findByUISelector("resourceID", "register_button_submit", appPackage).click();
        Thread.sleep(3000);

    }

    /**
     * Create new address
     */
    protected void createAddress(String venture, String name, String address, String phoneNumber) throws InterruptedException {

        // New billing address
        driver.findElement(By.xpath("//*[@id='ThreeStepBillingAddressForm_first_name']")).sendKeys(name);
        driver.findElement(By.xpath("//*[@id='ThreeStepBillingAddressForm_address1']")).sendKeys(address);
        if (venture.equals("Thailand") || venture.equals("Philippines")
                || venture.equals("Malaysia") || venture.equals("Indonesia")) {

            selectorRandom(By.xpath("//*[@id='ThreeStepBillingAddressForm_location_0']")); // select random Region
            selectorRandom(By.xpath("//*[@id='ThreeStepBillingAddressForm_location_1']")); // select random City
            selectorRandom(By.xpath("//*[@id='ThreeStepBillingAddressForm_location_2']")); // select random Postcode
            driver.findElement(By.xpath("//*[@id='ThreeStepBillingAddressForm_phone']")).sendKeys(phoneNumber);

        }
        if (venture.equals("Singapore")) {
            driver.findElement(By.xpath("//*[@id='ThreeStepBillingAddressForm_postcode']")).sendKeys("759674");
            driver.findElement(By.xpath("//*[@id='ThreeStepBillingAddressForm_phone']")).sendKeys(phoneNumber);
        }
        if (venture.equals("Vietnam")) {
            selectorRandom(By.xpath("//*[@id='ThreeStepBillingAddressForm_location_0']")); // select random Region
            selectorRandom(By.xpath("//*[@id='ThreeStepBillingAddressForm_location_1']")); // select random City
            driver.findElement(By.xpath("//*[@id='ThreeStepBillingAddressForm_phone']")).sendKeys(phoneNumber);
        }

        // Submit
        driver.findElement(By.xpath("//*[@class='orange-button']")).click();

    }

    protected void checkoutNewBillingAddress(String venture, String menuWiz, String categories, String filterWiz,
                                             String prodWiz, String name, String address, String phoneNumber) throws InterruptedException {
        // Perform Check Out steps
        selectVenture(venture, menuWiz);
        checkOut(categories, filterWiz, prodWiz);
        Thread.sleep(1000);

        // Check the Cash On Delivery is available or not for this CheckOutTest
        if (!isElementPresent(By.xpath("//*[@class='payment-method-option radio'and@value='CashOnDelivery'and@disabled='disabled']"))
                && isElementPresent(By.xpath("//label[@for='cashondelivery']"))) {

            driver.findElement(By.xpath("//label[@for='cashondelivery']")).click();
            driver.findElement(By.xpath("//button[@class='orange-button']")).click();
            Thread.sleep(3000);
            // Edit billing address

            driver.findElement(By.xpath("//*[@id='change-shipping'][contains(@href, 'billing')]")).click();
            createAddress(venture, name, address, phoneNumber);
            driver.findElement(By.xpath("//*[@class='orange-button']")).click();
            Thread.sleep(2000);
            driver.findElement(By.xpath("//label[@for='cashondelivery']")).click();
            driver.findElement(By.xpath("//button[@class='orange-button']")).click();
            driver.findElement(By.xpath("//button[@class='orange-button']")).click();

        }

    }

    /**
     *
     * Create new account -> check out via Bank transfer - ID
     */
    protected void checkoutBankTransferNewUser(String venture, String menuWiz, String categories, String filterWiz,
                                               String prodWiz,
                                               int bankIndex, String senderName) throws InterruptedException {

        checkOutNewAccount(venture, menuWiz, categories, filterWiz, prodWiz);
        bankTransfer(bankIndex, senderName);

    }

}