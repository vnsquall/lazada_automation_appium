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

        findElement(placeOrder).click();
    }

    public static void click_EditBillingAddress() {

        findElement(editBillingAddress).click();
    }

    public static void click_EditShippingAddress() {

        findElement(editShippingAddress).click();
    }

    public static void click_ChangePayment() {

        findElement(changePayment).click();
    }

    public static void click_addCouponBtn() {

        findElement(addCoupon).click();
    }

    public static void input_Coupon(String _coupon) {

        findElement(coupon).sendKeys(_coupon);
    }

    public static void click_SubmitCoupon() {

        findElement(submitCoupon).click();
    }
}
