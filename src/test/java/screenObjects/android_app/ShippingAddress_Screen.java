package screenObjects.android_app;



import org.openqa.selenium.WebElement;

import static util.Helper.*;

/**
 * Created by anhpham on 17/11/2014.
 */
public class ShippingAddress_Screen {

    private static String continueButton = "xpath:://*[@class='orange-button']";
    private static String name = "xpath:://*[@id='ThreeStepShippingAddressForm_first_name']";
    private static String address = "xpath:://*[@id='ThreeStepShippingAddressForm_address1']";
    private static String region = "xpath:://*[@id='ThreeStepShippingAddressForm_location_0']";
    private static String city = "xpath:://*[@id='ThreeStepShippingAddressForm_location_1']";
    private static String ward = "xpath:://*[@id='ThreeStepShippingAddressForm_location_2']";
    private static String postCode = "xpath:://*[@id='ThreeStepShippingAddressForm_postcode']";
    private static String phoneNumber = "xpath:://*[@id='ThreeStepShippingAddressForm_phone']";


    public static void click_ContinueBtn() {

        findByUISelector(split(continueButton)[0], split(continueButton)[1]).click();
    }

    public static void input_Name(String _name) {

        findByUISelector(split(name)[0], split(name)[1]).sendKeys(_name);
    }

    public static void input_Address(String _address) {

        findByUISelector(split(address)[0], split(address)[1]).sendKeys(_address);
    }

    public static WebElement region () {

        return findByUISelector(split(region)[0], split(region)[1]);
    }

    public static WebElement city () {

        return findByUISelector(split(city)[0], split(city)[1]);
    }

    public static WebElement ward() {

        return findByUISelector(split(ward)[0], split(ward)[1]);
    }

    public static void input_PostCode (String _postCode) {

        findByUISelector(split(postCode)[0], split(postCode)[1]).sendKeys(_postCode);
    }

    public static void input_PhoneNumber(String _phoneNumber) {

        findByUISelector(split(phoneNumber)[0], split(phoneNumber)[1]).sendKeys(_phoneNumber);
    }





}
