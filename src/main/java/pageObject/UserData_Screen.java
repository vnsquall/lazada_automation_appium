package pageObject;


import io.appium.java_client.android.AndroidDriver;

public class UserData_Screen extends CommonPageObject{

    public UserData_Screen(AndroidDriver driver) {
        super(driver);
    }

    public static String cancel = "resourceID::button_cancel";
    public static String name = "resourceID::clientFirstName";
    public static String email = "resourceID::clientEmail";

    public void click_CancelBtn() {
        getHelper().findElement(cancel).click();
    }

    public String name() {
        return getHelper().findElement(name).getText();
    }

    public String email() {
        return getHelper().findElement(email).getText();
    }



}
