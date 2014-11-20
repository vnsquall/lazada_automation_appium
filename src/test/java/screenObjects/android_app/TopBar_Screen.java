package screenObjects.android_app;

import static util.Helper.findElement;
import static util.Helper.split;

/**
 * Created by anhpham on 17/11/2014.
 */
public class TopBar_Screen {

    private static String home = "resourceID::abs__home";
    private static String search = "resourceID::abs__imageButton";
    private static String cart = "resourceID::cart_count";

    public static void click_HomeBtn() {

        findElement(split(home)[0], split(home)[1]).click();
    }

    public static void search () {

        findElement(split(search)[0], split(search)[1]).click();
    }

    public static void click_MyCartBtn() {

        findElement(split(cart)[0], split(cart)[1]).click();
    }
}
