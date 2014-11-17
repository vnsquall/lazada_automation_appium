package screenObjects.android_app;

import org.openqa.selenium.WebElement;

import static util.Helper.findByUISelector;
import static util.Helper.split;

/**
 * Created by anhpham on 17/11/2014.
 */
public class TopBar_Screen {

    private static String home = "resourceID::abs__home";
    private static String search = "resourceID::abs__imageButton";
    private static String cart = "resourceID::cart_count";

    public static WebElement home () {

        return findByUISelector(split(home)[0], split(home)[1]);
    }

    public static WebElement search () {

        return findByUISelector(split(search)[0], split(search)[1]);
    }

    public static WebElement cart () {

        return findByUISelector(split(cart)[0], split(cart)[1]);
    }
}
