package screenObjects.android_app;

import static util.Helper.findByUISelector;
import static util.Helper.split;

/**
 * Created by anhpham on 17/11/2014.
 */
public class OrderSummary_Screen {

    private static String placeOrder = "xpath:://*[@class='orange-button']";
    private static String editBillingAddress = "xpath:://*[@id='change-shipping'][contains(@href, 'billing')]";
    private static String editShippingAddress = "xpath:://*[@id='change-shipping'][contains(@href, 'shipping')]";
    public static String changePayment = "xpath:://*[@id='change-payment']";

    public static void click_PlaceOrderBtn() {

        findByUISelector(split(placeOrder)[0], split(placeOrder)[1]).click();
    }

    public static void click_EditBillingAddress() {

        findByUISelector(split(editBillingAddress)[0], split(editBillingAddress)[1]).click();
    }

    public static void click_EditShippingAddress() {

        findByUISelector(split(editShippingAddress)[0], split(editShippingAddress)[1]).click();
    }

    public static void click_ChangePayment() {

        findByUISelector(split(changePayment)[0], split(changePayment)[1]).click();
    }
}
