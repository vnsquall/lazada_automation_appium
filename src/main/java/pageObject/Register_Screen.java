package pageObject;

import io.appium.java_client.android.AndroidDriver;

public class Register_Screen extends CommonPageObject{
    
    public Register_Screen(AndroidDriver driver){
        super(driver);
    }

    public static String textBoxes = "xpath:://*[contains(@class,'android.widget.EditText')]";
    public static String showPassword = "className::android.widget.CheckBox";
    public static String submit = "resourceID::register_button_submit";

    public void input_Email (String email) {

        getHelper().findElements(textBoxes).get(0).sendKeys(email);
    }

    public void input_Password (String password) {

        getHelper().findElements(textBoxes).get(1).sendKeys(password);
    }

    public void input_RePassword (String password) {

        getHelper().findElements(textBoxes).get(2).sendKeys(password);
    }

    public void input_Name (String name) {

        getHelper().findElements(textBoxes).get(3).sendKeys(name);
    }

    public void click_ShowPassword () {

        getHelper().findElement(showPassword).click();
        getHelper().hideKeyBoard();
    }

    public void click_SubmitBtn () {

        getHelper().findElement(submit).click();
    }

}
