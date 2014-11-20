package scenario;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import screenObjects.android_app.*;
import util.AppiumSetupTest;
import static util.Constant.*;

import java.util.ArrayList;
import java.util.List;

import static util.Helper.*;

/**
 * Created by lazhcm10136 on 9/30/14.
 */
public class CheckOutScenario extends AppiumSetupTest {

    protected void checkOut(String categories, String filterWiz, String prodWiz) throws InterruptedException {

        TopBar_Screen.click_HomeBtn();
        Thread.sleep(1000);
        randomSelectProduct(categories, appPackage, filterWiz, prodWiz);
        ProductDetail_Screen.click_AddToCartBtn(); //Add to Cart

        //Check for Variant Selection
        Boolean cartConfirm = isElementPresent(ProductDetail_Screen.goToCart);
        if (cartConfirm) {
            ProductDetail_Screen.click_GoToCartBtn(); // Go to my click_MyCartBtn
        } else {
            chooseSize();
        }
        Cart_Screen.click_ProceedToCheckoutBtn();

        // Login as default account
        loginAs(USERNAME, PASSWORD);
        ShippingInformation_Screen.click_ContinueBtn();
        Thread.sleep(3000);
    }

    protected void checkOutUseTheCoD(String venture, String menuWiz, String categories, String filterWiz, String prodWiz) throws InterruptedException {

        // Perform Check Out steps
        selectVenture(venture, menuWiz);
        checkOut(categories, filterWiz, prodWiz);
        Thread.sleep(1000);

        // Check out via COD method
        boolean hasCashOnDelivery = cashOnDelivery(); /*1 => cash on delivery is available  ;0 => not available */

        // Verify success page
        if (hasCashOnDelivery) {
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

    /**
     * Perform checkout steps via COD
     */
    protected boolean cashOnDelivery () throws InterruptedException {

        boolean hasCashOnDelivery = false;
        if (!isElementPresent(PayMethod_Screen.radioCODDisabled)
                && isElementPresent(PayMethod_Screen.labelCOD)) { // Cash on delivery is available

            PayMethod_Screen.click_CashOnDeliveryRadio();
            PayMethod_Screen.click_ContinueBtn();
            Thread.sleep(3000);
            OrderSummary_Screen.click_PlaceOrderBtn();
            Thread.sleep(2000);
            switchToNativeApp();
            hasCashOnDelivery = true;

        }
        return hasCashOnDelivery;
    }

    protected void checkOutUseCreditCardInvalid(String venture, String menuWiz, String categories, String filterWiz,
                                                String prodWiz, String creditNumber, String customerName, String securityCode) throws InterruptedException {
        // Perform Check Out steps
        selectVenture(venture, menuWiz);
        checkOut(categories, filterWiz, prodWiz);

        // Check the Credit Cards is available or not for this CheckOutTest
        boolean hasCreditCard = creditCard(creditNumber, customerName, securityCode);

        // Verify message
        if (hasCreditCard) {

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

    /**
     * Perform check out steps via Credit card
     */
    protected boolean creditCard (String creditNumber, String customerName, String securityCode) throws InterruptedException {

        boolean hasCreditCard = false;

        // Check the Credit Cards is available or not for this CheckOutTest
        if (isElementPresent(PayMethod_Screen.labelCreditCard)) {

            PayMethod_Screen.click_CreditCardRadio();
            Thread.sleep(1000);

            // waiting & input the CC info
            PayMethod_Screen.input_CreditNumber(creditNumber);
            PayMethod_Screen.input_CreditName(customerName);
            PayMethod_Screen.input_CreditSecurityCode(securityCode);
            selectorRandom(PayMethod_Screen.creditCardMonthCbx());
            selectorRandom(PayMethod_Screen.creditCardYearCbx());
            PayMethod_Screen.click_ContinueBtn();
            Thread.sleep(1000);
            OrderSummary_Screen.click_PlaceOrderBtn();
            OrderSummary_Screen.click_PlaceOrderBtn();
            Thread.sleep(2000);
            switchToNativeApp();
            hasCreditCard = true;

        }
        return hasCreditCard;
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
        if (isElementPresent(PayMethod_Screen.labelBankTransfer)) {

            // Choose pay method -> bank transfer
            PayMethod_Screen.click_BankTransferRadio();
            Thread.sleep(1000);

            // Select bank name
            selector(PayMethod_Screen.bankNames , bankIndex);

            // Fill information and Submit
            PayMethod_Screen.input_SenderName(senderName);
            Thread.sleep(2000);
            PayMethod_Screen.click_ContinueBtn(); //Place Order
            OrderSummary_Screen.click_PlaceOrderBtn(); //Place Order
            OrderSummary_Screen.click_PlaceOrderBtn(); //Place Order

        }
    }

    // Only for staging
    protected void checkOutAndUsePaypal(String venture, String menuWiz, String categories, String filterWiz, String prodWiz) throws InterruptedException {

        // Perform Check Out steps
        selectVenture(venture, menuWiz);
        checkOut(categories, filterWiz, prodWiz);

        // Check the Paypal is available or not for this CheckOutTest
        if (isElementPresent(PayMethod_Screen.labelPaypal)) {

            PayMethod_Screen.click_PaypalRadio();
            Thread.sleep(1000);

            // Choose pay method -> Paypal
            PayMethod_Screen.click_ContinueBtn();
            OrderSummary_Screen.click_PlaceOrderBtn();
            Thread.sleep(10000);

        }
    }

    /**
     * Select random product -> add to Cart
     *
     */
    protected void addRandomProductToCart(String venture, String menuWiz, String categories,
                                          String filterWiz, String prodWiz) throws InterruptedException {
        selectVenture(venture, menuWiz);
        TopBar_Screen.click_HomeBtn();

        Thread.sleep(1000);
        randomSelectProduct(categories, appPackage, filterWiz, prodWiz);
        ProductDetail_Screen.click_AddToCartBtn(); //Add to Cart button

        //Check for Variant Selection
        Boolean cartConfirm = isElementPresent(ProductDetail_Screen.goToCart);
        if (cartConfirm) {
            ProductDetail_Screen.click_GoToCartBtn();
        } else {

            ProductDetail_Screen.click_ChooseSizeBtn();
            randClick(ProductDetail_Screen.sizes);
            ProductDetail_Screen.click_AddToCartBtn(); //Add to Cart button
            ProductDetail_Screen.click_GoToCartBtn();
        }

        Cart_Screen.click_ProceedToCheckoutBtn();

    }

    /**
     * Login as
     */
    protected void loginAs( String userName, String password) throws InterruptedException {
        //Login to CheckOut
        Login_Screen.input_Email(userName);
        Login_Screen.input_Password(password);
        Login_Screen.click_ShowPassword();
        Login_Screen.click_Login();

        Thread.sleep(5000);
        Login_Screen.wait_ForCheckout();
        Login_Screen.rocket_app_checkoutweb();
        Thread.sleep(2000);
        switchToWebView();
        Thread.sleep(3000);

    }

    protected void checkOutCreateShippingAddress(String venture, String menuWiz, String categories, String filterWiz,
                                                 String prodWiz, String name, String address,
                                                 String phoneNumber) throws InterruptedException {
        // Perform Check Out steps
        addRandomProductToCart(venture, menuWiz, categories, filterWiz, prodWiz);
        loginAs(USERNAME, PASSWORD);

        // Create new address
        swipeDown();
        ShippingInformation_Screen.click_NewAddress(); // Click New address
        Thread.sleep(2000);
        createShippingAddress(venture, name, address, phoneNumber);

        // Verify new address appears on delivery information page or Not
        String pageSource = driver.getPageSource();
        Assert.assertTrue(pageSource.contains(name));
        Assert.assertTrue(pageSource.contains(address));
        Assert.assertTrue(pageSource.contains(phoneNumber));

    }

    /**
     * Check out and check "Use different billing address"
     *
     */
    protected void checkOutDifferenBillingtAddress(String venture, String menuWiz, String categories, String filterWiz,
                                                   String prodWiz, String name, String address, String phoneNumber) throws InterruptedException {
        // Perform Check Out steps
        addRandomProductToCart(venture, menuWiz, categories, filterWiz, prodWiz);
        loginAs(USERNAME, PASSWORD);

        // Choose Use different billing address
        ShippingInformation_Screen.click_UseDifferent();
        createBillingAddress(venture, name, address, phoneNumber);

    }

    protected void checkOutEditBillingAddress(String venture, String menuWiz, String categories, String filterWiz,
                                              String prodWiz, String name, String address, String phoneNumber) throws InterruptedException {
        // Perform Check Out steps
        selectVenture(venture, menuWiz);
        checkOut(categories, filterWiz, prodWiz);

        // Check the Cash On Delivery is available or not for this CheckOutTest
        if (!isElementPresent(PayMethod_Screen.radioCODDisabled)
                && isElementPresent(PayMethod_Screen.labelCOD)) {

            PayMethod_Screen.click_CashOnDeliveryRadio();
            PayMethod_Screen.click_ContinueBtn();
            Thread.sleep(5000);
            OrderSummary_Screen.click_EditBillingAddress();

            // Edit billing address
            createBillingAddress(venture, name, address, phoneNumber);

            // Continue checking out
            PayMethod_Screen.click_CashOnDeliveryRadio();
            PayMethod_Screen.click_ContinueBtn();
            Thread.sleep(4000);

            // Verify billing address has changed
            String pageSource = driver.getPageSource();
            Assert.assertTrue(pageSource.contains(name));
            Assert.assertTrue(pageSource.contains(address));
            Assert.assertTrue(pageSource.contains(phoneNumber));

            // Submit order
            OrderSummary_Screen.click_PlaceOrderBtn();

        }

    }

    protected void checkOutEditShippingAddress(String venture, String menuWiz, String categories, String filterWiz,
                                               String prodWiz, String name, String address, String phoneNumber) throws InterruptedException {
        // Perform Check Out steps
        selectVenture(venture, menuWiz);
        checkOut(categories, filterWiz, prodWiz);

        // Check the Cash On Delivery is available or not for this CheckOutTest
        if (!isElementPresent(PayMethod_Screen.radioCODDisabled)
                && isElementPresent(PayMethod_Screen.labelCOD)) {

            PayMethod_Screen.click_CashOnDeliveryRadio();
            PayMethod_Screen.click_ContinueBtn();
            Thread.sleep(3000);
            OrderSummary_Screen.click_EditShippingAddress();

            // Random choose address to edit
            randClick(ShippingInformation_Screen.editAddress);

            // Edit shipping address
            editShippingAddress(venture, name, address, phoneNumber);
            Thread.sleep(4000);

            // Verify billing address has changed
            String pageSource = driver.getPageSource();
            Assert.assertTrue(pageSource.contains(name));
            Assert.assertTrue(pageSource.contains(address));
            Assert.assertTrue(pageSource.contains(phoneNumber));

            // Submit order
            OrderSummary_Screen.click_PlaceOrderBtn(); // Place your order
            cashOnDelivery(); // Checkout again

        }

    }

    /**
     * Remove all product from myCart
     */
    protected void checkOutRemoveFromCart(String venture, String menuWiz, String categories, String filterWiz,
                                          String prodWiz) throws InterruptedException {


        // Perform Check Out steps
        addRandomProductToCart(venture, menuWiz, categories, filterWiz, prodWiz);
        loginAs(USERNAME, PASSWORD);
        switchToNativeApp();

        // Go to myCart and remove all product
        TopBar_Screen.click_MyCartBtn();
        List<WebElement> arrDelete = new ArrayList<WebElement>();
        do {
            arrDelete = Cart_Screen.deleteButtons();
            if (arrDelete.size() > 0) {

                arrDelete.get(0).click();
                Cart_Screen.click_RemoveItem(); // Remove confirm

            }
            arrDelete = Cart_Screen.deleteButtons();
        } while (arrDelete.size() > 0);

    }

    protected void checkOutEditPaymentMethod(String venture, String menuWiz, String categories, String filterWiz,
                                             String prodWiz) throws InterruptedException {


        // Perform Check Out steps
        selectVenture(venture, menuWiz);
        checkOut(categories, filterWiz, prodWiz);

        // Check out by COD then edit payment method
        if (!isElementPresent(PayMethod_Screen.radioCODDisabled)
                && isElementPresent(PayMethod_Screen.labelCOD)) {

            PayMethod_Screen.click_CashOnDeliveryRadio();
            PayMethod_Screen.click_ContinueBtn();
            Thread.sleep(3000);

            // Change payment method
            OrderSummary_Screen.click_ChangePayment();
            randClick(PayMethod_Screen.paymentMethods);
            PayMethod_Screen.click_ContinueBtn();
            Thread.sleep(2000);

      }

    }

    protected void checkOutCoupon(String venture, String menuWiz, String categories, String filterWiz,
                                  String prodWiz, String coupon) throws InterruptedException {

        // Perform Check Out steps
        selectVenture(venture, menuWiz);
        checkOut(categories, filterWiz, prodWiz);

        // Check out by COD
        if (!isElementPresent(PayMethod_Screen.radioCODDisabled)
                && isElementPresent(PayMethod_Screen.labelCOD)) {

            PayMethod_Screen.click_CashOnDeliveryRadio();
            PayMethod_Screen.click_ContinueBtn();
            Thread.sleep(3000);

            // Input coupon code
            OrderSummary_Screen.click_addCouponBtn();
            OrderSummary_Screen.input_Coupon(coupon);
            OrderSummary_Screen.click_SubmitCoupon();
            OrderSummary_Screen.click_PlaceOrderBtn(); // Place your oder
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
        if (!isElementPresent(PayMethod_Screen.radioCODDisabled)
                && isElementPresent(PayMethod_Screen.labelCOD)) {

            PayMethod_Screen.click_CashOnDeliveryRadio();
            PayMethod_Screen.click_ContinueBtn();
            Thread.sleep(3000);
            OrderSummary_Screen.click_PlaceOrderBtn();
            Thread.sleep(2000);
            switchToNativeApp();

            // Share order
            Thankyou_Screen.click_ShareBtn();
            randClick(Thankyou_Screen.sharerApps); // Random & choose application to share order

        }
    }

    protected void checkOutSavedCreditCard(String venture, String menuWiz, String categories, String filterWiz,
                                           String prodWiz) throws InterruptedException {
        // Perform Check Out steps
        selectVenture(venture, menuWiz);
        checkOut(categories, filterWiz, prodWiz);

        // Check the Saved Credit Cards is available or not
        if (isElementPresent(PayMethod_Screen.creditCards)) {

            randClick(PayMethod_Screen.creditCards);
            PayMethod_Screen.click_ContinueBtn();
            swipeDown();
            OrderSummary_Screen.click_PlaceOrderBtn(); // Place your order
            Thread.sleep(2000);

        }

    }

    protected void checkOutNewAccount(String venture, String menuWiz, String categories, String filterWiz,
                                      String prodWiz) throws InterruptedException {
        // Perform Check Out steps
        addRandomProductToCart(venture, menuWiz, categories, filterWiz, prodWiz);

        // Create new account
        Login_Screen.click_RegisterBtn();
        registerAccount();

        Thread.sleep(1000);
        switchToWebView();

        // Create new shipping address
        String name = generateAlphabet(10);
        String address = generateAlphabet(10);
        String phoneNumber = generateNumber(10);
        editShippingAddress(venture, name, address, phoneNumber);
        cashOnDelivery();
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
        Register_Screen.input_Email(email);
        Register_Screen.input_Password(password);
        Register_Screen.input_RePassword(password);
        Register_Screen.input_Name(name);
        Register_Screen.click_ShowPassword(); // Show password

        // Submit
        Register_Screen.click_SubmitBtn();
        Thread.sleep(3000);

    }

    /**
     * Create new billing address
     */
    protected void createBillingAddress(String venture, String name, String address, String phoneNumber) throws InterruptedException {

        // New billing address
        BillingAddress_Screen.input_Name(name);
        BillingAddress_Screen.input_Address(address);
        if (venture.equals("Thailand") || venture.equals("Philippines")
                || venture.equals("Malaysia") || venture.equals("Indonesia")) {

            selectorRandom(BillingAddress_Screen.region()); // select random Region
            selectorRandom(BillingAddress_Screen.city()); // select random City
            selectorRandom(BillingAddress_Screen.ward()); // select random Postcode
            BillingAddress_Screen.input_PhoneNumber(phoneNumber);

        }
        if (venture.equals("Singapore")) {
            BillingAddress_Screen.input_PostCode("759674");
            BillingAddress_Screen.input_PhoneNumber(phoneNumber);

        }
        if (venture.equals("Vietnam")) {
            selectorRandom(BillingAddress_Screen.region()); // select random Region
            selectorRandom(BillingAddress_Screen.city()); // select random City
            BillingAddress_Screen.input_PhoneNumber(phoneNumber);
        }

        // Submit
        BillingAddress_Screen.click_ContinueBtn();

    }

    /**
     * create new shipping address - use Web view
     */
    protected void createShippingAddress(String venture, String name, String address, String phoneNumber) throws InterruptedException {

        // New billing address
        Thread.sleep(2000);
        ShippingAddress_Screen.input_Name(name);
        ShippingAddress_Screen.input_Address(address);
        if (venture.equals("Thailand") || venture.equals("Philippines")
                || venture.equals("Malaysia") || venture.equals("Indonesia")) {

            selectorRandom(ShippingAddress_Screen.region());
            selectorRandom(ShippingAddress_Screen.city());
            Thread.sleep(1000);
            selectorRandom(ShippingAddress_Screen.ward());
            ShippingAddress_Screen.input_PhoneNumber(phoneNumber);

        }
        if (venture.equals("Singapore")) {
            ShippingAddress_Screen.input_PostCode("759674");
            ShippingAddress_Screen.input_PhoneNumber(phoneNumber);
        }
        if (venture.equals("Vietnam")) {
            selectorRandom(ShippingAddress_Screen.region());
            selectorRandom(ShippingAddress_Screen.city());
            ShippingAddress_Screen.input_PhoneNumber(phoneNumber);
        }

        // Submit
        ShippingAddress_Screen.click_ContinueBtn();

    }

    /**
     * edit shipping address - use Native app
     */
    protected void editShippingAddress(String venture, String name, String address, String phoneNumber) throws InterruptedException {

        // Edit billing address
        switchToNativeApp();
        List<WebElement> editTexts = driver.findElements(By.xpath("//*[@class='android.widget.EditText']"));
        List<WebElement> buttons = new ArrayList<WebElement>();
        editTexts.get(0).clear();
        editTexts.get(0).sendKeys(name);
        editTexts.get(1).clear();
        editTexts.get(1).sendKeys(address);


        if (venture.equals("Thailand") || venture.equals("Philippines")
                || venture.equals("Malaysia") || venture.equals("Indonesia")) {

            buttons = driver.findElements(By.xpath("//*[@class='android.widget.Button']"));
            selectorRandom(buttons.get(0)); // select random Region
            selectorRandom(buttons.get(1)); // select random City
            selectorRandom(buttons.get(2)); // select random Postcode
            editTexts.get(2).clear();
            editTexts.get(2).sendKeys(phoneNumber);

        }
        if (venture.equals("Singapore")) {

            editTexts.get(2).clear();
            editTexts.get(2).sendKeys("759674");
            editTexts.get(3).clear();
            editTexts.get(3).sendKeys(phoneNumber);

        }
        if (venture.equals("Vietnam")) {

            buttons = driver.findElements(By.xpath("//*[@class='android.widget.Button']"));
            selectorRandom(buttons.get(0)); // select random Region
            selectorRandom(buttons.get(1)); // select random City
            editTexts.get(2).clear();
            editTexts.get(2).sendKeys(phoneNumber);
        }

        // Submit
        buttons.get(buttons.size()-1).click();
        switchToWebView();

    }

    protected void checkoutNewBillingAddress(String venture, String menuWiz, String categories, String filterWiz,
                                             String prodWiz, String name, String address, String phoneNumber) throws InterruptedException {
        // Perform Check Out steps
        selectVenture(venture, menuWiz);
        checkOut(categories, filterWiz, prodWiz);
        Thread.sleep(1000);

        // Check the Cash On Delivery is available or not for this CheckOutTest
        if (!isElementPresent(PayMethod_Screen.radioCODDisabled)
                && isElementPresent(PayMethod_Screen.labelCOD)) { // Cash on delivery is available

            PayMethod_Screen.click_CashOnDeliveryRadio();
            PayMethod_Screen.click_ContinueBtn();
            Thread.sleep(3000);
            // Edit billing address

            OrderSummary_Screen.click_EditBillingAddress();
            createBillingAddress(venture, name, address, phoneNumber);
            BillingAddress_Screen.click_ContinueBtn();
            Thread.sleep(2000);
            PayMethod_Screen.click_CashOnDeliveryRadio();
            PayMethod_Screen.click_ContinueBtn();
            OrderSummary_Screen.click_PlaceOrderBtn();

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

    /**
     * Use bank transfer payment method check out with a different billing address in ID venture
     */
    protected void checkoutBankTransferDifferentBillingAddress(String venture, String menuWiz,
                                                               String categories, String filterWiz, String prodWiz,
                                                               int bankIndex, String senderName, String name,
                                                               String address, String phoneNumber) throws InterruptedException {
        // Perform Check Out steps
        selectVenture(venture, menuWiz);
        checkOut(categories, filterWiz, prodWiz);
        Thread.sleep(1000);

        // Check the Bank transfer is available or not for this CheckOutTest
        if (isElementPresent(PayMethod_Screen.labelBankTransfer)) {

            // Choose pay method -> bank transfer
            PayMethod_Screen.click_BankTransferRadio();
            Thread.sleep(1000);

            // Select bank name
            selector(PayMethod_Screen.bankNames, bankIndex);

            // Fill information and Submit
            PayMethod_Screen.input_SenderName(senderName);
            Thread.sleep(2000);
            PayMethod_Screen.click_ContinueBtn();
            Thread.sleep(3000);
            OrderSummary_Screen.click_EditBillingAddress();

            // Edit billing address
            createBillingAddress(venture, name, address, phoneNumber);
            bankTransfer(bankIndex, senderName);
        }
    }

    /**
     * Random and select size clothes, shoes, ...
     */
    protected void chooseSize () throws InterruptedException {

        ProductDetail_Screen.click_ChooseSizeBtn();
        randClick(ProductDetail_Screen.sizes);
        ProductDetail_Screen.click_AddToCartBtn();
        ProductDetail_Screen.click_GoToCartBtn();
        Thread.sleep(2000);

    }

}