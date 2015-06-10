package pageObject;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;

public class WishList_Screen extends CommonPageObject{

    public WishList_Screen(AndroidDriver driver) {
        super(driver);
    }

    public static String addAllToCart = "resourceID::wishlist_bt_add_all";
    public static String continueBrowsing = "resourceID::wishlist_no_items_bt_continue";
    public static String delete = "resourceID::wishlist_item_bt_delete";
    public static String noItemMessage = "resourceID::wishlist_no_items_text";
    public static String addToCart = "resourceID::wishlist_item_bt_add";
    public static String OKBtn = "resourceID::button1";

    public void click_AddAllToCart() {

        getHelper().findElement(addAllToCart).click();
    }

    public void click_ContinueBrowsingBtn() {

        getHelper().findElement(continueBrowsing).click();
    }

    public WebElement deleteBtn() {

        return getHelper().findElement(delete);
    }

    public void click_DeleteBtn() {

        getHelper().findElement(delete).click();
    }

    public WebElement noItemMessage() {

        return getHelper().findElement(noItemMessage);
    }

    public void click_AddToCartBtn() {

        getHelper().findElement(addToCart).click();
    }

    public void click_OKBtn() {

        getHelper().findElement(OKBtn).click();
    }


}
