package screenObjects.android_app;



import org.openqa.selenium.WebElement;

import static util.Helper.*;

/**
 * Created by anhpham on 17/11/2014.
 */
public class ShippingAddress_Screen {

    public static String continueButton = "xpath:://*[@class='orange-button']";
    public static String name = "xpath:://*[@id='ThreeStepShippingAddressForm_first_name']";
    public static String address = "xpath:://*[@id='ThreeStepShippingAddressForm_address1']";
    public static String region = "xpath:://*[@id='ThreeStepShippingAddressForm_location_0']";
    public static String city = "xpath:://*[@id='ThreeStepShippingAddressForm_location_1']";
    public static String ward = "xpath:://*[@id='ThreeStepShippingAddressForm_location_2']";
    public static String postCode = "xpath:://*[@id='ThreeStepShippingAddressForm_postcode']";
    public static String phoneNumber = "xpath:://*[@id='ThreeStepShippingAddressForm_phone']";


    public static void click_ContinueBtn() {

        findElement(continueButton).click();
    }

    public static void input_Name(String _name) {

        findElement(name).sendKeys(_name);
    }

    public static void input_Address(String _address) {

        findElement(address).sendKeys(_address);
    }

    public static WebElement region () {

        return findElement(region);
    }

    public static WebElement city () {

        return findElement(city);
    }

    public static WebElement ward() {

        return findElement(ward);
    }

    public static void input_PostCode (String _postCode) {

        findElement(postCode).sendKeys(_postCode);
    }

    public static void input_PhoneNumber(String _phoneNumber) {

        findElement(phoneNumber).sendKeys(_phoneNumber);
    }





}
