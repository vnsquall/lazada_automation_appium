package screenObjects.android_app;

import org.openqa.selenium.WebElement;

import java.util.List;

import static util.Helper.*;
/**
 * Created by anhpham on 17/11/2014.
 */
public class ProductDetail_Screen {

    public static String addToCart = "resourceID::shop";
    public static String addToWishList = "resourceID::btn_wishlist";
    public static String share = "resourceID::btn_share";
    public static String goToCart = "resourceID::button1";
    public static String chooseSize = "resourceID::product_variant_button";
    public static String sizes = "resourceID::item_text";

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

    public static void click_ChooseSizeBtn() {

        findByUISelector(split(chooseSize)[0], split(chooseSize)[1]).click();
    }


}
