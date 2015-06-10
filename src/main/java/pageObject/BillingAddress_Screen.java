package pageObject;



import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import static util.Helper.split;


public class BillingAddress_Screen extends CommonPageObject{

    public BillingAddress_Screen(AndroidDriver driver) {
        super(driver);
    }

    public static String postCode = "xpath:://*[@id='ThreeStepBillingAddressForm_postcode']";
    public static String continueButton = "xpath:://*[@class='orange-button']";
    public static String name = "xpath:://*[@id='ThreeStepBillingAddressForm_first_name']";
    public static String address = "xpath:://*[@id='ThreeStepBillingAddressForm_address1']";
    public static String region = "xpath:://*[@id='ThreeStepBillingAddressForm_location_0']";
    public static String city = "xpath:://*[@id='ThreeStepBillingAddressForm_location_1']";
    public static String ward = "xpath:://*[@id='ThreeStepBillingAddressForm_location_2']";
    public static String phoneNumber = "xpath:://*[@id='ThreeStepBillingAddressForm_phone']";

    public void click_ContinueBtn() {
        getHelper().findElement(split(continueButton)[0], split(continueButton)[1]).click();
    }

    public void input_Name(String _name) {

        getHelper().findElement(split(name)[0], split(name)[1]).sendKeys(_name);
    }

    public void input_Address(String _address) {

        getHelper().findElement(split(address)[0], split(address)[1]).sendKeys(_address);
    }

    public WebElement region () {

        return getHelper().findElement(split(region)[0], split(region)[1]);
    }

    public WebElement city () {

        return getHelper().findElement(split(city)[0], split(city)[1]);
    }

    public WebElement ward() {

        return getHelper().findElement(split(ward)[0], split(ward)[1]);
    }

    public void input_PostCode (String _postCode) {

        getHelper().findElement(split(postCode)[0], split(postCode)[1]).sendKeys(_postCode);
    }

    public void input_PhoneNumber(String _phoneNumber) {

        getHelper().findElement(split(phoneNumber)[0], split(phoneNumber)[1]).sendKeys(_phoneNumber);
    }





}
