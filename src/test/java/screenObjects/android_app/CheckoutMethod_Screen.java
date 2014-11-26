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
    private static String continueBtn = "resourceID::continue_checkout";
    private static String email = "resourceID::emailid";

    public static void click_GuestRd() {

        findElement(split(guest)[0], split(guest)[1]).click();
    }

    public static void click_UserRd() {

        findElement(split(user)[0], split(user)[1]).click();
    }

    public static void click_ContinueBtn() {

        findElement(split(continueBtn)[0], split(continueBtn)[1]).click();
    }

    public static void input_Email(String _email) {

        findElement(split(email)[0], split(email)[1]).sendKeys(_email);
    }


}
