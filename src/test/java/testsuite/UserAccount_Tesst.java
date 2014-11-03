package testsuite;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import scenario.UserAccountScenario;

import static util.Helper.driver;
import static util.VentureText.setText;

/**
 * Created by lazhcm10136 on 10/7/14.
 */
public class UserAccount_Tesst extends UserAccountScenario {


    @DataProvider
    Object[][] getVenturesDataToRegister() {
        return new Object[][]{
                {"Singapore", "qa000@mail.com", "a12345", "Mr Test"}, //Existed Account
                {"Singapore", "new_user123@mail.com", "a12345", "Mr Test_123"},
                {"Malaysia", "qa000@mail.com", "a12345", "Mr Test"},
                {"Philippines", "qa000@mail.com", "a12345", "Mr Test"},
                {"Indonesia", "qa000@mail.com", "a12345", "Mr Test"},
                {"Vietnam", "qa000@mail.com", "a12345", "Mr Test"}
        };
    }

    @DataProvider
    Object[][] getVenturesDataToLoginFailed() {
        return new Object[][]{
                {"Singapore", "test_user123@mail.com", "@12345"},
                {"Vietnam", "test_user123@mail.com", "@12345"},
                {"Malaysia", "test_user123@mail.com", "@12345"},
                {"Philippines", "test_user123@mail.com", "@12345"},
                {"Indonesia", "test_user123@mail.com", "@12345"}
        };
    }

    @DataProvider
    Object[][] getVenturesDataToLoginPass() {
        return new Object[][]{
                {"Singapore", "qa000@mail.com", "a12345"},
                {"Vietnam", "qa000@mail.com", "a12345"},
                {"Malaysia", "qa000@mail.com", "a12345"},
                {"Philippines", "qa000@mail.com", "a12345"},
                {"Indonesia", "qa000@mail.com", "a12345"}
        };
    }

    @Test(dataProvider = "getVenturesDataToRegister")
    public void test_UserAccountRegister(String venture, String mail, String pass, String name) throws Exception {
        registerWithNewUser(venture, setText(venture).get("menuWiz"), setText(venture).get("account"),
                setText(venture).get("userData"), setText(venture).get("createUser"), mail, pass, name);
    }

    @Test(dataProvider = "getVenturesDataToLoginFailed")
    public void test_UserAccountLoginFailed(String venture, String mail, String pass) throws Exception {
        loginWrongData(venture, setText(venture).get("menuWiz"), setText(venture).get("account"),
                setText(venture).get("userData"), mail, pass, setText(venture).get("loginFailed"));
    }

    @Test(dataProvider = "getVenturesDataToLoginPass")
    public void test_UserAccountLoginAndLogout(String venture, String mail, String pass) throws Exception {
        loginAndLogout(venture, setText(venture).get("menuWiz"), setText(venture).get("account"),
                setText(venture).get("userData"), mail, pass, setText(venture).get("logOut"));
    }
}
