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
    @BeforeMethod
    public void setUp() throws Exception {
        init();
    }

    @AfterMethod
    public void tearDown() throws Exception {
        if (driver != null) driver.quit();
    }

    @DataProvider
    Object[][] getVenturesDataToRegister() {
        return new Object[][]{
                {"Singapore", "qa000@mail.com", "a12345", "Mr Test"}, //Existed Account
                {"Singapore", "new_user123@mail.com", "a12345", "Mr Test_123"}
//                {"Malaysia"},
//                {"Philippines"},
//                {"Indonesia"},
//                {"Vietnam"}
        };
    }

    @DataProvider
    Object[][] getVenturesDataToLoginFailed() {
        return new Object[][]{
                {"Singapore", "new_user123@mail.com", "a12345"}
        };
    }

    @DataProvider
    Object[][] getVenturesDataToLoginPass() {
        return new Object[][]{
                {"Singapore", "qa000@mail.com", "a12345"}
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
