package testsuite;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObject.*;
import util.AppiumSetupTest;
import java.util.ArrayList;
import java.util.List;
import static util.Constant.PASSWORD;
import static util.Constant.USERNAME;
import static util.VentureText.setText;


public class CheckOutTest extends AppiumSetupTest {

    private BillingAddress_Screen billingAddress_screen;
    private Cart_Screen cart_screen;
    private Init_Screen init_screen;
    private Login_Screen login_screen;
    private OrderSummary_Screen orderSummary_screen;
    private PayMethod_Screen payMethod_screen;
    private ProductDetail_Screen productDetail_screen;
    private Register_Screen register_screen;
    private ShippingAddress_Screen shippingAddress_screen;
    private ShippingInformation_Screen shippingInformation_screen;
    private Thankyou_Screen thankyou_screen;
    private TopBar_Screen topBar_screen;

    @Override
    @BeforeMethod
    public void setUp() throws Exception {
        super.setUp();
        billingAddress_screen = new BillingAddress_Screen(driver);
        cart_screen = new Cart_Screen(driver);
        init_screen = new Init_Screen(driver);
        login_screen = new Login_Screen(driver);
        orderSummary_screen = new OrderSummary_Screen(driver);
        payMethod_screen = new PayMethod_Screen(driver);
        productDetail_screen = new ProductDetail_Screen(driver);
        register_screen = new Register_Screen(driver);
        shippingAddress_screen = new ShippingAddress_Screen(driver);
        shippingInformation_screen = new ShippingInformation_Screen(driver);
        thankyou_screen = new Thankyou_Screen(driver);
        topBar_screen = new TopBar_Screen(driver);
    }

    @Test(dataProvider = "getVenturesDataToTestCashOnDelivery", dataProviderClass = StaticDataProvider.class)
    public void test_CashOnDelivery(String venture) throws Exception {
        String menuWiz = setText(venture).get("menuWiz");
        String categories = setText(venture).get("categories");
        String filterWiz = setText(venture).get("filterWiz");
        String prodWiz = setText(venture).get("prodWiz");

        // Perform Check Out steps
        init_screen.select_Country(venture, menuWiz);
//        checkOut(categories, filterWiz, prodWiz);
        topBar_screen.click_MenuBtn();
        Thread.sleep(1000);
        billingAddress_screen.getHelper().randomSelectProduct(categories, filterWiz, prodWiz);
//        addToCart();
        productDetail_screen.click_AddToCartBtn();
        if (billingAddress_screen.getHelper().hasSizeErrorMgs()) { // We need select size first

//            chooseSize();
            productDetail_screen.click_ChooseSizeBtn();
            billingAddress_screen.getHelper().randClick(productDetail_screen.sizes);
            Thread.sleep(2000);
            productDetail_screen.click_AddToCartBtn();
        }
        topBar_screen.click_MyCartBtn();
        cart_screen.click_CheckoutBtn();

        // Login as default account
//        loginAs(USERNAME, PASSWORD);
        login_screen.input_Email(USERNAME);
        login_screen.click_UserRd();
        login_screen.input_Password(PASSWORD);
        login_screen.click_ContinueBtn();
        login_screen.rocket_app_checkoutweb();
        billingAddress_screen.getHelper().switchToWebView();
        shippingInformation_screen.click_ContinueBtn();
        Thread.sleep(3000);
        Thread.sleep(1000);

        boolean hasCashOnDelivery = false;
        if (!billingAddress_screen.getHelper().isElementPresent(payMethod_screen.radioCODDisabled)
                && billingAddress_screen.getHelper().isElementPresent(payMethod_screen.labelCOD)) { // Cash on delivery is available

            payMethod_screen.click_CashOnDeliveryRadio();
            payMethod_screen.click_ContinueBtn();
            Thread.sleep(3000);
            orderSummary_screen.click_PlaceOrderBtn();
            Thread.sleep(2000);
            billingAddress_screen.getHelper().switchToNativeApp();
            hasCashOnDelivery = true;
            System.out.println(" >>> COD is available!");

        }
//        return hasCashOnDelivery;
        // Check out via COD method
//        boolean hasCashOnDelivery = cashOnDelivery(); /*1 => cash on delivery is available  ;0 => not available */

        // Verify success page
        if (hasCashOnDelivery) {
            if (venture.equals("Singapore")
                    || venture.equals("Philippines")
                    || venture.equals("Malaysia")
                    || venture.equals("Thailand")) {
                billingAddress_screen.getHelper().find("Your order number is").isDisplayed();
            }

            if (venture.equals("Vietnam")) {
                billingAddress_screen.getHelper().find("Mã đơn hàng của bạn là").isDisplayed();
            }

            if (venture.equals("Indonesia")) {
                billingAddress_screen.getHelper().find("Nomor order Anda adalah").isDisplayed();
            }
            System.out.println("Show success page");
        }
    }

    @Test(dataProvider = "getVenturesDataToTestCashOnDeliveryGuest", dataProviderClass = StaticDataProvider.class)
    public void test_CashOnDeliveryGuest(String venture,
                                         String name,
                                         String address,
                                         String phoneNumber) throws Exception {
        String menuWiz = setText(venture).get("menuWiz");
        String categories = setText(venture).get("categories");
        String filterWiz = setText(venture).get("filterWiz");
        String prodWiz = setText(venture).get("prodWiz");

        // Perform Check Out steps
//        buyNowRandomProduct(venture, menuWiz, categories, filterWiz, prodWiz);
        init_screen.select_Country(venture, menuWiz);
        topBar_screen.click_MenuBtn();

        Thread.sleep(1000);
        billingAddress_screen.getHelper().randomSelectProduct(categories, filterWiz, prodWiz);
//        buyNow();
        productDetail_screen.click_BuyNowBtn();
        if (billingAddress_screen.getHelper().hasSizeErrorMgs()) { // We need select size first

//            chooseSize();
            productDetail_screen.click_ChooseSizeBtn();
            billingAddress_screen.getHelper().randClick(productDetail_screen.sizes);
            Thread.sleep(2000);

            productDetail_screen.click_BuyNowBtn();
        }
        Thread.sleep(1000);

        // Login as guest
//        loginAsGuest();

        login_screen.input_Email(billingAddress_screen.getHelper().generateEmail());
        login_screen.click_ContinueBtn();
        Thread.sleep(8000);
        // Create new shipping address
//        createShippingAddress(venture, name, address, phoneNumber);
        // New billing address
        shippingAddress_screen.waitForWebView();
        billingAddress_screen.getHelper().switchToWebView();
        shippingAddress_screen.input_Name(name);


        if (venture.equals("Thailand") || venture.equals("Philippines")
                || venture.equals("Malaysia") || venture.equals("Indonesia")) {

            shippingAddress_screen.input_Address(address);

            billingAddress_screen.getHelper().selectorRandom(shippingAddress_screen.region());
            billingAddress_screen.getHelper().selectorRandom(shippingAddress_screen.city());
            Thread.sleep(1000);
            billingAddress_screen.getHelper().selectorRandom(shippingAddress_screen.ward());
            shippingAddress_screen.input_PhoneNumber(phoneNumber);

        }
        if (venture.equals("Singapore")) {
            shippingAddress_screen.input_PostCode("759674");
            shippingAddress_screen.input_PhoneNumber(phoneNumber);
        }
        if (venture.equals("Vietnam")) {

            shippingAddress_screen.input_Address(address);
            billingAddress_screen.getHelper().selectorRandom(shippingAddress_screen.region());
            billingAddress_screen.getHelper().selectorRandom(shippingAddress_screen.city());
            shippingAddress_screen.input_PhoneNumber(phoneNumber);
        }

        // Submit
        shippingAddress_screen.click_ContinueBtn();
        shippingAddress_screen.click_ContinueBtn();

        boolean hasCashOnDelivery = false;
        if (!billingAddress_screen.getHelper().isElementPresent(payMethod_screen.radioCODDisabled)
                && billingAddress_screen.getHelper().isElementPresent(payMethod_screen.labelCOD)) { // Cash on delivery is available

            payMethod_screen.click_CashOnDeliveryRadio();
            payMethod_screen.click_ContinueBtn();
            Thread.sleep(3000);
            orderSummary_screen.click_PlaceOrderBtn();
            Thread.sleep(2000);
            billingAddress_screen.getHelper().switchToNativeApp();
            hasCashOnDelivery = true;
            System.out.println(" >>> COD is available!");

        }
//        return hasCashOnDelivery;
        // Checkout via COD
//        boolean hasCashOnDelivery = cashOnDelivery();

        // Verify success page
        if (hasCashOnDelivery) {
            if (venture.equals("Singapore")
                    || venture.equals("Philippines")
                    || venture.equals("Malaysia")
                    || venture.equals("Thailand")) {
                billingAddress_screen.getHelper().find("Your order number is").isDisplayed();
            }

            if (venture.equals("Vietnam")) {
                billingAddress_screen.getHelper().find("Mã đơn hàng của bạn là").isDisplayed();
            }

            if (venture.equals("Indonesia")) {
                billingAddress_screen.getHelper().find("Nomor order Anda adalah").isDisplayed();
            }
        }
    }

