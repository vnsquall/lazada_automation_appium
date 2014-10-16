package testsuite;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import scenario.CheckOutScenario;

import static util.Helper.driver;
import static util.VentureText.setText;

/**
 * Created by lazhcm10136 on 9/30/14.
 */
public class CheckOut_Test extends CheckOutScenario {

    @BeforeMethod
    public void setUp() throws Exception {
        init();
    }

    @AfterMethod
    public void tearDown() throws Exception {
        if (driver != null) driver.quit();
    }

    @DataProvider
    Object[][] getVenturesDataToTestToCashOnDelivery() {
        return new Object[][]{
//                {"Singapore"}
                {"Philippines"},
//                {"Indonesia"},
//                {"Vietnam"},
//                {"Thailand"}
        };
    }

    @DataProvider
    Object[][] getVenturesDataToTestToCreditCard() {
        return new Object[][]{
//                {"Singapore"}
                {"Malaysia"}
//                {"Philippines"},
//                {"Indonesia"},
//                {"Vietnam"},
//                {"Thailand"}
        };
    }
    @DataProvider
    Object[][] getVenturesDataToTestToBankTransfer() {
        return new Object[][]{
                {"Indonesia"}

        };
    }


    @Test(dataProvider = "getVenturesDataToTestToCashOnDelivery", enabled = false)
    public void test_CashOnDelivery(String venture) throws Exception {
        checkOutAndUseTheCoD(venture, setText(venture).get("menuWiz"), setText(venture).get("categories"),
                setText(venture).get("filterWiz"), setText(venture).get("prodWiz"));
    }

    @Test(dataProvider = "getVenturesDataToTestToCreditCard", enabled = false)
    public void test_CreditCard(String venture) throws Exception {
        checkOutAndUseCreditCard(venture, setText(venture).get("menuWiz"), setText(venture).get("categories"),
                setText(venture).get("filterWiz"), setText(venture).get("prodWiz"));
    }

    @Test(dataProvider = "getVenturesDataToTestToBankTransfer")
    public void test_BankTransfer(String venture) throws Exception {
        checkOutAndUseBankTransfer(venture, setText(venture).get("menuWiz"), setText(venture).get("categories"),
                setText(venture).get("filterWiz"), setText(venture).get("prodWiz"));
    }
}
