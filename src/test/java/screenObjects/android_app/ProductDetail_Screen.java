package screenObjects.android_app;

import org.openqa.selenium.WebElement;

import static util.Helper.*;
/**
 * Created by anhpham on 17/11/2014.
 */
public class ProductDetail_Screen {

    private static String addToCart = "resourceID::shop";
    private static String addToWishList = "resourceID::btn_wishlist";
    private static String share = "resourceID::btn_share";
    private static String goToCart = "resourceID::button1";

    public static void click_AddToCartBtn() {

        findByUISelector(split(addToCart)[0], split(addToCart)[1]).click();
    }

    public static void click_AddToWishListBtn() {

        findByUISelector(split(addToWishList)[0], split(addToWishList)[1]).click();
    }

    public static void click_ShareBtn() {

        findByUISelector(split(share)[0], split(share)[1]).click();
    }

    public static void click_GoToCartBtn() {

        findByUISelector(split(goToCart)[0], split(goToCart)[1]).click();
    }
}