    @Test(dataProvider = "getVenturesDataToTestBankTransferGuest", dataProviderClass = StaticDataProvider.class)
    public void test_BankTransferGuest(String venture,
                                       String name,
                                       String address,
                                       String phoneNumber,
                                       int bankIndex,
                                       String senderName) throws Exception {
        String menuWiz = setText(venture).get("menuWiz");
        String categories = setText(venture).get("categories");
        String filterWiz = setText(venture).get("filterWiz");
        String prodWiz = setText(venture).get("prodWiz");

        // Perform Check Out steps
//        addRandomProductToCart(venture, menuWiz, categories, filterWiz, prodWiz);
        init_screen.select_Country(venture, menuWiz);
        topBar_screen.click_MenuBtn();

        Thread.sleep(1000);
        billingAddress_screen.getHelper().randomSelectProduct(categories, filterWiz, prodWiz);
//        addToCart();
        productDetail_screen.click_AddToCartBtn();
        if (billingAddress_screen.getHelper().hasSizeErrorMgs()) { // We need select size first

//            chooseSize();
            productDetail_screen.click_ChooseSizeBtn();
            billingAddress_screen.getHelper().randClick(productDetail_screen.sizes);
            Thread.sleep(2000);

            productDetail_screen.click_AddToCartBtn();
        }
        topBar_screen.click_MyCartBtn();

        cart_screen.click_CheckoutBtn();
        Thread.sleep(1000);
        login_screen.input_Email(billingAddress_screen.getHelper().generateEmail());
        login_screen.click_ContinueBtn();
        Thread.sleep(3000);
//        createShippingAddress(venture, name, address, phoneNumber);
        // New billing address
        shippingAddress_screen.waitForWebView();
        billingAddress_screen.getHelper().switchToWebView();
        shippingAddress_screen.input_Name(name);


        if (venture.equals("Thailand") || venture.equals("Philippines")
                || venture.equals("Malaysia") || venture.equals("Indonesia")) {

            shippingAddress_screen.input_Address(address);

            billingAddress_screen.getHelper().selectorRandom(shippingAddress_screen.region());
            billingAddress_screen.getHelper().selectorRandom(shippingAddress_screen.city());
            Thread.sleep(1000);
            billingAddress_screen.getHelper().selectorRandom(shippingAddress_screen.ward());
            shippingAddress_screen.input_PhoneNumber(phoneNumber);

        }
        if (venture.equals("Singapore")) {
            shippingAddress_screen.input_PostCode("759674");
            shippingAddress_screen.input_PhoneNumber(phoneNumber);
        }
        if (venture.equals("Vietnam")) {

            shippingAddress_screen.input_Address(address);
            billingAddress_screen.getHelper().selectorRandom(shippingAddress_screen.region());
            billingAddress_screen.getHelper().selectorRandom(shippingAddress_screen.city());
            shippingAddress_screen.input_PhoneNumber(phoneNumber);
        }

        // Submit
        shippingAddress_screen.click_ContinueBtn();
        shippingAddress_screen.click_ContinueBtn();
//        bankTransfer(bankIndex, senderName);
        // Check the Bank transfer is available or not for this CheckOutTest
        if (billingAddress_screen.getHelper().isElementPresent(payMethod_screen.labelBankTransfer)) {

            // Choose pay method -> bank transfer
            payMethod_screen.click_BankTransferRadio();
            Thread.sleep(1000);

            // Select bank name
            billingAddress_screen.getHelper().selector(payMethod_screen.bankNames , bankIndex);

            // Fill information and Submit
            payMethod_screen.input_SenderName(senderName);
            Thread.sleep(2000);
            payMethod_screen.click_ContinueBtn();
            Thread.sleep(2000);
            orderSummary_screen.click_PlaceOrderBtn(); //Place Order

            // Verify success page
            Assert.assertTrue(billingAddress_screen.getHelper().isPageContains("Pelanggan yang terhormat"));
            Assert.assertTrue(billingAddress_screen.getHelper().isPageContains("Nomor pesanan Anda adalah"));
            Assert.assertTrue(billingAddress_screen.getHelper().isPageContains("telah sukses diterima"));
//            Assert.assertTrue(billingAddress_screen.getHelper().isPageContains("Silakan melakukan pembayaran sebesar"));

        }

    }

    @Test(dataProvider = "getVenturesDataToTestBankTransfer", dataProviderClass = StaticDataProvider.class)
    public void test_BankTransfer(String venture, int bankIndex, String senderName) throws Exception {
        String menuWiz = setText(venture).get("menuWiz");
        String categories = setText(venture).get("categories");
        String filterWiz = setText(venture).get("filterWiz");
        String prodWiz = setText(venture).get("prodWiz");

        init_screen.select_Country(venture, menuWiz);
//        checkOut(categories, filterWiz, prodWiz);
        topBar_screen.click_MenuBtn();
        Thread.sleep(1000);
        billingAddress_screen.getHelper().randomSelectProduct(categories, filterWiz, prodWiz);
//        addToCart();
        productDetail_screen.click_AddToCartBtn();
        if (billingAddress_screen.getHelper().hasSizeErrorMgs()) { // We need select size first

//            chooseSize();
            productDetail_screen.click_ChooseSizeBtn();
            billingAddress_screen.getHelper().randClick(productDetail_screen.sizes);
            Thread.sleep(2000);
            productDetail_screen.click_AddToCartBtn();
        }
        topBar_screen.click_MyCartBtn();
        cart_screen.click_CheckoutBtn();

        // Login as default account
//        loginAs(USERNAME, PASSWORD);
        login_screen.input_Email(USERNAME);
        login_screen.click_UserRd();
        login_screen.input_Password(PASSWORD);
        login_screen.click_ContinueBtn();
        login_screen.rocket_app_checkoutweb();
        billingAddress_screen.getHelper().switchToWebView();
        shippingInformation_screen.click_ContinueBtn();
        Thread.sleep(3000);
//        bankTransfer(bankIndex, senderName);
        // Check the Bank transfer is available or not for this CheckOutTest
        if (billingAddress_screen.getHelper().isElementPresent(payMethod_screen.labelBankTransfer)) {

            // Choose pay method -> bank transfer
            payMethod_screen.click_BankTransferRadio();
            Thread.sleep(1000);

            // Select bank name
            billingAddress_screen.getHelper().selector(payMethod_screen.bankNames , bankIndex);

            // Fill information and Submit
            payMethod_screen.input_SenderName(senderName);
            Thread.sleep(2000);
            payMethod_screen.click_ContinueBtn();
            Thread.sleep(2000);
            orderSummary_screen.click_PlaceOrderBtn(); //Place Order

            // Verify success page
            Assert.assertTrue(billingAddress_screen.getHelper().isPageContains("Pelanggan yang terhormat"));
            Assert.assertTrue(billingAddress_screen.getHelper().isPageContains("Nomor pesanan Anda adalah"));
            Assert.assertTrue(billingAddress_screen.getHelper().isPageContains("telah sukses diterima"));
//            Assert.assertTrue(billingAddress_screen.getHelper().isPageContains("Silakan melakukan pembayaran sebesar"));

        }
    }

