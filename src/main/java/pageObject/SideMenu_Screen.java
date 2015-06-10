package pageObject;

import io.appium.java_client.android.AndroidDriver;

public class SideMenu_Screen extends CommonPageObject{

    public SideMenu_Screen(AndroidDriver driver) {
        super(driver);
    }

    public static String signOutConfirm = "resourceID::button2";
    public static String categories = "resourceID::category_name";
    public static String subCategories = "resourceID::text";

    public void click_Menu(String menuName) throws InterruptedException {

        Thread.sleep(3000);
        getHelper().text(menuName).click();
    }

    public void click_SignOutConfirmBtn() {

        getHelper().findElement(signOutConfirm).click();
    }

}
