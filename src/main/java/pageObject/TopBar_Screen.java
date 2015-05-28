package pageObject;

import static util.Helper.findElement;


public class TopBar_Screen {

    public static String menu = "resourceID::abs__home";
    public static String search = "resourceID::abs__imageButton";
    public static String cart = "resourceID::cart_count";

    public static void click_MenuBtn() {

        findElement(menu).click();
    }

    public static void click_MyCartBtn() {

        findElement(cart).click();
    }

    public static void click_SearchBtn() {

        findElement(search).click();
    }


}
