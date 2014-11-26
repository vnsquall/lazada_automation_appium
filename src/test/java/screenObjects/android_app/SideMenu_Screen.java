package screenObjects.android_app;



import static util.Helper.*;

/**
 * Created by anhpham on 17/11/2014.
 */
public class SideMenu_Screen {

    private static String signOutConfirm = "resourceID::button2";

    public static void click_Menu(String menuName) {

        find_TextView_Android(menuName).click();
    }

    public static void click_SignOutConfirmBtn() {

        findElement(split(signOutConfirm)[0], split(signOutConfirm)[1]).click();
    }


}
