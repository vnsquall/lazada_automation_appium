package pageObject;

import io.appium.java_client.android.AndroidDriver;

public class OrderSummary_Screen extends CommonPageObject{

    public OrderSummary_Screen(AndroidDriver driver) {
        super(driver);
    }

    public static String placeOrder = "xpath:://*[@class='orange-button']";
    public static String editBillingAddress = "xpath:://*[@id='change-shipping'][contains(@href, 'billing')]";
    public static String editShippingAddress = "xpath:://*[@id='change-shipping'][contains(@href, 'shipping')]";
    public static String changePayment = "xpath:://*[@id='change-payment']";
    public static String addCoupon = "xpath:://*[@id='addCoupon']";
    public static String coupon = "xpath:://*[@id='coupon']";
    public static String submitCoupon = "xpath:://*[@id='couponSend']";
    public static String productName = "xpath:://*[@class='product-name']";

    public void loaded() {

        getHelper().findElements(productName);
        getHelper().findElements(placeOrder);
        getHelper().findElements(editBillingAddress);
        getHelper().findElements(editShippingAddress);
        getHelper().findElements(changePayment);
        getHelper().findElements(addCoupon);
    }

    public void click_PlaceOrderBtn() {

        loaded();
        getHelper().findElement(placeOrder).click();
    }

    public void click_EditBillingAddress() {

        loaded();
        getHelper().findElement(editBillingAddress).click();
    }

    public void click_EditShippingAddress() {

        loaded();
        getHelper().findElement(editShippingAddress).click();
    }

    public void click_ChangePayment() {

        loaded();
        getHelper().findElement(changePayment).click();
    }

    public void click_addCouponBtn() {

        loaded();
        getHelper().findElement(addCoupon).click();
    }

    public void input_Coupon(String _coupon) {

        loaded();
        getHelper().findElement(coupon).sendKeys(_coupon);
    }

    public void click_SubmitCoupon() {

        loaded();
        getHelper().findElement(submitCoupon).click();
    }
}
