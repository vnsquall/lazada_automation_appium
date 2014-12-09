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

        findElement(proceedToCheckout).click();
    }

    public static List<WebElement> deleteButtons() {

        return findElements(delete);
    }

    public static void click_DeleteConfirm() {

        findElement(removeItem).click();
    }

    public static WebElement productName() {

        return findElement(productName);
    }

    public static void click_deleteProductBtn() {

        findElement(delete).click();
    }

    public static void click_quantityBtn() {

        findElement(quantity).click();
    }

    public static WebElement quantity() {

        return findElement(quantity);
    }

    public static List<WebElement> sizes() {

        return findElements(sizes);
    }


}
