package pageObject;

import io.appium.java_client.android.AndroidDriver;

public class TopBar_Screen extends CommonPageObject{

    public TopBar_Screen(AndroidDriver driver) {
        super(driver);
    }

    public static String menu = "resourceID::abs__home";
    public static String search = "resourceID::abs__imageButton";
    public static String cart = "resourceID::cart_count";

    public void click_MenuBtn() {

        getHelper().findElement(menu).click();
    }

    public void click_MyCartBtn() {

        getHelper().findElement(cart).click();
    }

    public void click_SearchBtn() {

        getHelper().findElement(search).click();
    }


}
