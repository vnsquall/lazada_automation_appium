package screenObjects.android_app;

import org.openqa.selenium.WebElement;

import java.util.List;

import static util.Helper.*;

/**
 * Created by anhpham on 17/11/2014.
 */
public class BillingAddress_Screen {

    private static String continueButton = "xpath:://*[@class='orange-button']";


    public static void click_ContinueBtn() {

        findByUISelector(split(continueButton)[0], split(continueButton)[1]).click();
    }




}
