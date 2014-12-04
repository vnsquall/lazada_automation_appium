package testsuite;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import scenario.CheckOutScenario;

import static util.VentureText.setText;

/**
 * Created by lazhcm10136 on 9/30/14.
 */
public class CheckOut_Test extends CheckOutScenario {


    @DataProvider
    Object[][] getVenturesDataToTestCashOnDelivery() {
        return new Object[][]{
                {"Singapore"},
                {"Philippines"},
//                {"Indonesia"},
//                {"Malaysia"},
//                {"Vietnam"},
//                {"Thailand"}
        };
    }

    //4000000000000002 737 06/2015 CC for using the OTP function
    @DataProvider
    Object[][] getVenturesDataToTestCreditCardInvalid() {
        return new Object[][]{
                {"Singapore", "4400123456784011", "Mr Test", "123"},
//                {"Malaysia", "4400123456784011", "Mr Test", "123"},
//                {"Philippines", "4400123456784011", "Mr Test", "123"},
//                {"Indonesia", "4400123456784011", "Mr Test", "123"},
//                {"Vietnam", "4400123456784011", "Mr Test", "123"},
//                {"Thailand", "4400123456784011", "Mr Test", "123"}

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
//                {"Indonesia", "Hikary djakdjd", "Hai ba trung quan 1", "19224832332"},
                {"Philippines", "djaldawd", "Hai ba trung quan 1", "1922483923"},
//                {"Singapore", "dmladad", "Hai ba trung quan 1", "1922483923"},
//                {"Malaysia", "haiwdjald", "Hai ba trung quan 1", "1923123222"},
//                {"Thailand", "andlad", "Hai ba trung quan 1", "1922483923"},
//                {"Vietnam", "dhuaiwuda", "Hai ba trung quan 1", "1922483923"},
        };
    }

    @DataProvider
    Object[][] getVenturesDataToTestEditAddress() {
        return new Object[][]{
                {"Malaysia", "QA name edited", "QA address edited", "1933081152"},
                {"Philippines", "QA name edited", "QA address edited", "1933081152"},
                {"Singapore", "QA name edited", "QA address edited", "1933081152"}

        };
    }

    @DataProvider
    Object[][] getVenturesDataToTestDifferentAddress() {
        return new Object[][]{
                {"Malaysia", "differentially QA name", "QA address differentially", "1933081155"},
                {"Philippines", "differentially QA name", "QA address differentially", "1933081155"},
                {"Singapore", "differentially QA name", "QA address differentially", "1933081155"},
                {"Thailand", "differentially QA name", "QA address differentially", "1933081155"},
        };
    }

    @DataProvider
    Object[][] getVenturesDataToTestEditBillingAddress() {
        return new Object[][]{
                {"Malaysia", "differentially QA name", "QA address differentially", "1933081155"},
//                {"Philippines", "differentially QA name", "QA address differentially", "1933081155"},
                {"Singapore", "differentially QA name", "QA address differentially", "1933081155"},
                {"Thailand", "differentially QA name", "QA address differentially", "1933081155"},
                {"Vietnam", "differentially QA name", "QA address differentially", "1933081155"},
                {"Indonesia", "differentially QA name", "QA address differentially", "1933081155"},

        };
    }

    @DataProvider
    Object[][] getVenturesDataToTestEditShippingAddress() {
        return new Object[][]{
                {"Malaysia", " QA name edit ", "QA address edit", "1933085533"},
                {"Philippines", " QA name edit ", "QA address edit", "1933085533"},
                {"Singapore", " QA name edit ", "QA address edit", "1933085533"},
                {"Thailand", " QA name edit ", "QA address edit", "1933085533"},
                {"Vietnam", " QA name edit ", "QA address edit", "1933085533"},
                {"Indonesia", " QA name edit ", "QA address edit", "1933085533"},

        };
    }

