package pageObject;


import static util.Helper.findElement;


public class Thankyou_Screen {

    public static String share = "resourceID::btn_checkout_share";
    public static String sharerApps = "xpath:://*[@resource-id='android:id/text1']";


    public static void click_ShareBtn() {

        findElement(share).click();
    }


}
