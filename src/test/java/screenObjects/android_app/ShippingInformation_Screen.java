package screenObjects.android_app;



import static util.Helper.*;

/**
 * Created by anhpham on 17/11/2014.
 */
public class ShippingInformation_Screen {

    public static String continueButton = "xpath:://*[@class='orange-button']";
    public static String newAddress = "xpath:://*[@id='load-different-billing-mobile']";
    public static String useDifferentBillingAddress = "xpath:://*[@for='ThreeStepBillingAddressForm_isSameShipping']";
    public static String editAddress = "xpath:://*[@class='change-billing']";

    public static void click_ContinueBtn() {

        findElement(split(continueButton)[0], split(continueButton)[1]).click();
    }

    public static void click_NewAddress () {

        findElement(split(newAddress)[0], split(newAddress)[1]).click();
    }

    /**
     * Click on Use different billing address checkbox
     */
    public static void click_UseDifferent () {

        findElement(split(useDifferentBillingAddress)[0], split(useDifferentBillingAddress)[1]).click();
    }



}
