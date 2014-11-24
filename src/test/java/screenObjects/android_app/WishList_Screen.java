package screenObjects.android_app;

import org.openqa.selenium.WebElement;

import static util.Helper.*;

/**
 * Created by anhpham on 17/11/2014.
 */
public class WishList_Screen {

    private static String addAllToCart = "resourceID::checkout_button";
    private static String continueBrowsing = "resourceID::wishlist_no_items_bt_continue";
    private static String delete = "resourceID::wishlist_item_bt_delete";
    private static String noItemMessage = "resourceID::wishlist_no_items_text";
    private static String addToCart = "resourceID::wishlist_item_bt_add";
    private static String OKBtn = "resourceID::button1";

    public static void click_AddAllToCart() {

        findElement(split(addAllToCart)[0], split(addAllToCart)[1]).click();
    }

    public static void click_ContinueBrowsingBtn() {

        findElement(split(continueBrowsing)[0], split(continueBrowsing)[1]).click();
    }

    public static WebElement deleteBtn() {

        return findElement(split(delete)[0], split(delete)[1]);
    }

    public static void click_DeleteBtn() {

        findElement(split(delete)[0], split(delete)[1]).click();
    }

    public static WebElement noItemMessage() {

        return findElement(split(noItemMessage)[0], split(noItemMessage)[1]);
    }

    public static void click_AddToCartBtn() {

        findElement(split(addToCart)[0], split(addToCart)[1]).click();
    }

    public static void click_OKBtn() {

        findElement(split(OKBtn)[0], split(OKBtn)[1]).click();
    }


}
