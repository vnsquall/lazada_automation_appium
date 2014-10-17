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
    Object[][] getVenturesDataToTestCashOnDelivery() {
        return new Object[][]{
//                {"Singapore"}
                {"Philippines"},
//                {"Indonesia"},
//                {"Vietnam"},
//                {"Thailand"}
        };
    }

    @DataProvider
    Object[][] getVenturesDataToTestCreditCard() {
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
    Object[][] getVenturesDataToTestBankTransfer() {
        return new Object[][]{
                {"Indonesia"}

        };
    }

    @DataProvider
    Object[][] getVenturesDataToTestPaypal() {
        return new Object[][]{
                {"Malaysia"},
                {"Philippines"},
                {"Singapore"},
                {"Thailand"}
        };
    }


    @Test(dataProvider = "getVenturesDataToTestCashOnDelivery", enabled = false)
    public void test_CashOnDelivery(String venture) throws Exception {
        checkOutAndUseTheCoD(venture, setText(venture).get("menuWiz"), setText(venture).get("categories"),
                setText(venture).get("filterWiz"), setText(venture).get("prodWiz"));
    }

    @Test(dataProvider = "getVenturesDataToTestCreditCard", enabled = false)
    public void test_CreditCard(String venture) throws Exception {
        checkOutAndUseCreditCard(venture, setText(venture).get("menuWiz"), setText(venture).get("categories"),
                setText(venture).get("filterWiz"), setText(venture).get("prodWiz"));
    }

    @Test(dataProvider = "getVenturesDataToTestBankTransfer")
    public void test_BankTransfer(String venture) throws Exception {
        checkOutAndUseBankTransfer(venture, setText(venture).get("menuWiz"), setText(venture).get("categories"),
                setText(venture).get("filterWiz"), setText(venture).get("prodWiz"));
    }

    @Test(dataProvider = "getVenturesDataToTestPaypal")
    public void test_Paypal(String venture) throws Exception {
        checkOutAndUsePaypal(venture, setText(venture).get("menuWiz"), setText(venture).get("categories"),
                setText(venture).get("filterWiz"), setText(venture).get("prodWiz"));
    }
}