    @DataProvider
    Object[][] getVenturesDataToTestRemoveFromCart() {
        return new Object[][]{
                {"Singapore"},
//                {"Thailand"},
//                {"Vietnam"},
//                {"Indonesia"},
//                {"Malaysia"},
//                {"Philippines"}

        };
    }

    @DataProvider
    Object[][] getVenturesDataToTestEditPaymentMethod() {
        return new Object[][]{
                {"Malaysia"},
                {"Philippines"},
                {"Singapore"},
                {"Thailand"},
                {"Vietnam"},
                {"Indonesia"},

        };
    }

    @DataProvider
    Object[][] getVenturesDataToTestCoupon() {
        return new Object[][]{
                {"Malaysia", "1ijc6sd"},


        };
    }

    @DataProvider
    Object[][] getVenturesDataToTestShareOrder() {
        return new Object[][]{

                {"Philippines"},
                {"Singapore"},
                {"Thailand"},
                {"Vietnam"},
                {"Indonesia"},
                {"Malaysia"}

        };
    }

    @DataProvider
    Object[][] getVenturesDataToTestSavedCreditCard() {
        return new Object[][]{

                {"Philippines"},
                {"Singapore"},
                {"Vietnam"},
                {"Indonesia"},
                {"Malaysia"},
                {"Thailand"}


        };
    }

    @DataProvider
    Object[][] getVenturesDataToTestNewAccount() {
        return new Object[][]{

//                {"Philippines"},
//                {"Singapore"},
                {"Vietnam"},
//                {"Indonesia"},
//                {"Malaysia"},
//                {"Thailand"}


        };
    }

    @DataProvider
    Object[][] getVenturesDataToTestNewBillingAddress() {
        return new Object[][]{

                {"Philippines", " QA name edit ", "QA address edit", "0933085533"},
//                {"Singapore", " QA name edit ", "QA address edit", "0933085533"},
//                {"Vietnam", " QA name edit ", "QA address edit", "0933085533"},
//                {"Indonesia", " QA name edit ", "QA address edit", "0933085533"},
//                {"Malaysia", " QA name edit ", "QA address edit", "0933085533"},
//                {"Thailand", " QA name edit ", "QA address edit", "0933085533"},



        };
    }

    @DataProvider
    Object[][] getVenturesDataToTestBankTransferNewUser() {
        return new Object[][]{

                {"Indonesia", 3, "Mr Test"}

        };
    }

    @DataProvider
    Object[][] getVenturesDataToTestBankTransferDifferentBillingAddress() {
        return new Object[][]{

                {"Indonesia", 3, "Mr Test", " QA name edit ", "QA address editddkdmd", "09330242442"}

        };
    }

    @DataProvider
    Object[][] getVenturesDataToTestCashOnDeliveryGuest () {
        return new Object[][]{
                {"Singapore", "jdalwd", "dhakdhawd", "99999393939"},
                {"Philippines", "jack", "Bom bai", "8788332222"},
                {"Indonesia", "nicky", "LA", "38877737373"},
                {"Malaysia", "jdjdjd", "jdjdda", "42424242424"},
                {"Vietnam", "nicky", "LA", "38877737373" },
//                {"Thailand", "hajdald", "dgaihwdh", "88883838383"}
        };
    }

    @DataProvider
    Object[][] getVenturesDataToTestBankTransferGuest () {
        return new Object[][]{
                {"Indonesia", "nicky", "LA", "38877737373", 3, "jdajwd"},
        };
    }
    @DataProvider
    Object[][] getVenturesDataToTestTerm () {
        return new Object[][]{
//                {"Singapore"},
//                {"Philippines"},
                {"Indonesia"},
//                {"Malaysia"},
//                {"Vietnam"},
//                {"Thailand"}
        };
    }


    @Test(dataProvider = "getVenturesDataToTestCashOnDelivery")
    public void test_CashOnDelivery(String venture) throws Exception {

        checkOutUseTheCoD(venture,
                setText(venture).get("menuWiz"),
                setText(venture).get("categories"),
                setText(venture).get("filterWiz"),
                setText(venture).get("prodWiz"));
    }

