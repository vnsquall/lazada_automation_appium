package testsuite;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObject.*;
import util.AppiumSetupTest;

import java.util.ArrayList;
import java.util.List;
import static util.Constant.PASSWORD;
import static util.Constant.USERNAME;
import static util.Helper.*;
import static util.Helper.hasSizeErrorMgs;
import static util.VentureText.setText;


public class CheckOut_Test extends AppiumSetupTest {


    @Test(dataProvider = "getVenturesDataToTestCashOnDelivery", dataProviderClass = StaticDataProvider.class)
    public void test_CashOnDelivery(String venture) throws Exception {

        checkOutUseTheCoD(venture,
                setText(venture).get("menuWiz"),
                setText(venture).get("categories"),
                setText(venture).get("filterWiz"),
                setText(venture).get("prodWiz"));
    }

    @Test(dataProvider = "getVenturesDataToTestCashOnDeliveryGuest", dataProviderClass = StaticDataProvider.class)
    public void test_CashOnDeliveryGuest(String venture,
                                         String name,
                                         String address,
                                         String phoneNumber) throws Exception {

        checkoutCODAsGuest(venture,
                setText(venture).get("menuWiz"),
                setText(venture).get("categories"),
                setText(venture).get("filterWiz"),
                setText(venture).get("prodWiz"),
                name,
                address,
                phoneNumber);
    }

    @Test(dataProvider = "getVenturesDataToTestBankTransferGuest", dataProviderClass = StaticDataProvider.class)
    public void test_BankTransferGuest(String venture,
                                       String name,
                                       String address,
                                       String phoneNumber,
                                       int bankIndex,
                                       String senderName) throws Exception {

        checkoutBankTransferAsGuest(venture,
                setText(venture).get("menuWiz"),
                setText(venture).get("categories"),
                setText(venture).get("filterWiz"),
                setText(venture).get("prodWiz"),
                name,
                address,
                phoneNumber,
                bankIndex,
                senderName);
    }

    @Test(dataProvider = "getVenturesDataToTestBankTransfer", dataProviderClass = StaticDataProvider.class)
    public void test_BankTransfer(String venture, int bankIndex, String senderName) throws Exception {

        checkOutUseBankTransfer(venture,
                setText(venture).get("menuWiz"),
                setText(venture).get("categories"),
                setText(venture).get("filterWiz"),
                setText(venture).get("prodWiz"),
                bankIndex,
                senderName);
    }

    @Test(dataProvider = "getVenturesDataToTestCreditCardInvalid", dataProviderClass = StaticDataProvider.class)
    public void test_CreditCardInvalid(String venture,
                                       String creditNumber,
                                       String customerName,
                                       String securityCode) throws Exception {

        checkOutUseCreditCardInvalid(venture,
                setText(venture).get("menuWiz"),
                setText(venture).get("categories"),
                setText(venture).get("filterWiz"),
                setText(venture).get("prodWiz"),
                creditNumber,
                customerName,
                securityCode);
    }

    @Test(dataProvider = "getVenturesDataToTestCreditCardInvalidGuest", dataProviderClass = StaticDataProvider.class)
    public void test_CreditCardInvalidGuest(String venture,
                                            String creditNumber,
                                            String customerName,
                                            String securityCode,
                                            String name,
                                            String address,
                                            String phoneNumber) throws Exception {

        checkOutUseCreditCardInvalidGuest(venture,
                setText(venture).get("menuWiz"),
                setText(venture).get("categories"),
                setText(venture).get("filterWiz"),
                setText(venture).get("prodWiz"),
                creditNumber,
                customerName,
                securityCode,
                name,
                address,
                phoneNumber);
    }



    @Test(dataProvider = "getVenturesDataToTestPaypal", dataProviderClass = StaticDataProvider.class)
    public void test_Paypal(String venture) throws Exception {

        checkOutAndUsePaypal(venture,
                setText(venture).get("menuWiz"),
                setText(venture).get("categories"),
                setText(venture).get("filterWiz"),
                setText(venture).get("prodWiz"));
    }

    @Test(dataProvider = "getVenturesDataToTestCreateAddress", dataProviderClass = StaticDataProvider.class)
    public void test_CreateShippingAddress(String venture,
                                           String name,
                                           String address,
                                           String phoneNumber) throws Exception {
        checkOutCreateShippingAddress(venture,
                setText(venture).get("menuWiz"),
                setText(venture).get("categories"),
                setText(venture).get("filterWiz"),
                setText(venture).get("prodWiz"),
                name,
                address,
                phoneNumber);
    }

