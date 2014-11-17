package screenObjects.android_app;

import static util.Helper.findByUISelector;
import static util.Helper.split;

/**
 * Created by anhpham on 17/11/2014.
 */
public class OderSummary_Screen {

    public static String radioCODDisabled = "xpath:://*[@class='payment-method-option radio'and@value='CashOnDelivery'and@disabled='disabled']";
    public static String labelCOD = "xpath:://label[@for='cashondelivery']";
    public static String continueButton = "xpath:://button[@class='orange-button']";


    public static void click_CashOnDeliveryRadio() {

        findByUISelector(split(labelCOD)[0], split(labelCOD)[1]).click();
    }

    public static void click_ContinueBtn() {

        findByUISelector(split(continueButton)[0], split(continueButton)[1]).click();
    }

}
