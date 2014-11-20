package screenObjects.android_app;

import static util.Helper.*;

/**
 * Created by anhpham on 17/11/2014.
 */
public class Register_Screen {

    public static String textBoxes = "xpath:://*[contains(@class,'android.widget.EditText')]";
    public static String showPassword = "className::android.widget.CheckBox";
    public static String submit = "resourceID::register_button_submit";

    public static void input_Email (String email) {

        findsByUISelector(split(textBoxes)[0], split(textBoxes)[1]).get(0).sendKeys(email);
    }

    public static void input_Password (String password) {

        findsByUISelector(split(textBoxes)[0], split(textBoxes)[1]).get(1).sendKeys(password);
    }

    public static void input_RePassword (String password) {

        findsByUISelector(split(textBoxes)[0], split(textBoxes)[1]).get(2).sendKeys(password);
    }

    public static void input_Name (String name) {

        findsByUISelector(split(textBoxes)[0], split(textBoxes)[1]).get(3).sendKeys(name);
    }

    public static void click_ShowPassword () {

        findElement(split(showPassword)[0], split(showPassword)[1]).click();
    }

    public static void click_SubmitBtn () {

        findElement(split(submit)[0], split(submit)[1]).click();
    }



}