    @Test(dataProvider = "getVenturesDataToTestEditShippingAddress", dataProviderClass = StaticDataProvider.class)
    public void test_EditShippingAddress(String venture,
                                         String name,
                                         String address,
                                         String phoneNumber) throws Exception {

        checkOutEditShippingAddress(venture,
                setText(venture).get("menuWiz"),
                setText(venture).get("categories"),
                setText(venture).get("filterWiz"),
                setText(venture).get("prodWiz"),
                name,
                address,
                phoneNumber);
    }

    @Test(dataProvider = "getVenturesDataToTestDifferentAddress", dataProviderClass = StaticDataProvider.class)
    public void test_DifferentBillingAddress(String venture,
                                             String name,
                                             String address,
                                             String phoneNumber) throws Exception {

        checkOutDifferenBillingtAddress(venture,
                setText(venture).get("menuWiz"),
                setText(venture).get("categories"),
                setText(venture).get("filterWiz"),
                setText(venture).get("prodWiz"),
                name,
                address,
                phoneNumber);
    }

    @Test(dataProvider = "getVenturesDataToTestEditBillingAddress", dataProviderClass = StaticDataProvider.class)
    public void test_EditBillingAddress(String venture,
                                        String name,
                                        String address,
                                        String phoneNumber) throws Exception {

        checkOutEditBillingAddress(venture,
                setText(venture).get("menuWiz"),
                setText(venture).get("categories"),
                setText(venture).get("filterWiz"),
                setText(venture).get("prodWiz"),
                name,
                address,
                phoneNumber);
    }



    @Test(dataProvider = "getVenturesDataToTestRemoveFromCart", dataProviderClass = StaticDataProvider.class)
    public void test_RemoveFromCart(String venture) throws Exception {

        checkOutRemoveFromCart(venture,
                setText(venture).get("menuWiz"),
                setText(venture).get("categories"),
                setText(venture).get("filterWiz"),
                setText(venture).get("prodWiz")
        );
    }

    @Test(dataProvider = "getVenturesDataToTestEditPaymentMethod", dataProviderClass = StaticDataProvider.class)
    public void test_EditPaymentMethod(String venture) throws Exception {

        checkOutEditPaymentMethod(venture,
                setText(venture).get("menuWiz"),
                setText(venture).get("categories"),
                setText(venture).get("filterWiz"),
                setText(venture).get("prodWiz")
        );
    }

    // Staging only
    @Test(dataProvider = "getVenturesDataToTestCoupon", dataProviderClass = StaticDataProvider.class)
    public void test_Coupon(String venture, String coupon) throws Exception {

        checkOutCoupon(venture,
                setText(venture).get("menuWiz"),
                setText(venture).get("categories"),
                setText(venture).get("filterWiz"),
                setText(venture).get("prodWiz"),
                coupon);
    }

    @Test(dataProvider = "getVenturesDataToTestShareOrder", dataProviderClass = StaticDataProvider.class)
    public void test_ShareOrder(String venture) throws Exception {

        checkOutShareOrder(venture,
                setText(venture).get("menuWiz"),
                setText(venture).get("categories"),
                setText(venture).get("filterWiz"),
                setText(venture).get("prodWiz")
        );
    }

    // Staging only
    @Test(dataProvider = "getVenturesDataToTestSavedCreditCard", dataProviderClass = StaticDataProvider.class)
    public void test_SavedCreditCard(String venture) throws Exception {

        checkOutSavedCreditCard(venture,
                setText(venture).get("menuWiz"),
                setText(venture).get("categories"),
                setText(venture).get("filterWiz"),
                setText(venture).get("prodWiz")
        );
    }

    @Test(dataProvider = "getVenturesDataToTestNewAccount", dataProviderClass = StaticDataProvider.class)
    public void test_NewAccount(String venture) throws Exception {

        checkOutNewAccount(venture,
                setText(venture).get("menuWiz"),
                setText(venture).get("categories"),
                setText(venture).get("filterWiz"),
                setText(venture).get("prodWiz")
        );
    }

    @Test(dataProvider = "getVenturesDataToTestNewBillingAddress", dataProviderClass = StaticDataProvider.class)
    public void test_NewBillingAddress(String venture,
                                       String name,
                                       String address,
                                       String phoneNumber) throws Exception {

        checkoutNewBillingAddress(venture,
                setText(venture).get("menuWiz"),
                setText(venture).get("categories"),
                setText(venture).get("filterWiz"),
                setText(venture).get("prodWiz"),
                name,
                address,
                phoneNumber);
    }

    @Test(dataProvider = "getVenturesDataToTestBankTransferDifferentBillingAddress", dataProviderClass = StaticDataProvider.class)
    public void test_BankTransferEditBillingAddress(String venture,
                                                    int bankIndex,
                                                    String senderName,
                                                    String name,
                                                    String address,
                                                    String phoneNumber) throws Exception {

        checkoutBankTransferDifferentBillingAddress(venture,
                setText(venture).get("menuWiz"),
                setText(venture).get("categories"),
                setText(venture).get("filterWiz"),
                setText(venture).get("prodWiz"),
                bankIndex,
                senderName,
                name,
                address,
                phoneNumber);
    }

