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
    private static String changeQuantity = "resourceID::hangequantity_button";

    public static void click_ProceedToCheckoutBtn() {

        findByUISelector(split(proceedToCheckout)[0], split(proceedToCheckout)[1]).click();
    }

    public static List<WebElement> delete () {

        return findsByUISelector(split(delete)[0], split(delete)[1]);
    }

    public static List<WebElement> changeQuantity () {

        return findsByUISelector(split(changeQuantity)[0], split(changeQuantity)[1]);
    }


}
