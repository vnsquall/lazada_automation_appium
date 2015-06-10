package pageObject;



import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;


public class ShippingAddress_Screen extends CommonPageObject{

    public ShippingAddress_Screen(AndroidDriver driver){
        super(driver);
    }

    public static String continueButton = "xpath:://*[@class='orange-button']";
    public static String name = "xpath:://*[@id='ThreeStepShippingAddressForm_first_name']";
    public static String address = "xpath:://*[@id='ThreeStepShippingAddressForm_address1']";
    public static String region = "xpath:://*[@id='ThreeStepShippingAddressForm_location_0']";
    public static String city = "xpath:://*[@id='ThreeStepShippingAddressForm_location_1']";
    public static String ward = "xpath:://*[@id='ThreeStepShippingAddressForm_location_2']";
    public static String postCode = "xpath:://*[@id='ThreeStepShippingAddressForm_postcode']";
    public static String phoneNumber = "xpath:://*[@id='ThreeStepShippingAddressForm_phone']";


    public void click_ContinueBtn() {

        getHelper().findElement(continueButton).click();
    }

    public void input_Name(String _name) {

        getHelper().findElement(name).sendKeys(_name);
    }

    public void input_Address(String _address) {

        getHelper().findElement(address).sendKeys(_address);
    }

    public WebElement region () {

        return getHelper().findElement(region);
    }

    public WebElement city () {

        return getHelper().findElement(city);
    }

    public WebElement ward() {

        return getHelper().findElement(ward);
    }

    public void input_PostCode (String _postCode) {

        getHelper().findElement(postCode).sendKeys(_postCode);
    }

    public void input_PhoneNumber(String _phoneNumber) {

        getHelper().findElement(phoneNumber).sendKeys(_phoneNumber);
    }

    /**
     * try to find Web view in 30s
     */
    public void waitForWebView() {

        long start = System.currentTimeMillis();
        long end = start + 30 * 1000; // 30 seconds * 1000 ms/sec
        boolean isWebViewLoaded = false;
        while (System.currentTimeMillis() < end)
        {

            isWebViewLoaded = getHelper().switchToWebView();
            if(isWebViewLoaded == true) return;

        }
        System.out.println(">>>> No web view!!!");
    }



}
