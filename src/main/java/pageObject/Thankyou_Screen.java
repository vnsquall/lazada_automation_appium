package pageObject;


import io.appium.java_client.android.AndroidDriver;

public class Thankyou_Screen extends CommonPageObject{

    public Thankyou_Screen(AndroidDriver driver) {
        super(driver);
    }

    public static String share = "resourceID::btn_checkout_share";
    public static String sharerApps = "xpath:://*[@resource-id='android:id/text1']";


    public void click_ShareBtn() {

        getHelper().findElement(share).click();
    }


}
