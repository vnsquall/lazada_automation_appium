package screenObjects.android_app;

import org.openqa.selenium.WebElement;

import java.util.List;

import static util.Helper.findByUISelector;
import static util.Helper.findsByUISelector;
import static util.Helper.split;

/**
 * Created by anhpham on 17/11/2014.
 */
public class Cart_Screen {

    private static String proceedToCheckout = "resourceID::checkout_button";
    private static String delete = "resourceID::delete_button";
    private static String removeItem = "resourceID::button1";

    public static void click_ProceedToCheckoutBtn() {

        findByUISelector(split(proceedToCheckout)[0], split(proceedToCheckout)[1]).click();
    }

    public static List<WebElement> deleteButtons() {

        return findsByUISelector(split(delete)[0], split(delete)[1]);
    }

    public static void click_RemoveItem() {

        findByUISelector(split(removeItem)[0], split(removeItem)[1]).click();
    }


}
