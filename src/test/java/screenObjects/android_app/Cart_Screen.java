package screenObjects.android_app;

import org.openqa.selenium.WebElement;
import java.util.List;
import static util.Helper.findElement;
import static util.Helper.findElements;
import static util.Helper.split;

/**
 * Created by anhpham on 17/11/2014.
 */
public class Cart_Screen {

    private static String proceedToCheckout = "resourceID::checkout_button";
    private static String delete = "resourceID::delete_button";
    private static String removeItem = "resourceID::button1";
    private static String productName = "resourceID::item_name";
    private static String quantity = "resourceID::changequantity_button";
    private static String sizes = "resourceID::item_text";

    public static void click_CheckoutBtn() {

        findElement(split(proceedToCheckout)[0], split(proceedToCheckout)[1]).click();
    }

    public static List<WebElement> deleteButtons() {

        return findElements(split(delete)[0], split(delete)[1]);
    }

    public static void click_DeleteConfirm() {

        findElement(split(removeItem)[0], split(removeItem)[1]).click();
    }

    public static WebElement productName() {

        return findElement(split(productName)[0], split(productName)[1]);
    }

    public static void click_deleteProductBtn() {

        findElement(split(delete)[0], split(delete)[1]).click();
    }

    public static void click_quantityBtn() {

        findElement(split(quantity)[0], split(quantity)[1]).click();
    }

    public static WebElement quantity() {

        return findElement(split(quantity)[0], split(quantity)[1]);
    }

    public static List<WebElement> sizes() {

        return findElements(split(sizes)[0], split(sizes)[1]);
    }


}