    @Test(dataProvider = "getVenturesDataToTestCreditCardInvalid", dataProviderClass = StaticDataProvider.class)
    public void test_CreditCardInvalid(String venture,
                                       String creditNumber,
                                       String customerName,
                                       String securityCode) throws Exception {
        String menuWiz = setText(venture).get("menuWiz");
        String categories = setText(venture).get("categories");
        String filterWiz = setText(venture).get("filterWiz");
        String prodWiz = setText(venture).get("prodWiz");

        // Perform Check Out steps
        init_screen.select_Country(venture, menuWiz);
//        checkOut(categories, filterWiz, prodWiz);
        topBar_screen.click_MenuBtn();
        Thread.sleep(1000);
        billingAddress_screen.getHelper().randomSelectProduct(categories, filterWiz, prodWiz);
//        addToCart();
        productDetail_screen.click_AddToCartBtn();
        if (billingAddress_screen.getHelper().hasSizeErrorMgs()) { // We need select size first

//            chooseSize();
            productDetail_screen.click_ChooseSizeBtn();
            billingAddress_screen.getHelper().randClick(productDetail_screen.sizes);
            Thread.sleep(2000);
            productDetail_screen.click_AddToCartBtn();
        }
        topBar_screen.click_MyCartBtn();
        cart_screen.click_CheckoutBtn();

        // Login as default account
//        loginAs(USERNAME, PASSWORD);
        login_screen.input_Email(USERNAME);
        login_screen.click_UserRd();
        login_screen.input_Password(PASSWORD);
        login_screen.click_ContinueBtn();
        login_screen.rocket_app_checkoutweb();
        billingAddress_screen.getHelper().switchToWebView();
        shippingInformation_screen.click_ContinueBtn();
        Thread.sleep(3000);

        // Check the Credit Cards is available or not for this CheckOutTest
        boolean hasCreditCard = false;

        // Check the Credit Cards is available or not for this CheckOutTest
        if (billingAddress_screen.getHelper().isElementPresent(payMethod_screen.labelCreditCard)) {

            payMethod_screen.click_CreditCardRadio();
            Thread.sleep(1000);

            // waiting & input the CC info
            payMethod_screen.input_CreditNumber(creditNumber);
            payMethod_screen.input_CreditName(customerName);
            payMethod_screen.input_CreditSecurityCode(securityCode);
            billingAddress_screen.getHelper().selectorRandom(payMethod_screen.creditCardMonthCbx());
            billingAddress_screen.getHelper().selectorRandom(payMethod_screen.creditCardYearCbx());
            payMethod_screen.click_ContinueBtn();
            Thread.sleep(1000);
            orderSummary_screen.click_PlaceOrderBtn();
            orderSummary_screen.click_PlaceOrderBtn();
            Thread.sleep(2000);
            billingAddress_screen.getHelper().switchToNativeApp();
            hasCreditCard = true;

        }
//        return hasCreditCard;
//        boolean hasCreditCard = creditCard(creditNumber, customerName, securityCode);

        // Verify message
        if (hasCreditCard) {

            if (venture.equals("Singapore")) {
                billingAddress_screen.getHelper().find("Sorry, the Credit Card number you entered is Invalid").isDisplayed();
            }
            if (venture.equals("Philippines")) {
                billingAddress_screen.getHelper().find("The Credit Card number is not correct").isDisplayed();
            }
            if (venture.equals("Vietnam")) {
                billingAddress_screen.getHelper().find("Số thẻ tín dụng không đúng").isDisplayed();
            }
        }
    }

    @Test(dataProvider = "getVenturesDataToTestCreditCardInvalidGuest", dataProviderClass = StaticDataProvider.class)
    public void test_CreditCardInvalidGuest(String venture,
                                            String creditNumber,
                                            String customerName,
                                            String securityCode,
                                            String name,
                                            String address,
                                            String phoneNumber) throws Exception {
        String menuWiz = setText(venture).get("menuWiz");
        String categories = setText(venture).get("categories");
        String filterWiz = setText(venture).get("filterWiz");
        String prodWiz = setText(venture).get("prodWiz");

        // Perform Check Out steps
//        buyNowRandomProduct(venture, menuWiz, categories, filterWiz, prodWiz);
        init_screen.select_Country(venture, menuWiz);
        topBar_screen.click_MenuBtn();

        Thread.sleep(1000);
        billingAddress_screen.getHelper().randomSelectProduct(categories, filterWiz, prodWiz);
//        buyNow();
        productDetail_screen.click_BuyNowBtn();
        if (billingAddress_screen.getHelper().hasSizeErrorMgs()) { // We need select size first

//            chooseSize();
            productDetail_screen.click_ChooseSizeBtn();
            billingAddress_screen.getHelper().randClick(productDetail_screen.sizes);
            Thread.sleep(2000);

            productDetail_screen.click_BuyNowBtn();
        }
        Thread.sleep(1000);
//        loginAsGuest();

        login_screen.input_Email(billingAddress_screen.getHelper().generateEmail());
        login_screen.click_ContinueBtn();
        Thread.sleep(8000);

        // Create new shipping address
//        createShippingAddress(venture, name, address, phoneNumber);
        // New billing address
        shippingAddress_screen.waitForWebView();
        billingAddress_screen.getHelper().switchToWebView();
        shippingAddress_screen.input_Name(name);


        if (venture.equals("Thailand") || venture.equals("Philippines")
                || venture.equals("Malaysia") || venture.equals("Indonesia")) {

            shippingAddress_screen.input_Address(address);

            billingAddress_screen.getHelper().selectorRandom(shippingAddress_screen.region());
            billingAddress_screen.getHelper().selectorRandom(shippingAddress_screen.city());
            Thread.sleep(1000);
            billingAddress_screen.getHelper().selectorRandom(shippingAddress_screen.ward());
            shippingAddress_screen.input_PhoneNumber(phoneNumber);

        }
        if (venture.equals("Singapore")) {
            shippingAddress_screen.input_PostCode("759674");
            shippingAddress_screen.input_PhoneNumber(phoneNumber);
        }
        if (venture.equals("Vietnam")) {

            shippingAddress_screen.input_Address(address);
            billingAddress_screen.getHelper().selectorRandom(shippingAddress_screen.region());
            billingAddress_screen.getHelper().selectorRandom(shippingAddress_screen.city());
            shippingAddress_screen.input_PhoneNumber(phoneNumber);
        }

        // Submit
        shippingAddress_screen.click_ContinueBtn();

        boolean hasCreditCard = false;

        // Check the Credit Cards is available or not for this CheckOutTest
        if (billingAddress_screen.getHelper().isElementPresent(payMethod_screen.labelCreditCard)) {

            payMethod_screen.click_CreditCardRadio();
            Thread.sleep(1000);

            // waiting & input the CC info
            payMethod_screen.input_CreditNumber(creditNumber);
            payMethod_screen.input_CreditName(customerName);
            payMethod_screen.input_CreditSecurityCode(securityCode);
            billingAddress_screen.getHelper().selectorRandom(payMethod_screen.creditCardMonthCbx());
            billingAddress_screen.getHelper().selectorRandom(payMethod_screen.creditCardYearCbx());
            payMethod_screen.click_ContinueBtn();
            Thread.sleep(1000);
            orderSummary_screen.click_PlaceOrderBtn();
            orderSummary_screen.click_PlaceOrderBtn();
            Thread.sleep(2000);
            billingAddress_screen.getHelper().switchToNativeApp();
            hasCreditCard = true;

        }
//        return hasCreditCard;
        // Check the Credit Cards is available or not for this CheckOutTest
//        boolean hasCreditCard = creditCard(creditNumber, customerName, securityCode);

        // Verify message
        if (hasCreditCard) {

            if (venture.equals("Singapore")) {
                billingAddress_screen.getHelper().find("Sorry, the Credit Card number you entered is Invalid").isDisplayed();
            }
            if (venture.equals("Philippines")) {
                billingAddress_screen.getHelper().find("The Credit Card number is not correct").isDisplayed();
            }
            if (venture.equals("Vietnam")) {
                billingAddress_screen.getHelper().find("Số thẻ tín dụng không đúng").isDisplayed();
            }
        }
    }



