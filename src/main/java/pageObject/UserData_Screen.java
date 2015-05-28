package pageObject;


import static util.Helper.findElement;


public class UserData_Screen {

    public static String cancel = "resourceID::button_cancel";
    public static String name = "resourceID::clientFirstName";
    public static String email = "resourceID::clientEmail";

    public static void click_CancelBtn() {
        findElement(cancel).click();
    }

    public static String name() {
        return findElement(name).getText();
    }

    public static String email() {
        return findElement(email).getText();
    }



}
