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

        findElement(home).click();
    }

    public static void click_MyCartBtn() {

        findElement(cart).click();
    }

    /**
     * get Number of items in my cart
     */
    public static int getNumberItem() {

        String itemNumber = findElement(cart).getText();
        if (itemNumber.equals("")) {
            return 0;
        }
        return Integer.parseInt(itemNumber);
    }
}
