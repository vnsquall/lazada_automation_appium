package pageObject;

import static util.Helper.findElement;
import static util.Helper.findElements;

public class OrderSummary_Screen {

    public static String placeOrder = "xpath:://*[@class='orange-button']";
    public static String editBillingAddress = "xpath:://*[@id='change-shipping'][contains(@href, 'billing')]";
    public static String editShippingAddress = "xpath:://*[@id='change-shipping'][contains(@href, 'shipping')]";
    public static String changePayment = "xpath:://*[@id='change-payment']";
    public static String addCoupon = "xpath:://*[@id='addCoupon']";
    public static String coupon = "xpath:://*[@id='coupon']";
    public static String submitCoupon = "xpath:://*[@id='couponSend']";
    public static String productName = "xpath:://*[@class='product-name']";

    public static void loaded() {

        findElements(productName);
        findElements(placeOrder);
        findElements(editBillingAddress);
        findElements(editShippingAddress);
        findElements(changePayment);
        findElements(addCoupon);
    }

    public static void click_PlaceOrderBtn() {

        loaded();
        findElement(placeOrder).click();
    }

    public static void click_EditBillingAddress() {

        loaded();
        findElement(editBillingAddress).click();
    }

    public static void click_EditShippingAddress() {

        loaded();
        findElement(editShippingAddress).click();
    }

    public static void click_ChangePayment() {

        loaded();
        findElement(changePayment).click();
    }

    public static void click_addCouponBtn() {

        loaded();
        findElement(addCoupon).click();
    }

    public static void input_Coupon(String _coupon) {

        loaded();
        findElement(coupon).sendKeys(_coupon);
    }

    public static void click_SubmitCoupon() {

        loaded();
        findElement(submitCoupon).click();
    }
}