    @Test(dataProvider = "getVenturesDataToTestPaypal", dataProviderClass = StaticDataProvider.class)
    public void test_Paypal(String venture) throws Exception {
        String menuWiz = setText(venture).get("menuWiz");
        String categories = setText(venture).get("categories");
        String filterWiz = setText(venture).get("filterWiz");
        String prodWiz = setText(venture).get("prodWiz");

        // Perform Check Out steps
        init_screen.select_Country(venture, menuWiz);
//        checkOut(categories, filterWiz, prodWiz);
        topBar_screen.click_MenuBtn();
        Thread.sleep(1000);
        billingAddress_screen.getHelper().randomSelectProduct(categories, filterWiz, prodWiz);
//        addToCart();
        productDetail_screen.click_AddToCartBtn();
        if (billingAddress_screen.getHelper().hasSizeErrorMgs()) { // We need select size first

//            chooseSize();
            productDetail_screen.click_ChooseSizeBtn();
            billingAddress_screen.getHelper().randClick(productDetail_screen.sizes);
            Thread.sleep(2000);
            productDetail_screen.click_AddToCartBtn();
        }
        topBar_screen.click_MyCartBtn();
        cart_screen.click_CheckoutBtn();

        // Login as default account
//        loginAs(USERNAME, PASSWORD);
        login_screen.input_Email(USERNAME);
        login_screen.click_UserRd();
        login_screen.input_Password(PASSWORD);
        login_screen.click_ContinueBtn();
        login_screen.rocket_app_checkoutweb();
        billingAddress_screen.getHelper().switchToWebView();
        shippingInformation_screen.click_ContinueBtn();
        Thread.sleep(3000);

        // Check the Paypal is available or not for this CheckOutTest
        if (billingAddress_screen.getHelper().isElementPresent(payMethod_screen.labelPaypal)) {

            payMethod_screen.click_PaypalRadio();
            Thread.sleep(1000);

            // Choose pay method -> Paypal
            payMethod_screen.click_ContinueBtn();
            orderSummary_screen.click_PlaceOrderBtn();
            Thread.sleep(10000);

        }
    }

    @Test(dataProvider = "getVenturesDataToTestCreateAddress", dataProviderClass = StaticDataProvider.class)
    public void test_CreateShippingAddress(String venture,
                                           String name,
                                           String address,
                                           String phoneNumber) throws Exception {
        String menuWiz = setText(venture).get("menuWiz");
        String categories = setText(venture).get("categories");
        String filterWiz = setText(venture).get("filterWiz");
        String prodWiz = setText(venture).get("prodWiz");

        // Perform Check Out steps
//        addRandomProductToCart(venture, menuWiz, categories, filterWiz, prodWiz);
        init_screen.select_Country(venture, menuWiz);
        topBar_screen.click_MenuBtn();

        Thread.sleep(1000);
        billingAddress_screen.getHelper().randomSelectProduct(categories, filterWiz, prodWiz);
//        addToCart();
        productDetail_screen.click_AddToCartBtn();
        if (billingAddress_screen.getHelper().hasSizeErrorMgs()) { // We need select size first

//            chooseSize();
            productDetail_screen.click_ChooseSizeBtn();
            billingAddress_screen.getHelper().randClick(productDetail_screen.sizes);
            Thread.sleep(2000);

            productDetail_screen.click_AddToCartBtn();
        }
        topBar_screen.click_MyCartBtn();

        cart_screen.click_CheckoutBtn();
//        loginAs(USERNAME, PASSWORD);
        // Choose checkout method: Guest or User
        login_screen.input_Email(USERNAME);
        login_screen.click_UserRd();
        login_screen.input_Password(PASSWORD);
        login_screen.click_ContinueBtn();
        login_screen.rocket_app_checkoutweb();
        billingAddress_screen.getHelper().switchToWebView();
        login_screen.click_ContinueBtn();

        Thread.sleep(5000);
//        login_screen.wait_ForWebView();
        login_screen.rocket_app_checkoutweb();
        Thread.sleep(2000);
        billingAddress_screen.getHelper().switchToWebView();
        Thread.sleep(3000);

        // Create new address
        billingAddress_screen.getHelper().swipeDown();
        shippingInformation_screen.click_NewAddress(); // Click New address
        Thread.sleep(2000);
//        createShippingAddress(venture, name, address, phoneNumber);
        // New billing address
        shippingAddress_screen.waitForWebView();
        billingAddress_screen.getHelper().switchToWebView();
        shippingAddress_screen.input_Name(name);


        if (venture.equals("Thailand") || venture.equals("Philippines")
                || venture.equals("Malaysia") || venture.equals("Indonesia")) {

            shippingAddress_screen.input_Address(address);

            billingAddress_screen.getHelper().selectorRandom(shippingAddress_screen.region());
            billingAddress_screen.getHelper().selectorRandom(shippingAddress_screen.city());
            Thread.sleep(1000);
            billingAddress_screen.getHelper().selectorRandom(shippingAddress_screen.ward());
            shippingAddress_screen.input_PhoneNumber(phoneNumber);

        }
        if (venture.equals("Singapore")) {
            shippingAddress_screen.input_PostCode("759674");
            shippingAddress_screen.input_PhoneNumber(phoneNumber);
        }
        if (venture.equals("Vietnam")) {

            shippingAddress_screen.input_Address(address);
            billingAddress_screen.getHelper().selectorRandom(shippingAddress_screen.region());
            billingAddress_screen.getHelper().selectorRandom(shippingAddress_screen.city());
            shippingAddress_screen.input_PhoneNumber(phoneNumber);
        }

        // Submit
        shippingAddress_screen.click_ContinueBtn();

        // Verify new address appears on delivery information page or Not
        String pageSource = driver.getPageSource();
        Assert.assertTrue(pageSource.contains(name));
        Assert.assertTrue(pageSource.contains(address));
        Assert.assertTrue(pageSource.contains(phoneNumber));
    }

    @Test(dataProvider = "getVenturesDataToTestEditShippingAddress", dataProviderClass = StaticDataProvider.class)
    public void test_EditShippingAddress(String venture,
                                         String name,
                                         String address,
                                         String phoneNumber) throws Exception {
        String menuWiz = setText(venture).get("menuWiz");
        String categories = setText(venture).get("categories");
        String filterWiz = setText(venture).get("filterWiz");
        String prodWiz = setText(venture).get("prodWiz");

        // Perform Check Out steps
        init_screen.select_Country(venture, menuWiz);
//        checkOut(categories, filterWiz, prodWiz);
        topBar_screen.click_MenuBtn();
        Thread.sleep(1000);
        billingAddress_screen.getHelper().randomSelectProduct(categories, filterWiz, prodWiz);
//        addToCart();
        productDetail_screen.click_AddToCartBtn();
        if (billingAddress_screen.getHelper().hasSizeErrorMgs()) { // We need select size first

//            chooseSize();
            productDetail_screen.click_ChooseSizeBtn();
            billingAddress_screen.getHelper().randClick(productDetail_screen.sizes);
            Thread.sleep(2000);
            productDetail_screen.click_AddToCartBtn();
        }
        topBar_screen.click_MyCartBtn();
        cart_screen.click_CheckoutBtn();

        // Login as default account
//        loginAs(USERNAME, PASSWORD);
        login_screen.input_Email(USERNAME);
        login_screen.click_UserRd();
        login_screen.input_Password(PASSWORD);
        login_screen.click_ContinueBtn();
        login_screen.rocket_app_checkoutweb();
        billingAddress_screen.getHelper().switchToWebView();
        shippingInformation_screen.click_ContinueBtn();
        Thread.sleep(3000);

        // Check the Cash On Delivery is available or not for this CheckOutTest
        if (!billingAddress_screen.getHelper().isElementPresent(payMethod_screen.radioCODDisabled)
                && billingAddress_screen.getHelper().isElementPresent(payMethod_screen.labelCOD)) {

            payMethod_screen.click_CashOnDeliveryRadio();
            payMethod_screen.click_ContinueBtn();
            Thread.sleep(3000);
            orderSummary_screen.click_EditShippingAddress();

            // Random choose address to edit
            billingAddress_screen.getHelper().randClick(shippingInformation_screen.editAddress);

            // Edit shipping address
//            editShippingAddress(venture, name, address, phoneNumber);
            // Edit billing address
            billingAddress_screen.getHelper().switchToNativeApp();
            List<WebElement> editTexts = driver.findElementsByClassName("android.widget.EditText");
            List<WebElement> buttons = new ArrayList<WebElement>();
            editTexts.get(0).clear();
            editTexts.get(0).sendKeys(name);
            editTexts.get(1).clear();
            editTexts.get(1).sendKeys(address);


            if (venture.equals("Thailand") || venture.equals("Philippines")
                    || venture.equals("Malaysia") || venture.equals("Indonesia")) {

                buttons = driver.findElementsByClassName("android.widget.Button");
                billingAddress_screen.getHelper().selectorRandom(buttons.get(0)); // select random Region
                billingAddress_screen.getHelper().selectorRandom(buttons.get(1)); // select random City
                billingAddress_screen.getHelper().selectorRandom(buttons.get(2)); // select random Postcode
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
                billingAddress_screen.getHelper().selectorRandom(buttons.get(0)); // select random Region
                billingAddress_screen.getHelper().selectorRandom(buttons.get(1)); // select random City
                editTexts.get(2).clear();
                editTexts.get(2).sendKeys(phoneNumber);
            }

            // Submit
            buttons.get(buttons.size() - 1).click();
            billingAddress_screen.getHelper().switchToWebView();
            Thread.sleep(4000);

            // Verify billing address has changed
            String pageSource = driver.getPageSource();
            Assert.assertTrue(pageSource.contains(name));
            Assert.assertTrue(pageSource.contains(address));
            Assert.assertTrue(pageSource.contains(phoneNumber));

            // Submit order
            orderSummary_screen.click_PlaceOrderBtn(); // Place your order
//            cashOnDelivery(); // Checkout again
            if (!billingAddress_screen.getHelper().isElementPresent(payMethod_screen.radioCODDisabled)
                    && billingAddress_screen.getHelper().isElementPresent(payMethod_screen.labelCOD)) { // Cash on delivery is available

                payMethod_screen.click_CashOnDeliveryRadio();
                payMethod_screen.click_ContinueBtn();
                Thread.sleep(3000);
                orderSummary_screen.click_PlaceOrderBtn();
                Thread.sleep(2000);
                billingAddress_screen.getHelper().switchToNativeApp();
                System.out.println(" >>> COD is available!");

            }

        }
    }

