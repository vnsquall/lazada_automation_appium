package screenObjects.android_app;


import static util.Helper.findElement;
import static util.Helper.find_TextView_Android;
import static util.Helper.split;

/**
 * Created by anhpham on 17/11/2014.
 */
public class MyAccount_Screen {


    public static void click_UserData(String userData) {

        find_TextView_Android(userData).click();
    }

}
