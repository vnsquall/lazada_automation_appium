package pageObject;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;

public class ProductDetail_Screen extends CommonPageObject{

    public ProductDetail_Screen(AndroidDriver driver) {
        super(driver);
    }

    public static String addToCart = "resourceID::btn_add_to_cart";
    public static String addToWishList = "resourceID::wishlist_item_add";
    public static String share = "resourceID::btn_share";
    public static String chooseSize = "resourceID::product_variant_button";
    public static String sizes = "resourceID::item_text";
    public static String OKBtn = "resourceID::button1"; // This button is showed after click on Add to wish list
    public static String productName = "resourceID::product_name";
    public static String chooseSizeMsg = "resourceID::product_variant_choose_error";
    public static String buyNow = "resourceID::btn_buy_now";
    public static String leftArrow = "resourceID::product_previous";
    public static String rightArrow = "resourceID::product_next";

    public void click_AddToCartBtn() {

        getHelper().findElement(addToCart).click();
    }

    public void click_AddToWishListBtn() {

        getHelper().findElement(addToWishList).click();
    }

    public void click_ShareBtn() {

        getHelper().findElement(share).click();
    }

    public void click_ChooseSizeBtn() {

        getHelper().findElement(chooseSize).click();
    }

    public WebElement chooseSizeBtn() {

        return getHelper().findElement(chooseSize);
    }

    public void click_OKBtn() {

        getHelper().findElement(OKBtn).click();
    }

    /**
     * Choose application to share product: message, Email, Bluetooth...
     */
    public void click_AppSharer(String appName) {

        getHelper().find_TextView_Android(appName).click();
    }

    public WebElement productName() {

        return getHelper().findElement(productName);
    }

    public void click_BuyNowBtn() {

        getHelper().findElement(buyNow).click();
    }

    public void click_LeftArrow() {

        getHelper().findElement(leftArrow).click();
    }

    public void click_RightArrow() {

        getHelper().findElement(rightArrow).click();
    }


}