    @Test(dataProvider = "getVenturesDataToTestCashOnDeliveryGuest")
    public void test_CashOnDeliveryGuest(String venture, String name,
                                         String address, String phoneNumber) throws Exception {

        checkoutCODAsGuest(venture,
                setText(venture).get("menuWiz"),
                setText(venture).get("categories"),
                setText(venture).get("filterWiz"),
                setText(venture).get("prodWiz"),
                name,
                address,
                phoneNumber);
    }

    @Test(dataProvider = "getVenturesDataToTestBankTransferGuest")
    public void test_BankTransferGuest(String venture, String name,
                                         String address, String phoneNumber, int bankIndex, String senderName) throws Exception {

        checkoutBankTransferAsGuest(venture,
                setText(venture).get("menuWiz"),
                setText(venture).get("categories"),
                setText(venture).get("filterWiz"),
                setText(venture).get("prodWiz"),
                name,
                address,
                phoneNumber,
                bankIndex,
                senderName);
    }

    @Test(dataProvider = "getVenturesDataToTestCreditCardInvalid")
    public void test_CreditCardInvalid(String venture, String creditNumber, String customerName, String securityCode) throws Exception {

        checkOutUseCreditCardInvalid(venture,
                setText(venture).get("menuWiz"),
                setText(venture).get("categories"),
                setText(venture).get("filterWiz"),
                setText(venture).get("prodWiz"),
                creditNumber,
                customerName,
                securityCode);
    }

    @Test(dataProvider = "getVenturesDataToTestBankTransfer")
    public void test_BankTransfer(String venture, int bankIndex, String senderName) throws Exception {

        checkOutUseBankTransfer(venture,
                setText(venture).get("menuWiz"),
                setText(venture).get("categories"),
                setText(venture).get("filterWiz"),
                setText(venture).get("prodWiz"),
                bankIndex,
                senderName);
    }

    @Test(dataProvider = "getVenturesDataToTestPaypal")
    public void test_Paypal(String venture) throws Exception {

        checkOutAndUsePaypal(venture,
                setText(venture).get("menuWiz"),
                setText(venture).get("categories"),
                setText(venture).get("filterWiz"),
                setText(venture).get("prodWiz"));
    }

    @Test(dataProvider = "getVenturesDataToTestCreateAddress")
    public void test_CreateShippingAddress(String venture, String name, String address,
                                           String phoneNumber) throws Exception {
        checkOutCreateShippingAddress(venture,
                setText(venture).get("menuWiz"),
                setText(venture).get("categories"),
                setText(venture).get("filterWiz"),
                setText(venture).get("prodWiz"),
                name,
                address,
                phoneNumber);
    }

    @Test(dataProvider = "getVenturesDataToTestEditShippingAddress")
    public void test_EditShippingAddress(String venture, String name, String address, String phoneNumber) throws Exception {

        checkOutEditShippingAddress(venture,
                setText(venture).get("menuWiz"),
                setText(venture).get("categories"),
                setText(venture).get("filterWiz"),
                setText(venture).get("prodWiz"),
                name,
                address,
                phoneNumber);
    }
    
    @Test(dataProvider = "getVenturesDataToTestDifferentAddress")
    public void test_DifferentBillingAddress(String venture, String name, String address, String phoneNumber) throws Exception {

        checkOutDifferenBillingtAddress(venture,
                setText(venture).get("menuWiz"),
                setText(venture).get("categories"),
                setText(venture).get("filterWiz"),
                setText(venture).get("prodWiz"),
                name,
                address,
                phoneNumber);
    }

    @Test(dataProvider = "getVenturesDataToTestEditBillingAddress")
    public void test_EditBillingAddress(String venture, String name, String address, String phoneNumber) throws Exception {
        checkOutEditBillingAddress(venture,
                setText(venture).get("menuWiz"),
                setText(venture).get("categories"),
                setText(venture).get("filterWiz"),
                setText(venture).get("prodWiz"),
                name,
                address,
                phoneNumber);
    }