    protected void checkOut(String categories, String filterWiz, String prodWiz) throws InterruptedException {

        TopBar_Screen.click_MenuBtn();
        Thread.sleep(1000);
        randomSelectProduct(categories, filterWiz, prodWiz);
        addToCart();
        Cart_Screen.click_CheckoutBtn();

        // Login as default account
        loginAs(USERNAME, PASSWORD);
        ShippingInformation_Screen.click_ContinueBtn();
        Thread.sleep(3000);
    }

    protected void checkOutUseTheCoD(String venture, String menuWiz, String categories, String filterWiz, String prodWiz) throws InterruptedException {

        // Perform Check Out steps
        Init_Screen.select_Country(venture, menuWiz);
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
            System.out.println("Show success page");
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
            System.out.println(" >>> COD is available!");

        }
        return hasCashOnDelivery;
    }

    protected void checkOutUseCreditCardInvalid(String venture, String menuWiz, String categories, String filterWiz,
                                                String prodWiz, String creditNumber, String customerName, String securityCode) throws InterruptedException {
        // Perform Check Out steps
        Init_Screen.select_Country(venture, menuWiz);
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

    protected void checkOutUseCreditCardInvalidGuest(String venture, String menuWiz, String categories, String filterWiz,
                                                     String prodWiz, String creditNumber, String customerName, String securityCode,
                                                     String name, String address, String phoneNumber) throws InterruptedException {
        // Perform Check Out steps
        buyNowRandomProduct(venture, menuWiz, categories, filterWiz, prodWiz);
        Thread.sleep(1000);
        loginAsGuest();

        // Create new shipping address
        createShippingAddress(venture, name, address, phoneNumber);

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
        Init_Screen.select_Country(venture, menuWiz);
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
            PayMethod_Screen.click_ContinueBtn();
            Thread.sleep(2000);
            OrderSummary_Screen.click_PlaceOrderBtn(); //Place Order

            // Verify success page
            Assert.assertTrue(isPageContains("Pelanggan yang terhormat"));
            Assert.assertTrue(isPageContains("Nomor pesanan Anda adalah"));
            Assert.assertTrue(isPageContains("telah sukses diterima"));
//            Assert.assertTrue(isPageContains("Silakan melakukan pembayaran sebesar"));

        }
    }

    // Only for staging
    protected void checkOutAndUsePaypal(String venture, String menuWiz, String categories, String filterWiz, String prodWiz) throws InterruptedException {

        // Perform Check Out steps
        Init_Screen.select_Country(venture, menuWiz);
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
        Init_Screen.select_Country(venture, menuWiz);
        TopBar_Screen.click_MenuBtn();

        Thread.sleep(1000);
        randomSelectProduct(categories, filterWiz, prodWiz);
        addToCart();
        Cart_Screen.click_CheckoutBtn();

    }

    /**
     * Select random product -> add to Cart
     *
     */
    protected void buyNowRandomProduct(String venture, String menuWiz, String categories,
                                       String filterWiz, String prodWiz) throws InterruptedException {
        Init_Screen.select_Country(venture, menuWiz);
        TopBar_Screen.click_MenuBtn();

        Thread.sleep(1000);
        randomSelectProduct(categories, filterWiz, prodWiz);
        buyNow();
    }

    /**
     * Login as
     */
    protected void loginAs( String email, String password) throws InterruptedException {

        // Choose checkout method: Guest or User
        Login_Screen.input_Email(email);
        Login_Screen.click_UserRd();
        Login_Screen.input_Password(password);
        Login_Screen.click_ContinueBtn();

        Thread.sleep(5000);
//        Login_Screen.wait_ForWebView();
        Login_Screen.rocket_app_checkoutweb();
        Thread.sleep(2000);
        switchToWebView();
        Thread.sleep(3000);

    }

    /**
     * Login as guest
     */
    protected void loginAsGuest() throws InterruptedException {

        Login_Screen.input_Email(generateEmail());
        Login_Screen.click_ContinueBtn();
        Thread.sleep(8000);
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
        Init_Screen.select_Country(venture, menuWiz);
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
        Init_Screen.select_Country(venture, menuWiz);
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
                Cart_Screen.click_DeleteConfirm(); // Remove confirm

            }
            arrDelete = Cart_Screen.deleteButtons();
        } while (arrDelete.size() > 0);

    }

    protected void checkOutEditPaymentMethod(String venture, String menuWiz, String categories, String filterWiz,
                                             String prodWiz) throws InterruptedException {


        // Perform Check Out steps
        Init_Screen.select_Country(venture, menuWiz);
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
        Init_Screen.select_Country(venture, menuWiz);
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
        Init_Screen.select_Country(venture, menuWiz);
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
        Init_Screen.select_Country(venture, menuWiz);
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
            Thread.sleep(2000); // wait for loading data from dropdown list
            selectorRandom(BillingAddress_Screen.city()); // select random City
            Thread.sleep(2000); // wait for loading data from dropdown list
            selectorRandom(BillingAddress_Screen.ward()); // select random Postcode
            BillingAddress_Screen.input_PhoneNumber(phoneNumber);

        }
        if (venture.equals("Singapore")) {
            BillingAddress_Screen.input_PostCode("759674");
            BillingAddress_Screen.input_PhoneNumber(phoneNumber);

        }
        if (venture.equals("Vietnam")) {
            selectorRandom(BillingAddress_Screen.region()); // select random Region
            Thread.sleep(2000); // wait for loading data from dropdown list
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
        ShippingAddress_Screen.waitForWebView();
        switchToWebView();
        ShippingAddress_Screen.input_Name(name);


        if (venture.equals("Thailand") || venture.equals("Philippines")
                || venture.equals("Malaysia") || venture.equals("Indonesia")) {

            ShippingAddress_Screen.input_Address(address);

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

            ShippingAddress_Screen.input_Address(address);
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
        List<WebElement> editTexts = driver.findElementsByClassName("android.widget.EditText");
        List<WebElement> buttons = new ArrayList<WebElement>();
        editTexts.get(0).clear();
        editTexts.get(0).sendKeys(name);
        editTexts.get(1).clear();
        editTexts.get(1).sendKeys(address);


        if (venture.equals("Thailand") || venture.equals("Philippines")
                || venture.equals("Malaysia") || venture.equals("Indonesia")) {

            buttons = driver.findElementsByClassName("android.widget.Button");
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

            buttons = driver.findElementsByClassName("android.widget.Button");
            selectorRandom(buttons.get(0)); // select random Region
            selectorRandom(buttons.get(1)); // select random City
            editTexts.get(2).clear();
            editTexts.get(2).sendKeys(phoneNumber);
        }

        // Submit
        buttons.get(buttons.size() - 1).click();
        switchToWebView();

    }

    protected void checkoutNewBillingAddress(String venture, String menuWiz, String categories, String filterWiz,
                                             String prodWiz, String name, String address, String phoneNumber) throws InterruptedException {
        // Perform Check Out steps
        Init_Screen.select_Country(venture, menuWiz);
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
     * Checkout as a guest
     */
    protected void checkoutCODAsGuest (String venture, String menuWiz, String categories, String filterWiz,
                                       String prodWiz, String name, String address, String phoneNumber ) throws InterruptedException {
        // Perform Check Out steps
        buyNowRandomProduct(venture, menuWiz, categories, filterWiz, prodWiz);
        Thread.sleep(1000);

        // Login as guest
        loginAsGuest();

        // Create new shipping address
        createShippingAddress(venture, name, address, phoneNumber);
        ShippingAddress_Screen.click_ContinueBtn();

        // Checkout via COD
        boolean hasCashOnDelivery = cashOnDelivery();

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

    protected void checkoutBankTransferAsGuest (String venture, String menuWiz, String categories, String filterWiz,
                                                String prodWiz, String name, String address, String phoneNumber, int bankIndex, String senderName ) throws InterruptedException {
        // Perform Check Out steps
        addRandomProductToCart(venture, menuWiz, categories, filterWiz, prodWiz);
        Thread.sleep(1000);
        Login_Screen.input_Email(generateEmail());
        Login_Screen.click_ContinueBtn();
        Thread.sleep(3000);
        createShippingAddress(venture, name, address, phoneNumber);
        ShippingAddress_Screen.click_ContinueBtn();
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
        Init_Screen.select_Country(venture, menuWiz);
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
        Thread.sleep(2000);

    }

    /**
     * Add product to cart
     */
    protected void addToCart() throws InterruptedException {

        ProductDetail_Screen.click_AddToCartBtn();
        if (hasSizeErrorMgs()) { // We need select size first

            chooseSize();
            ProductDetail_Screen.click_AddToCartBtn();
        }
        TopBar_Screen.click_MyCartBtn();
    }

    /**
     * Buy now
     */
    protected void buyNow() throws InterruptedException {

        ProductDetail_Screen.click_BuyNowBtn();
        if (hasSizeErrorMgs()) { // We need select size first

            chooseSize();
            ProductDetail_Screen.click_BuyNowBtn();
        }
    }

}
