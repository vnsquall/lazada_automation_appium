package pageObject;


import static util.Helper.find_TextView_Android;


public class MyAccount_Screen {


    public static void click_UserData(String userData) {

        find_TextView_Android(userData).click();
    }

}
