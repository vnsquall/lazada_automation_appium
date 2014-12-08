package screenObjects.android_app;

import org.openqa.selenium.WebElement;

import static util.Helper.*;
/**
 * Created by anhpham on 17/11/2014.
 */
public class ProductDetail_Screen {

    public static String addToCart = "resourceID::btn_add_to_cart";
    public static String addToWishList = "resourceID::wishlist_item_add";
    public static String share = "resourceID::btn_share";
    public static String goToCart = "resourceID::button1";
    public static String chooseSize = "resourceID::product_variant_button";
    public static String sizes = "resourceID::item_text";
    public static String OKBtn = "resourceID::button1"; // This button be showed after click on Add to wish list
    public static String productName = "resourceID::product_name";
    public static String chooseSizeMsg = "resourceID::product_variant_choose_error";
    public static String buyNow = "resourceID::btn_buy_now";

    public static void click_AddToCartBtn() {

        findElement(addToCart).click();
    }

    public static void click_AddToWishListBtn() {

        findElement(addToWishList).click();
    }

    public static void click_ShareBtn() {

        findElement(share).click();
    }

    public static void click_GoToCartBtn() {

        findElement(goToCart).click();
    }

    public static void click_ChooseSizeBtn() {

        findElement(chooseSize).click();
    }

    public static WebElement chooseSizeBtn() {

        return findElement(chooseSize);
    }

    public static void click_OKBtn() {

        findElement(OKBtn).click();
    }

    /**
     * Choose application to share product: message, Email, Bluetooth...
     */
    public static void click_AppSharer(String appName) {

        find_TextView_Android(appName).click();
    }

    public static WebElement productName() {

        return findElement(productName);
    }

    public static void click_BuyNowBtn() {

        findElement(buyNow).click();
    }


}
