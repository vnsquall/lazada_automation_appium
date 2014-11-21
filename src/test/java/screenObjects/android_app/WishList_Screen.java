package screenObjects.android_app;

import org.openqa.selenium.WebElement;

import java.util.List;

import static util.Helper.*;

/**
 * Created by anhpham on 17/11/2014.
 */
public class WishList_Screen {

    private static String proceedToCheckout = "resourceID::checkout_button";

    public static void click_CheckoutBtn() {

        findElement(split(proceedToCheckout)[0], split(proceedToCheckout)[1]).click();
    }


}
