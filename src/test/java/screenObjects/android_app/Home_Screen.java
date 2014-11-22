package screenObjects.android_app;

import static util.Helper.*;

/**
 * Created by anhpham on 17/11/2014.
 */
public class Home_Screen {

    private static String proceedToCheckout = "resourceID::checkout_button";

    public static void click_CheckoutBtn() {

        findElement(split(proceedToCheckout)[0], split(proceedToCheckout)[1]).click();
    }


}
