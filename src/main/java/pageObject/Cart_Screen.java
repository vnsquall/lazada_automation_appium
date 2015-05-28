package pageObject;

import org.openqa.selenium.WebElement;

import java.util.List;

import static util.Helper.findElement;
import static util.Helper.findElements;


public class Cart_Screen {

    public static String proceedToCheckout = "resourceID::checkout_button";
    public static String delete = "resourceID::delete_button";
    public static String removeItem = "resourceID::button1";
    public static String productName = "resourceID::item_name";
    public static String quantity = "resourceID::changequantity_button";
    public static String sizes = "resourceID::item_text";

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
