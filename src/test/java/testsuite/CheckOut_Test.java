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
                {"Singapore"},
                {"Philippines"},
                {"Indonesia"},
                {"Vietnam"},
                {"Thailand"}
        };
    }

    //4000000000000002 737 06/2015 CC for using the OTP function
    @DataProvider
    Object[][] getVenturesDataToTestCreditCard() {
        return new Object[][]{
                {"Singapore"},
                {"Malaysia"},
                {"Philippines"},
                {"Indonesia"},
                {"Vietnam"},
                {"Thailand"}
        };
    }

    @DataProvider
    Object[][] getVenturesDataToTestBankTransfer() {
        return new Object[][]{
                {"Indonesia", 3, "Mr Test"}

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

    @DataProvider
    Object[][] getVenturesDataToTestCreateAddress() {
        return new Object[][]{
//                {"Malaysia", "QA name", "QA address", 3, 4, "0933081162"}
//                {"Philippines"},
                {"Malaysia", "QA name", "QA address", 3, 4, "0933081162"}
//                {"Thailand"}
        };
    }

    @DataProvider
    Object[][] getVenturesDataToTestEditAddress() {
        return new Object[][]{
                {"Malaysia", "QA name edited", "QA address edited", "0933081152"},
                {"Philippines"},
                {"Singapore"},
                {"Thailand"}
        };
    }

    @DataProvider
    Object[][] getVenturesDataToTestDifferentAddress() {
        return new Object[][]{
                {"Malaysia", "differentially QA name", "QA address differentially", "0933081155"},
                {"Philippines"},
                {"Singapore"},
                {"Thailand"}
        };
    }

    @Test(dataProvider = "getVenturesDataToTestCashOnDelivery")
    public void test_CashOnDelivery(String venture) throws Exception {
        checkOutAndUseTheCoD(venture, setText(venture).get("menuWiz"), setText(venture).get("categories"),
                setText(venture).get("filterWiz"), setText(venture).get("prodWiz"));
    }

    @Test(dataProvider = "getVenturesDataToTestCreditCard")
    public void test_CreditCard(String venture) throws Exception {
        checkOutAndUseCreditCard(venture, setText(venture).get("menuWiz"), setText(venture).get("categories"),
                setText(venture).get("filterWiz"), setText(venture).get("prodWiz"));
    }

    @Test(dataProvider = "getVenturesDataToTestBankTransfer")
    public void test_BankTransfer(String venture, int bankIndex, String senderName) throws Exception {
        checkOutAndUseBankTransfer(venture, setText(venture).get("menuWiz"), setText(venture).get("categories"),
                setText(venture).get("filterWiz"), setText(venture).get("prodWiz"), bankIndex, senderName);
    }

    @Test(dataProvider = "getVenturesDataToTestPaypal", enabled = false)
    public void test_Paypal(String venture) throws Exception {
        checkOutAndUsePaypal(venture, setText(venture).get("menuWiz"), setText(venture).get("categories"),
                setText(venture).get("filterWiz"), setText(venture).get("prodWiz"));
    }

    @Test(dataProvider = "getVenturesDataToTestCreateAddress")
    public void test_CreateAddress(String venture, String name, String address, int addressIndex,
                                   int locationIndex, String phoneNumber) throws Exception {
        checkOutCreateAddress(venture, setText(venture).get("menuWiz"), setText(venture).get("categories"),
                setText(venture).get("filterWiz"), setText(venture).get("prodWiz"), name, address, phoneNumber);
    }

    @Test(dataProvider = "getVenturesDataToTestEditAddress")
    public void test_EditAddress(String venture, String name, String address, String phoneNumber) throws Exception {
        checkOutEditAddress(venture, setText(venture).get("menuWiz"), setText(venture).get("categories"),
                setText(venture).get("filterWiz"), setText(venture).get("prodWiz"),
                setText(venture).get("editAddSuccess"), name, address, phoneNumber);
    }

    @Test(dataProvider = "getVenturesDataToTestDifferentAddress")
    public void test_DifferentAddress(String venture, String name, String address, String phoneNumber) throws Exception {
        checkOutDifferentAddress(venture, setText(venture).get("menuWiz"), setText(venture).get("categories"),
                setText(venture).get("filterWiz"), setText(venture).get("prodWiz"),
                setText(venture).get("editAddSuccess"), name, address, phoneNumber);
    }


}
