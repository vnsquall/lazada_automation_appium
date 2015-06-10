package pageObject;

import io.appium.java_client.android.AndroidDriver;

public class MyAccount_Screen extends CommonPageObject{

    public MyAccount_Screen(AndroidDriver driver) {
        super(driver);
    }

    public void click_UserData(String userData) {

        getHelper().find_TextView_Android(userData).click();
    }

}