    @Test(dataProvider = "getVenturesDataToTestDifferentAddress", dataProviderClass = StaticDataProvider.class)
    public void test_DifferentBillingAddress(String venture,
                                             String name,
                                             String address,
                                             String phoneNumber) throws Exception {
        String menuWiz = setText(venture).get("menuWiz");
        String categories = setText(venture).get("categories");
        String filterWiz = setText(venture).get("filterWiz");
        String prodWiz = setText(venture).get("prodWiz");

        // Perform Check Out steps
//        addRandomProductToCart(venture, menuWiz, categories, filterWiz, prodWiz);
        init_screen.select_Country(venture, menuWiz);
        topBar_screen.click_MenuBtn();

        Thread.sleep(1000);
        billingAddress_screen.getHelper().randomSelectProduct(categories, filterWiz, prodWiz);
//        addToCart();
        productDetail_screen.click_AddToCartBtn();
        if (billingAddress_screen.getHelper().hasSizeErrorMgs()) { // We need select size first

//            chooseSize();
            productDetail_screen.click_ChooseSizeBtn();
            billingAddress_screen.getHelper().randClick(productDetail_screen.sizes);
            Thread.sleep(2000);

            productDetail_screen.click_AddToCartBtn();
        }
        topBar_screen.click_MyCartBtn();

        cart_screen.click_CheckoutBtn();
//        loginAs(USERNAME, PASSWORD);
        login_screen.input_Email(USERNAME);
        login_screen.click_UserRd();
        login_screen.input_Password(PASSWORD);
        login_screen.click_ContinueBtn();
        login_screen.rocket_app_checkoutweb();
        billingAddress_screen.getHelper().switchToWebView();

        // Choose Use different billing address
        shippingInformation_screen.click_UseDifferent();
//        createBillingAddress(venture, name, address, phoneNumber);
        // New billing address
        billingAddress_screen.input_Name(name);
        billingAddress_screen.input_Address(address);
        if (venture.equals("Thailand") || venture.equals("Philippines")
                || venture.equals("Malaysia") || venture.equals("Indonesia")) {

            billingAddress_screen.getHelper().selectorRandom(billingAddress_screen.region()); // select random Region
            Thread.sleep(2000); // wait for loading data from dropdown list
            billingAddress_screen.getHelper().selectorRandom(billingAddress_screen.city()); // select random City
            Thread.sleep(2000); // wait for loading data from dropdown list
            billingAddress_screen.getHelper().selectorRandom(billingAddress_screen.ward()); // select random Postcode
            billingAddress_screen.input_PhoneNumber(phoneNumber);

        }
        if (venture.equals("Singapore")) {
            billingAddress_screen.input_PostCode("759674");
            billingAddress_screen.input_PhoneNumber(phoneNumber);

        }
        if (venture.equals("Vietnam")) {
            billingAddress_screen.getHelper().selectorRandom(billingAddress_screen.region()); // select random Region
            Thread.sleep(2000); // wait for loading data from dropdown list
            billingAddress_screen.getHelper().selectorRandom(billingAddress_screen.city()); // select random City
            billingAddress_screen.input_PhoneNumber(phoneNumber);
        }

        // Submit
        billingAddress_screen.click_ContinueBtn();
    }

    @Test(dataProvider = "getVenturesDataToTestEditBillingAddress", dataProviderClass = StaticDataProvider.class)
    public void test_EditBillingAddress(String venture,
                                        String name,
                                        String address,
                                        String phoneNumber) throws Exception {
        String menuWiz = setText(venture).get("menuWiz");
        String categories = setText(venture).get("categories");
        String filterWiz = setText(venture).get("filterWiz");
        String prodWiz = setText(venture).get("prodWiz");

        // Perform Check Out steps
        init_screen.select_Country(venture, menuWiz);
//        checkOut(categories, filterWiz, prodWiz);
        topBar_screen.click_MenuBtn();
        Thread.sleep(1000);
        billingAddress_screen.getHelper().randomSelectProduct(categories, filterWiz, prodWiz);
//        addToCart();
        productDetail_screen.click_AddToCartBtn();
        if (billingAddress_screen.getHelper().hasSizeErrorMgs()) { // We need select size first

//            chooseSize();
            productDetail_screen.click_ChooseSizeBtn();
            billingAddress_screen.getHelper().randClick(productDetail_screen.sizes);
            Thread.sleep(2000);
            productDetail_screen.click_AddToCartBtn();
        }
        topBar_screen.click_MyCartBtn();
        cart_screen.click_CheckoutBtn();

        // Login as default account
//        loginAs(USERNAME, PASSWORD);
        login_screen.input_Email(USERNAME);
        login_screen.click_UserRd();
        login_screen.input_Password(PASSWORD);
        login_screen.click_ContinueBtn();
        login_screen.rocket_app_checkoutweb();
        billingAddress_screen.getHelper().switchToWebView();
        shippingInformation_screen.click_ContinueBtn();
        Thread.sleep(3000);

        // Check the Cash On Delivery is available or not for this CheckOutTest
        if (!billingAddress_screen.getHelper().isElementPresent(payMethod_screen.radioCODDisabled)
                && billingAddress_screen.getHelper().isElementPresent(payMethod_screen.labelCOD)) {

            payMethod_screen.click_CashOnDeliveryRadio();
            payMethod_screen.click_ContinueBtn();
            Thread.sleep(5000);
            orderSummary_screen.click_EditBillingAddress();

            // Edit billing address
//            createBillingAddress(venture, name, address, phoneNumber);
            // New billing address
            billingAddress_screen.input_Name(name);
            billingAddress_screen.input_Address(address);
            if (venture.equals("Thailand") || venture.equals("Philippines")
                    || venture.equals("Malaysia") || venture.equals("Indonesia")) {

                billingAddress_screen.getHelper().selectorRandom(billingAddress_screen.region()); // select random Region
                Thread.sleep(2000); // wait for loading data from dropdown list
                billingAddress_screen.getHelper().selectorRandom(billingAddress_screen.city()); // select random City
                Thread.sleep(2000); // wait for loading data from dropdown list
                billingAddress_screen.getHelper().selectorRandom(billingAddress_screen.ward()); // select random Postcode
                billingAddress_screen.input_PhoneNumber(phoneNumber);

            }
            if (venture.equals("Singapore")) {
                billingAddress_screen.input_PostCode("759674");
                billingAddress_screen.input_PhoneNumber(phoneNumber);

            }
            if (venture.equals("Vietnam")) {
                billingAddress_screen.getHelper().selectorRandom(billingAddress_screen.region()); // select random Region
                Thread.sleep(2000); // wait for loading data from dropdown list
                billingAddress_screen.getHelper().selectorRandom(billingAddress_screen.city()); // select random City
                billingAddress_screen.input_PhoneNumber(phoneNumber);
            }

            // Submit
            billingAddress_screen.click_ContinueBtn();

            // Continue checking out
            payMethod_screen.click_CashOnDeliveryRadio();
            payMethod_screen.click_ContinueBtn();
            Thread.sleep(4000);

            // Verify billing address has changed
            String pageSource = driver.getPageSource();
            Assert.assertTrue(pageSource.contains(name));
            Assert.assertTrue(pageSource.contains(address));
            Assert.assertTrue(pageSource.contains(phoneNumber));

            // Submit order
            orderSummary_screen.click_PlaceOrderBtn();

        }
    }



