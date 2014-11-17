package screenObjects.android_app;

import static util.Helper.findByUISelector;
import static util.Helper.split;

/**
 * Created by anhpham on 17/11/2014.
 */
public class OrderSummary_Screen {

    private static String placeOrder = "xpath:://*[@class='orange-button']";


    public static void click_PlaceOrderBtn() {

        findByUISelector(split(placeOrder)[0], split(placeOrder)[1]).click();
    }

}
