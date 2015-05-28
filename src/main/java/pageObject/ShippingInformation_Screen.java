package pageObject;



import static util.Helper.findElement;


public class ShippingInformation_Screen {

    public static String continueButton = "xpath:://*[@class='orange-button']";
    public static String newAddress = "xpath:://*[@id='load-different-billing-mobile']";
    public static String useDifferentBillingAddress = "xpath:://*[@for='ThreeStepBillingAddressForm_isSameShipping']";
    public static String editAddress = "xpath:://*[@class='change-billing']";

    public static void click_ContinueBtn() {

        findElement(continueButton).click();
    }

    public static void click_NewAddress () {

        findElement(newAddress).click();
    }

    /**
     * Click on Use different billing address checkbox
     */
    public static void click_UseDifferent () {

        findElement(useDifferentBillingAddress).click();
    }



}
