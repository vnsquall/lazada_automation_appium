package screenObjects.android_app;


import org.openqa.selenium.WebElement;

import static util.Helper.findElement;
import static util.Helper.split;

/**
 * Created by anhpham on 17/11/2014.
 */
public class MyAccount_Screen {

    private static String continueButton = "xpath:://*[@class='orange-button']";



    public static void click_ContinueBtn() {

        findElement(split(continueButton)[0], split(continueButton)[1]).click();
    }

}