    @Test(dataProvider = "getVenturesDataToTestRemoveFromCart", dataProviderClass = StaticDataProvider.class)
    public void test_RemoveFromCart(String venture) throws Exception {
        String menuWiz = setText(venture).get("menuWiz");
        String categories = setText(venture).get("categories");
        String filterWiz = setText(venture).get("filterWiz");
        String prodWiz = setText(venture).get("prodWiz");

        // Perform Check Out steps
//        addRandomProductToCart(venture, menuWiz, categories, filterWiz, prodWiz);
        init_screen.select_Country(venture, menuWiz);
        topBar_screen.click_MenuBtn();

        Thread.sleep(1000);
        billingAddress_screen.getHelper().randomSelectProduct(categories, filterWiz, prodWiz);
//        addToCart();
        productDetail_screen.click_AddToCartBtn();
        if (billingAddress_screen.getHelper().hasSizeErrorMgs()) { // We need select size first

//            chooseSize();
            productDetail_screen.click_ChooseSizeBtn();
            billingAddress_screen.getHelper().randClick(productDetail_screen.sizes);
            Thread.sleep(2000);

            productDetail_screen.click_AddToCartBtn();
        }
        topBar_screen.click_MyCartBtn();

        cart_screen.click_CheckoutBtn();
//        loginAs(USERNAME, PASSWORD);
        login_screen.input_Email(USERNAME);
        login_screen.click_UserRd();
        login_screen.input_Password(PASSWORD);
        login_screen.click_ContinueBtn();
        login_screen.rocket_app_checkoutweb();
        billingAddress_screen.getHelper().switchToWebView();
        billingAddress_screen.getHelper().switchToNativeApp();

        // Go to myCart and remove all product
        topBar_screen.click_MyCartBtn();
        List<WebElement> arrDelete = new ArrayList<WebElement>();
        do {
            arrDelete = cart_screen.deleteButtons();
            if (arrDelete.size() > 0) {

                arrDelete.get(0).click();
                cart_screen.click_DeleteConfirm(); // Remove confirm

            }
            arrDelete = cart_screen.deleteButtons();
        } while (arrDelete.size() > 0);
    }

    @Test(dataProvider = "getVenturesDataToTestEditPaymentMethod", dataProviderClass = StaticDataProvider.class)
    public void test_EditPaymentMethod(String venture) throws Exception {
        String menuWiz = setText(venture).get("menuWiz");
        String categories = setText(venture).get("categories");
        String filterWiz = setText(venture).get("filterWiz");
        String prodWiz = setText(venture).get("prodWiz");

        // Perform Check Out steps
        init_screen.select_Country(venture, menuWiz);
//        checkOut(categories, filterWiz, prodWiz);
        topBar_screen.click_MenuBtn();
        Thread.sleep(1000);
        billingAddress_screen.getHelper().randomSelectProduct(categories, filterWiz, prodWiz);
//        addToCart();
        productDetail_screen.click_AddToCartBtn();
        if (billingAddress_screen.getHelper().hasSizeErrorMgs()) { // We need select size first

//            chooseSize();
            productDetail_screen.click_ChooseSizeBtn();
            billingAddress_screen.getHelper().randClick(productDetail_screen.sizes);
            Thread.sleep(2000);
            productDetail_screen.click_AddToCartBtn();
        }
        topBar_screen.click_MyCartBtn();
        cart_screen.click_CheckoutBtn();

        // Login as default account
//        loginAs(USERNAME, PASSWORD);
        login_screen.input_Email(USERNAME);
        login_screen.click_UserRd();
        login_screen.input_Password(PASSWORD);
        login_screen.click_ContinueBtn();
        login_screen.rocket_app_checkoutweb();
        billingAddress_screen.getHelper().switchToWebView();
        shippingInformation_screen.click_ContinueBtn();
        Thread.sleep(3000);

        // Check out by COD then edit payment method
        if (!billingAddress_screen.getHelper().isElementPresent(payMethod_screen.radioCODDisabled)
                && billingAddress_screen.getHelper().isElementPresent(payMethod_screen.labelCOD)) {

            payMethod_screen.click_CashOnDeliveryRadio();
            payMethod_screen.click_ContinueBtn();
            Thread.sleep(3000);

            // Change payment method
            orderSummary_screen.click_ChangePayment();
            billingAddress_screen.getHelper().randClick(payMethod_screen.paymentMethods);
            payMethod_screen.click_ContinueBtn();
            Thread.sleep(2000);

        }
    }

    // Staging only
    @Test(dataProvider = "getVenturesDataToTestCoupon", dataProviderClass = StaticDataProvider.class)
    public void test_Coupon(String venture, String coupon) throws Exception {
        String menuWiz = setText(venture).get("menuWiz");
        String categories = setText(venture).get("categories");
        String filterWiz = setText(venture).get("filterWiz");
        String prodWiz = setText(venture).get("prodWiz");

        // Perform Check Out steps
        init_screen.select_Country(venture, menuWiz);
//        checkOut(categories, filterWiz, prodWiz);
        topBar_screen.click_MenuBtn();
        Thread.sleep(1000);
        billingAddress_screen.getHelper().randomSelectProduct(categories, filterWiz, prodWiz);
//        addToCart();
        productDetail_screen.click_AddToCartBtn();
        if (billingAddress_screen.getHelper().hasSizeErrorMgs()) { // We need select size first

//            chooseSize();
            productDetail_screen.click_ChooseSizeBtn();
            billingAddress_screen.getHelper().randClick(productDetail_screen.sizes);
            Thread.sleep(2000);
            productDetail_screen.click_AddToCartBtn();
        }
        topBar_screen.click_MyCartBtn();
        cart_screen.click_CheckoutBtn();

        // Login as default account
//        loginAs(USERNAME, PASSWORD);
        login_screen.input_Email(USERNAME);
        login_screen.click_UserRd();
        login_screen.input_Password(PASSWORD);
        login_screen.click_ContinueBtn();
        login_screen.rocket_app_checkoutweb();
        billingAddress_screen.getHelper().switchToWebView();
        shippingInformation_screen.click_ContinueBtn();
        Thread.sleep(3000);

        // Check out by COD
        if (!billingAddress_screen.getHelper().isElementPresent(payMethod_screen.radioCODDisabled)
                && billingAddress_screen.getHelper().isElementPresent(payMethod_screen.labelCOD)) {

            payMethod_screen.click_CashOnDeliveryRadio();
            payMethod_screen.click_ContinueBtn();
            Thread.sleep(3000);

            // Input coupon code
            orderSummary_screen.click_addCouponBtn();
            orderSummary_screen.input_Coupon(coupon);
            orderSummary_screen.click_SubmitCoupon();
            orderSummary_screen.click_PlaceOrderBtn(); // Place your oder
            Thread.sleep(2000);

        }
    }

