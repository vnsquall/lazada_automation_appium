package screenObjects.android_app;


import static util.Helper.findElement;
import static util.Helper.split;

/**
 * Created by anhpham on 17/11/2014.
 */
public class Thankyou_Screen {

    public static String share = "resourceID::btn_checkout_share";
    public static String sharerApps = "xpath:://*[@resource-id='android:id/text1']";


    public static void click_ShareBtn() {

        findElement(split(share)[0], split(share)[1]).click();
    }


}