    @Test(dataProvider = "getVenturesDataToTestRemoveFromCart")
    public void test_RemoveFromCart(String venture) throws Exception {

        checkOutRemoveFromCart(venture,
                setText(venture).get("menuWiz"),
                setText(venture).get("categories"),
                setText(venture).get("filterWiz"),
                setText(venture).get("prodWiz")
        );
    }

    @Test(dataProvider = "getVenturesDataToTestEditPaymentMethod")
    public void test_EditPaymentMethod(String venture) throws Exception {

        checkOutEditPaymentMethod(venture,
                setText(venture).get("menuWiz"),
                setText(venture).get("categories"),
                setText(venture).get("filterWiz"),
                setText(venture).get("prodWiz")
        );
    }

    // Staging only
    @Test(dataProvider = "getVenturesDataToTestCoupon")
    public void test_Coupon(String venture, String coupon) throws Exception {

        checkOutCoupon(venture,
                setText(venture).get("menuWiz"),
                setText(venture).get("categories"),
                setText(venture).get("filterWiz"),
                setText(venture).get("prodWiz"),
                coupon);
    }

    @Test(dataProvider = "getVenturesDataToTestShareOrder")
    public void test_ShareOrder(String venture) throws Exception {

        checkOutShareOrder(venture,
                setText(venture).get("menuWiz"),
                setText(venture).get("categories"),
                setText(venture).get("filterWiz"),
                setText(venture).get("prodWiz")
        );
    }

    // Staging only
    @Test(dataProvider = "getVenturesDataToTestSavedCreditCard")
    public void test_SavedCreditCard(String venture) throws Exception {

        checkOutSavedCreditCard(venture,
                setText(venture).get("menuWiz"),
                setText(venture).get("categories"),
                setText(venture).get("filterWiz"),
                setText(venture).get("prodWiz")
        );
    }

    @Test(dataProvider = "getVenturesDataToTestNewAccount")
    public void test_NewAccount(String venture) throws Exception {

        checkOutNewAccount(venture,
                setText(venture).get("menuWiz"),
                setText(venture).get("categories"),
                setText(venture).get("filterWiz"),
                setText(venture).get("prodWiz")
        );
    }

    @Test(dataProvider = "getVenturesDataToTestNewBillingAddress")
    public void test_NewBillingAddress(String venture, String name, String address, String phoneNumber) throws Exception {

        checkoutNewBillingAddress(venture,
                setText(venture).get("menuWiz"),
                setText(venture).get("categories"),
                setText(venture).get("filterWiz"),
                setText(venture).get("prodWiz"),
                name,
                address,
                phoneNumber);
    }

    /**
     * Create new user and check out via Bank transfer - ID
     */
    @Test(dataProvider = "getVenturesDataToTestBankTransferNewUser")
    public void test_BankTransferNewUser(String venture, int bankIndex, String senderName) throws Exception {

        checkoutBankTransferNewUser(venture,
                setText(venture).get("menuWiz"),
                setText(venture).get("categories"),
                setText(venture).get("filterWiz"),
                setText(venture).get("prodWiz"),
                bankIndex,
                senderName );
    }

    @Test(dataProvider = "getVenturesDataToTestBankTransferDifferentBillingAddress")
    public void test_BankTransferEditBillingAddress(String venture, int bankIndex, String senderName,
                                                    String name, String address, String phoneNumber) throws Exception {

        checkoutBankTransferDifferentBillingAddress(venture,
                setText(venture).get("menuWiz"),
                setText(venture).get("categories"),
                setText(venture).get("filterWiz"),
                setText(venture).get("prodWiz"),
                bankIndex,
                senderName,
                name,
                address,
                phoneNumber);
    }


}