    @Test(dataProvider = "getVenturesDataToTestShareOrder", dataProviderClass = StaticDataProvider.class)
    public void test_ShareOrder(String venture) throws Exception {
        String menuWiz = setText(venture).get("menuWiz");
        String categories = setText(venture).get("categories");
        String filterWiz = setText(venture).get("filterWiz");
        String prodWiz = setText(venture).get("prodWiz");

        // Perform Check Out steps
        init_screen.select_Country(venture, menuWiz);
//        checkOut(categories, filterWiz, prodWiz);
        topBar_screen.click_MenuBtn();
        Thread.sleep(1000);
        billingAddress_screen.getHelper().randomSelectProduct(categories, filterWiz, prodWiz);
//        addToCart();
        productDetail_screen.click_AddToCartBtn();
        if (billingAddress_screen.getHelper().hasSizeErrorMgs()) { // We need select size first

//            chooseSize();
            productDetail_screen.click_ChooseSizeBtn();
            billingAddress_screen.getHelper().randClick(productDetail_screen.sizes);
            Thread.sleep(2000);
            productDetail_screen.click_AddToCartBtn();
        }
        topBar_screen.click_MyCartBtn();
        cart_screen.click_CheckoutBtn();

        // Login as default account
//        loginAs(USERNAME, PASSWORD);
        login_screen.input_Email(USERNAME);
        login_screen.click_UserRd();
        login_screen.input_Password(PASSWORD);
        login_screen.click_ContinueBtn();
        login_screen.rocket_app_checkoutweb();
        billingAddress_screen.getHelper().switchToWebView();
        shippingInformation_screen.click_ContinueBtn();
        Thread.sleep(3000);
        Thread.sleep(1000);

        // Check the Cash On Delivery is available or not for this CheckOutTest
        if (!billingAddress_screen.getHelper().isElementPresent(payMethod_screen.radioCODDisabled)
                && billingAddress_screen.getHelper().isElementPresent(payMethod_screen.labelCOD)) {

            payMethod_screen.click_CashOnDeliveryRadio();
            payMethod_screen.click_ContinueBtn();
            Thread.sleep(3000);
            orderSummary_screen.click_PlaceOrderBtn();
            Thread.sleep(2000);
            billingAddress_screen.getHelper().switchToNativeApp();

            // Share order
            thankyou_screen.click_ShareBtn();
            billingAddress_screen.getHelper().randClick(thankyou_screen.sharerApps); // Random & choose application to share order

        }
    }

    // Staging only
    @Test(dataProvider = "getVenturesDataToTestSavedCreditCard", dataProviderClass = StaticDataProvider.class)
    public void test_SavedCreditCard(String venture) throws Exception {
        String menuWiz = setText(venture).get("menuWiz");
        String categories = setText(venture).get("categories");
        String filterWiz = setText(venture).get("filterWiz");
        String prodWiz = setText(venture).get("prodWiz");

        // Perform Check Out steps
        init_screen.select_Country(venture, menuWiz);
//        checkOut(categories, filterWiz, prodWiz);
        topBar_screen.click_MenuBtn();
        Thread.sleep(1000);
        billingAddress_screen.getHelper().randomSelectProduct(categories, filterWiz, prodWiz);
//        addToCart();
        productDetail_screen.click_AddToCartBtn();
        if (billingAddress_screen.getHelper().hasSizeErrorMgs()) { // We need select size first

//            chooseSize();
            productDetail_screen.click_ChooseSizeBtn();
            billingAddress_screen.getHelper().randClick(productDetail_screen.sizes);
            Thread.sleep(2000);
            productDetail_screen.click_AddToCartBtn();
        }
        topBar_screen.click_MyCartBtn();
        cart_screen.click_CheckoutBtn();

        // Login as default account
//        loginAs(USERNAME, PASSWORD);
        login_screen.input_Email(USERNAME);
        login_screen.click_UserRd();
        login_screen.input_Password(PASSWORD);
        login_screen.click_ContinueBtn();
        login_screen.rocket_app_checkoutweb();
        billingAddress_screen.getHelper().switchToWebView();
        shippingInformation_screen.click_ContinueBtn();
        Thread.sleep(3000);

        // Check the Saved Credit Cards is available or not
        if (billingAddress_screen.getHelper().isElementPresent(payMethod_screen.creditCards)) {

            billingAddress_screen.getHelper().randClick(payMethod_screen.creditCards);
            payMethod_screen.click_ContinueBtn();
            billingAddress_screen.getHelper().swipeDown();
            orderSummary_screen.click_PlaceOrderBtn(); // Place your order
            Thread.sleep(2000);

        }
    }

    @Test(dataProvider = "getVenturesDataToTestNewAccount", dataProviderClass = StaticDataProvider.class)
    public void test_NewAccount(String venture) throws Exception {
        String menuWiz = setText(venture).get("menuWiz");
        String categories = setText(venture).get("categories");
        String filterWiz = setText(venture).get("filterWiz");
        String prodWiz = setText(venture).get("prodWiz");

        // Perform Check Out steps
//        addRandomProductToCart(venture, menuWiz, categories, filterWiz, prodWiz);

        init_screen.select_Country(venture, menuWiz);
        topBar_screen.click_MenuBtn();

        Thread.sleep(1000);
        billingAddress_screen.getHelper().randomSelectProduct(categories, filterWiz, prodWiz);
//        addToCart();
        productDetail_screen.click_AddToCartBtn();
        if (billingAddress_screen.getHelper().hasSizeErrorMgs()) { // We need select size first

//            chooseSize();
            productDetail_screen.click_ChooseSizeBtn();
            billingAddress_screen.getHelper().randClick(productDetail_screen.sizes);
            Thread.sleep(2000);

            productDetail_screen.click_AddToCartBtn();
        }
        topBar_screen.click_MyCartBtn();

        cart_screen.click_CheckoutBtn();
        // Create new account
        login_screen.click_RegisterBtn();
//        registerAccount();
        // Random email, password, name
        String email = billingAddress_screen.getHelper().generateEmail();
        String password = billingAddress_screen.getHelper().generatePassword();
        String name = billingAddress_screen.getHelper().generateAlphabet(5);

        // Input email, password, name
        register_screen.input_Email(email);
        register_screen.input_Password(password);
        register_screen.input_RePassword(password);
        register_screen.input_Name(name);
        register_screen.click_ShowPassword(); // Show password

        // Submit
        register_screen.click_SubmitBtn();
        Thread.sleep(3000);

        Thread.sleep(1000);
        billingAddress_screen.getHelper().switchToWebView();

        // Create new shipping address
//        String name = generateAlphabet(10);
        String address = billingAddress_screen.getHelper().generateAlphabet(10);
        String phoneNumber = billingAddress_screen.getHelper().generateNumber(10);
//        editShippingAddress(venture, name, address, phoneNumber);
        // Edit billing address
        billingAddress_screen.getHelper().switchToNativeApp();
        List<WebElement> editTexts = driver.findElementsByClassName("android.widget.EditText");
        List<WebElement> buttons = new ArrayList<WebElement>();
        editTexts.get(0).clear();
        editTexts.get(0).sendKeys(name);
        editTexts.get(1).clear();
        editTexts.get(1).sendKeys(address);


        if (venture.equals("Thailand") || venture.equals("Philippines")
                || venture.equals("Malaysia") || venture.equals("Indonesia")) {

            buttons = driver.findElementsByClassName("android.widget.Button");
            billingAddress_screen.getHelper().selectorRandom(buttons.get(0)); // select random Region
            billingAddress_screen.getHelper().selectorRandom(buttons.get(1)); // select random City
            billingAddress_screen.getHelper().selectorRandom(buttons.get(2)); // select random Postcode
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
            billingAddress_screen.getHelper().selectorRandom(buttons.get(0)); // select random Region
            billingAddress_screen.getHelper().selectorRandom(buttons.get(1)); // select random City
            editTexts.get(2).clear();
            editTexts.get(2).sendKeys(phoneNumber);
        }

        // Submit
        buttons.get(buttons.size() - 1).click();
        billingAddress_screen.getHelper().switchToWebView();
//        cashOnDelivery();
        if (!billingAddress_screen.getHelper().isElementPresent(payMethod_screen.radioCODDisabled)
                && billingAddress_screen.getHelper().isElementPresent(payMethod_screen.labelCOD)) { // Cash on delivery is available

            payMethod_screen.click_CashOnDeliveryRadio();
            payMethod_screen.click_ContinueBtn();
            Thread.sleep(3000);
            orderSummary_screen.click_PlaceOrderBtn();
            Thread.sleep(2000);
            billingAddress_screen.getHelper().switchToNativeApp();
            System.out.println(" >>> COD is available!");

        }
//        return hasCashOnDelivery;
    }

