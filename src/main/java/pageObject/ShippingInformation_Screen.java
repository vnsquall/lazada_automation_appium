package pageObject;

import io.appium.java_client.android.AndroidDriver;

public class ShippingInformation_Screen extends CommonPageObject{
    
    public ShippingInformation_Screen(AndroidDriver driver) {
        super(driver);
    }

    public static String continueButton = "xpath:://*[@class='orange-button']";
    public static String newAddress = "xpath:://*[@id='load-different-billing-mobile']";
    public static String useDifferentBillingAddress = "xpath:://*[@for='ThreeStepBillingAddressForm_isSameShipping']";
    public static String editAddress = "xpath:://*[@class='change-billing']";

    public void click_ContinueBtn() {

        getHelper().findElement(continueButton).click();
    }

    public void click_NewAddress () {

        getHelper().findElement(newAddress).click();
    }

    /**
     * Click on Use different billing address checkbox
     */
    public void click_UseDifferent () {

        getHelper().findElement(useDifferentBillingAddress).click();
    }



}
