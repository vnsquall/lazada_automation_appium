package screenObjects.android_app;


import org.openqa.selenium.WebElement;

import static util.Helper.findElement;
import static util.Helper.find_TextView_Android;
import static util.Helper.split;

/**
 * Created by anhpham on 17/11/2014.
 */
public class UserData_Screen {

    public static String cancel = "resourceID::button_cancel";


    public static WebElement verifyText(String text) {

        return find_TextView_Android(text);
    }

    public static void click_CancelBtn() {

        findElement(cancel).click();
    }



}