    @Test(dataProvider = "getVenturesDataToTestNewBillingAddress", dataProviderClass = StaticDataProvider.class)
    public void test_NewBillingAddress(String venture,
                                       String name,
                                       String address,
                                       String phoneNumber) throws Exception {
        String menuWiz = setText(venture).get("menuWiz");
        String categories = setText(venture).get("categories");
        String filterWiz = setText(venture).get("filterWiz");
        String prodWiz = setText(venture).get("prodWiz");

        // Perform Check Out steps
        init_screen.select_Country(venture, menuWiz);
//        checkOut(categories, filterWiz, prodWiz);
        topBar_screen.click_MenuBtn();
        Thread.sleep(1000);
        billingAddress_screen.getHelper().randomSelectProduct(categories, filterWiz, prodWiz);
//        addToCart();
        productDetail_screen.click_AddToCartBtn();
        if (billingAddress_screen.getHelper().hasSizeErrorMgs()) { // We need select size first

//            chooseSize();
            productDetail_screen.click_ChooseSizeBtn();
            billingAddress_screen.getHelper().randClick(productDetail_screen.sizes);
            Thread.sleep(2000);
            productDetail_screen.click_AddToCartBtn();
        }
        topBar_screen.click_MyCartBtn();
        cart_screen.click_CheckoutBtn();

        // Login as default account
//        loginAs(USERNAME, PASSWORD);
        login_screen.input_Email(USERNAME);
        login_screen.click_UserRd();
        login_screen.input_Password(PASSWORD);
        login_screen.click_ContinueBtn();
        login_screen.rocket_app_checkoutweb();
        billingAddress_screen.getHelper().switchToWebView();
        shippingInformation_screen.click_ContinueBtn();
        Thread.sleep(3000);
        Thread.sleep(1000);

        // Check the Cash On Delivery is available or not for this CheckOutTest
        if (!billingAddress_screen.getHelper().isElementPresent(payMethod_screen.radioCODDisabled)
                && billingAddress_screen.getHelper().isElementPresent(payMethod_screen.labelCOD)) { // Cash on delivery is available

            payMethod_screen.click_CashOnDeliveryRadio();
            payMethod_screen.click_ContinueBtn();
            Thread.sleep(3000);
            // Edit billing address

            orderSummary_screen.click_EditBillingAddress();
//            createBillingAddress(venture, name, address, phoneNumber);
            // New billing address
            billingAddress_screen.input_Name(name);
            billingAddress_screen.input_Address(address);
            if (venture.equals("Thailand") || venture.equals("Philippines")
                    || venture.equals("Malaysia") || venture.equals("Indonesia")) {

                billingAddress_screen.getHelper().selectorRandom(billingAddress_screen.region()); // select random Region
                Thread.sleep(2000); // wait for loading data from dropdown list
                billingAddress_screen.getHelper().selectorRandom(billingAddress_screen.city()); // select random City
                Thread.sleep(2000); // wait for loading data from dropdown list
                billingAddress_screen.getHelper().selectorRandom(billingAddress_screen.ward()); // select random Postcode
                billingAddress_screen.input_PhoneNumber(phoneNumber);

            }
            if (venture.equals("Singapore")) {
                billingAddress_screen.input_PostCode("759674");
                billingAddress_screen.input_PhoneNumber(phoneNumber);

            }
            if (venture.equals("Vietnam")) {
                billingAddress_screen.getHelper().selectorRandom(billingAddress_screen.region()); // select random Region
                Thread.sleep(2000); // wait for loading data from dropdown list
                billingAddress_screen.getHelper().selectorRandom(billingAddress_screen.city()); // select random City
                billingAddress_screen.input_PhoneNumber(phoneNumber);
            }

            // Submit
            billingAddress_screen.click_ContinueBtn();
            billingAddress_screen.click_ContinueBtn();
            Thread.sleep(2000);
            payMethod_screen.click_CashOnDeliveryRadio();
            payMethod_screen.click_ContinueBtn();
            orderSummary_screen.click_PlaceOrderBtn();

        }
    }

    @Test(dataProvider = "getVenturesDataToTestBankTransferDifferentBillingAddress", dataProviderClass = StaticDataProvider.class)
    public void test_BankTransferEditBillingAddress(String venture,
                                                    int bankIndex,
                                                    String senderName,
                                                    String name,
                                                    String address,
                                                    String phoneNumber) throws Exception {
        String menuWiz = setText(venture).get("menuWiz");
        String categories = setText(venture).get("categories");
        String filterWiz = setText(venture).get("filterWiz");
        String prodWiz = setText(venture).get("prodWiz");

        // Perform Check Out steps
        init_screen.select_Country(venture, menuWiz);
//        checkOut(categories, filterWiz, prodWiz);
        topBar_screen.click_MenuBtn();
        Thread.sleep(1000);
        billingAddress_screen.getHelper().randomSelectProduct(categories, filterWiz, prodWiz);
//        addToCart();
        productDetail_screen.click_AddToCartBtn();
        if (billingAddress_screen.getHelper().hasSizeErrorMgs()) { // We need select size first

//            chooseSize();
            productDetail_screen.click_ChooseSizeBtn();
            billingAddress_screen.getHelper().randClick(productDetail_screen.sizes);
            Thread.sleep(2000);
            productDetail_screen.click_AddToCartBtn();
        }
        topBar_screen.click_MyCartBtn();
        cart_screen.click_CheckoutBtn();

        // Login as default account
//        loginAs(USERNAME, PASSWORD);
        login_screen.input_Email(USERNAME);
        login_screen.click_UserRd();
        login_screen.input_Password(PASSWORD);
        login_screen.click_ContinueBtn();
        login_screen.rocket_app_checkoutweb();
        billingAddress_screen.getHelper().switchToWebView();
        shippingInformation_screen.click_ContinueBtn();
        Thread.sleep(3000);
        Thread.sleep(1000);

        // Check the Bank transfer is available or not for this CheckOutTest
        if (billingAddress_screen.getHelper().isElementPresent(payMethod_screen.labelBankTransfer)) {

            // Choose pay method -> bank transfer
            payMethod_screen.click_BankTransferRadio();
            Thread.sleep(1000);

            // Select bank name
            billingAddress_screen.getHelper().selector(payMethod_screen.bankNames, bankIndex);

            // Fill information and Submit
            payMethod_screen.input_SenderName(senderName);
            Thread.sleep(2000);
            payMethod_screen.click_ContinueBtn();
            Thread.sleep(3000);
            orderSummary_screen.click_EditBillingAddress();

            // Edit billing address
//            createBillingAddress(venture, name, address, phoneNumber);
            // New billing address
            billingAddress_screen.input_Name(name);
            billingAddress_screen.input_Address(address);
            if (venture.equals("Thailand") || venture.equals("Philippines")
                    || venture.equals("Malaysia") || venture.equals("Indonesia")) {

                billingAddress_screen.getHelper().selectorRandom(billingAddress_screen.region()); // select random Region
                Thread.sleep(2000); // wait for loading data from dropdown list
                billingAddress_screen.getHelper().selectorRandom(billingAddress_screen.city()); // select random City
                Thread.sleep(2000); // wait for loading data from dropdown list
                billingAddress_screen.getHelper().selectorRandom(billingAddress_screen.ward()); // select random Postcode
                billingAddress_screen.input_PhoneNumber(phoneNumber);

            }
            if (venture.equals("Singapore")) {
                billingAddress_screen.input_PostCode("759674");
                billingAddress_screen.input_PhoneNumber(phoneNumber);

            }
            if (venture.equals("Vietnam")) {
                billingAddress_screen.getHelper().selectorRandom(billingAddress_screen.region()); // select random Region
                Thread.sleep(2000); // wait for loading data from dropdown list
                billingAddress_screen.getHelper().selectorRandom(billingAddress_screen.city()); // select random City
                billingAddress_screen.input_PhoneNumber(phoneNumber);
            }

            // Submit
            billingAddress_screen.click_ContinueBtn();
//            bankTransfer(bankIndex, senderName);
            // Check the Bank transfer is available or not for this CheckOutTest
            if (billingAddress_screen.getHelper().isElementPresent(payMethod_screen.labelBankTransfer)) {

                // Choose pay method -> bank transfer
                payMethod_screen.click_BankTransferRadio();
                Thread.sleep(1000);

                // Select bank name
                billingAddress_screen.getHelper().selector(payMethod_screen.bankNames , bankIndex);

                // Fill information and Submit
                payMethod_screen.input_SenderName(senderName);
                Thread.sleep(2000);
                payMethod_screen.click_ContinueBtn();
                Thread.sleep(2000);
                orderSummary_screen.click_PlaceOrderBtn(); //Place Order

                // Verify success page
                Assert.assertTrue(billingAddress_screen.getHelper().isPageContains("Pelanggan yang terhormat"));
                Assert.assertTrue(billingAddress_screen.getHelper().isPageContains("Nomor pesanan Anda adalah"));
                Assert.assertTrue(billingAddress_screen.getHelper().isPageContains("telah sukses diterima"));
//            Assert.assertTrue(billingAddress_screen.getHelper().isPageContains("Silakan melakukan pembayaran sebesar"));

            }
        }
    }

}
