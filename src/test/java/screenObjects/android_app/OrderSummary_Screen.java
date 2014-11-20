package screenObjects.android_app;

import static util.Helper.findElement;
import static util.Helper.split;

/**
 * Created by anhpham on 17/11/2014.
 */
public class OrderSummary_Screen {

    private static String placeOrder = "xpath:://*[@class='orange-button']";
    private static String editBillingAddress = "xpath:://*[@id='change-shipping'][contains(@href, 'billing')]";
    private static String editShippingAddress = "xpath:://*[@id='change-shipping'][contains(@href, 'shipping')]";
    public static String changePayment = "xpath:://*[@id='change-payment']";
    public static String addCoupon = "xpath:://*[@id='addCoupon']";
    public static String coupon = "xpath:://*[@id='coupon']";
    public static String submitCoupon = "xpath:://*[@id='couponSend']";

    public static void click_PlaceOrderBtn() {

        findElement(split(placeOrder)[0], split(placeOrder)[1]).click();
    }

    public static void click_EditBillingAddress() {

        findElement(split(editBillingAddress)[0], split(editBillingAddress)[1]).click();
    }

    public static void click_EditShippingAddress() {

        findElement(split(editShippingAddress)[0], split(editShippingAddress)[1]).click();
    }

    public static void click_ChangePayment() {

        findElement(split(changePayment)[0], split(changePayment)[1]).click();
    }

    public static void click_addCouponBtn() {

        findElement(split(addCoupon)[0], split(addCoupon)[1]).click();
    }

    public static void input_Coupon(String _coupon) {

        findElement(split(coupon)[0], split(coupon)[1]).sendKeys(_coupon);
    }

    public static void click_SubmitCoupon() {

        findElement(split(submitCoupon)[0], split(submitCoupon)[1]).click();
    }
}
