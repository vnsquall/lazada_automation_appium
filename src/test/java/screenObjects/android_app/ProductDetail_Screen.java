package screenObjects.android_app;

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

        findElement(split(addToCart)[0], split(addToCart)[1]).click();
    }

    public static void click_AddToWishListBtn() {

        findElement(split(addToWishList)[0], split(addToWishList)[1]).click();
    }

    public static void click_ShareBtn() {

        findElement(split(share)[0], split(share)[1]).click();
    }

    public static void click_GoToCartBtn() {

        findElement(split(goToCart)[0], split(goToCart)[1]).click();
    }

    public static void click_ChooseSizeBtn() {

        findElement(split(chooseSize)[0], split(chooseSize)[1]).click();
    }


}
