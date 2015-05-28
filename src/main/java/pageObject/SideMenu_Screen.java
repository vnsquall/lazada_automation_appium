package pageObject;


import static util.Helper.findElement;
import static util.Helper.text;

public class SideMenu_Screen {

    public static String signOutConfirm = "resourceID::button2";
    public static String categories = "resourceID::category_name";
    public static String subCategories = "resourceID::text";

    public static void click_Menu(String menuName) throws InterruptedException {

        Thread.sleep(3000);
        text(menuName).click();
    }

    public static void click_SignOutConfirmBtn() {

        findElement(signOutConfirm).click();
    }

}
