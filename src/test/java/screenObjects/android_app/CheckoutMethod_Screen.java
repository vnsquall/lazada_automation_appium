package screenObjects.android_app;


import org.openqa.selenium.WebElement;

import static util.Helper.findElement;
import static util.Helper.split;

/**
 * Created by anhpham on 17/11/2014.
 */
public class CheckoutMethod_Screen {

    private static String guest = "resourceID::guestradio";
    private static String user = "resourceID::accountradio";
    private static String continueBtn = "resourceID::accountradio";



    public static void click_GuestRd() {

        findElement(split(guest)[0], split(guest)[1]).click();
    }

    public static void click_UserRd() {

        findElement(split(user)[0], split(user)[1]).click();
    }

    public static void click_ContinueBtn() {

        findElement(split(continueBtn)[0], split(continueBtn)[1]).click();
    }


}
