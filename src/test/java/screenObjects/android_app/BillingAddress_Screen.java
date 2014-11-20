package screenObjects.android_app;



import org.openqa.selenium.WebElement;

import static util.Helper.*;

/**
 * Created by anhpham on 17/11/2014.
 */
public class BillingAddress_Screen {

    private static String continueButton = "xpath:://*[@class='orange-button']";
    private static String name = "xpath:://*[@id='ThreeStepBillingAddressForm_first_name']";
    private static String address = "xpath:://*[@id='ThreeStepBillingAddressForm_address1']";
    private static String region = "xpath:://*[@id='ThreeStepBillingAddressForm_location_0']";
    private static String city = "xpath:://*[@id='ThreeStepBillingAddressForm_location_1']";
    private static String ward = "xpath:://*[@id='ThreeStepBillingAddressForm_location_2']";
    private static String postCode = "xpath:://*[@id='ThreeStepBillingAddressForm_postcode']";
    private static String phoneNumber = "xpath:://*[@id='ThreeStepBillingAddressForm_phone']";


    public static void click_ContinueBtn() {

        findElement(split(continueButton)[0], split(continueButton)[1]).click();
    }

    public static void input_Name(String _name) {

        findElement(split(name)[0], split(name)[1]).sendKeys(_name);
    }

    public static void input_Address(String _address) {

        findElement(split(address)[0], split(address)[1]).sendKeys(_address);
    }

    public static WebElement region () {

        return findElement(split(region)[0], split(region)[1]);
    }

    public static WebElement city () {

        return findElement(split(city)[0], split(city)[1]);
    }

    public static WebElement ward() {

        return findElement(split(ward)[0], split(ward)[1]);
    }

    public static void input_PostCode (String _postCode) {

        findElement(split(postCode)[0], split(postCode)[1]).sendKeys(_postCode);
    }

    public static void input_PhoneNumber(String _phoneNumber) {

        findElement(split(phoneNumber)[0], split(phoneNumber)[1]).sendKeys(_phoneNumber);
    }





}
