package screenObjects.android_app;

import org.openqa.selenium.WebElement;

import java.util.List;

import static util.Helper.*;

/**
 * Created by anhpham on 17/11/2014.
 */
public class ShippingAddress_Screen {

    private static String continueButton = "xpath:://*[@class='orange-button']";
    private static String newAddress = "resourceID::";
    private static String edit = "resourceID::";

    public static void click_ContinueBtn() {

        findByUISelector(split(continueButton)[0], split(continueButton)[1]).click();
    }

    public static List<WebElement> newAddress () {

        return findsByUISelector(split(newAddress)[0], split(newAddress)[1]);
    }

    public static List<WebElement> edit () {

        return findsByUISelector(split(edit)[0], split(edit)[1]);
    }


}
