package screenObjects.android_app;

import org.openqa.selenium.WebElement;

import static util.Helper.*;

/**
 * Created by anhpham on 17/11/2014.
 */
public class WishList_Screen {

    public static String addAllToCart = "resourceID::checkout_button";
    public static String continueBrowsing = "resourceID::wishlist_no_items_bt_continue";
    public static String delete = "resourceID::wishlist_item_bt_delete";
    public static String noItemMessage = "resourceID::wishlist_no_items_text";
    public static String addToCart = "resourceID::wishlist_item_bt_add";
    public static String OKBtn = "resourceID::button1";

    public static void click_AddAllToCart() {

        findElement(addAllToCart).click();
    }

    public static void click_ContinueBrowsingBtn() {

        findElement(continueBrowsing).click();
    }

    public static WebElement deleteBtn() {

        return findElement(delete);
    }

    public static void click_DeleteBtn() {

        findElement(delete).click();
    }

    public static WebElement noItemMessage() {

        return findElement(noItemMessage);
    }

    public static void click_AddToCartBtn() {

        findElement(addToCart).click();
    }

    public static void click_OKBtn() {

        findElement(OKBtn).click();
    }


}
