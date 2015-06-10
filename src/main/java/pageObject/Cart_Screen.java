package pageObject;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class Cart_Screen extends CommonPageObject{

    public Cart_Screen(AndroidDriver driver) {
        super(driver);
    }

    public static String proceedToCheckout = "resourceID::checkout_button";
    public static String delete = "resourceID::delete_button";
    public static String removeItem = "resourceID::button1";
    public static String productName = "resourceID::item_name";
    public static String quantity = "resourceID::changequantity_button";
    public static String sizes = "resourceID::item_text";

    public void click_CheckoutBtn() {

        getHelper().findElement(proceedToCheckout).click();
    }

    public List<WebElement> deleteButtons() {

        return getHelper().findElements(delete);
    }

    public void click_DeleteConfirm() {

        getHelper().findElement(removeItem).click();
    }

    public WebElement productName() {

        return getHelper().findElement(productName);
    }

    public void click_deleteProductBtn() {

        getHelper().findElement(delete).click();
    }

    public void click_quantityBtn() {

        getHelper().findElement(quantity).click();
    }

    public WebElement quantity() {

        return getHelper().findElement(quantity);
    }

    public List<WebElement> sizes() {

        return getHelper().findElements(sizes);
    }


}
