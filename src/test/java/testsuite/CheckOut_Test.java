package testsuite;

import org.testng.annotations.Test;
import scenario.CheckOutScenario;

import static util.VentureText.setText;

/**
 * Created by lazhcm10136 on 9/30/14.
 */
public class CheckOut_Test extends CheckOutScenario {


    @Test(dataProvider = "getVenturesDataToTestCashOnDelivery", dataProviderClass = StaticDataProvider.class)
    public void test_CashOnDelivery(String venture) throws Exception {

        checkOutUseTheCoD(venture,
                setText(venture).get("menuWiz"),
                setText(venture).get("categories"),
                setText(venture).get("filterWiz"),
                setText(venture).get("prodWiz"));
    }

    @Test(dataProvider = "getVenturesDataToTestCashOnDeliveryGuest", dataProviderClass = StaticDataProvider.class)
    public void test_CashOnDeliveryGuest(String venture,
                                         String name,
                                         String address,
                                         String phoneNumber) throws Exception {

        checkoutCODAsGuest(venture,
                setText(venture).get("menuWiz"),
                setText(venture).get("categories"),
                setText(venture).get("filterWiz"),
                setText(venture).get("prodWiz"),
                name,
                address,
                phoneNumber);
    }

    @Test(dataProvider = "getVenturesDataToTestBankTransferGuest", dataProviderClass = StaticDataProvider.class)
    public void test_BankTransferGuest(String venture,
                                       String name,
                                       String address,
                                       String phoneNumber,
                                       int bankIndex,
                                       String senderName) throws Exception {

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

    @Test(dataProvider = "getVenturesDataToTestCreditCardInvalid", dataProviderClass = StaticDataProvider.class)
    public void test_CreditCardInvalid(String venture,
                                       String creditNumber,
                                       String customerName,
                                       String securityCode) throws Exception {

        checkOutUseCreditCardInvalid(venture,
                setText(venture).get("menuWiz"),
                setText(venture).get("categories"),
                setText(venture).get("filterWiz"),
                setText(venture).get("prodWiz"),
                creditNumber,
                customerName,
                securityCode);
    }

    @Test(dataProvider = "getVenturesDataToTestCreditCardInvalidGuest", dataProviderClass = StaticDataProvider.class)
    public void test_CreditCardInvalidGuest(String venture,
                                            String creditNumber,
                                            String customerName,
                                            String securityCode,
                                            String name,
                                            String address,
                                            String phoneNumber) throws Exception {

        checkOutUseCreditCardInvalidGuest(venture,
                setText(venture).get("menuWiz"),
                setText(venture).get("categories"),
                setText(venture).get("filterWiz"),
                setText(venture).get("prodWiz"),
                creditNumber,
                customerName,
                securityCode,
                name,
                address,
                phoneNumber);
    }

    @Test(dataProvider = "getVenturesDataToTestBankTransfer", dataProviderClass = StaticDataProvider.class)
    public void test_BankTransfer(String venture, int bankIndex, String senderName) throws Exception {

        checkOutUseBankTransfer(venture,
                setText(venture).get("menuWiz"),
                setText(venture).get("categories"),
                setText(venture).get("filterWiz"),
                setText(venture).get("prodWiz"),
                bankIndex,
                senderName);
    }

    @Test(dataProvider = "getVenturesDataToTestPaypal", dataProviderClass = StaticDataProvider.class)
    public void test_Paypal(String venture) throws Exception {

        checkOutAndUsePaypal(venture,
                setText(venture).get("menuWiz"),
                setText(venture).get("categories"),
                setText(venture).get("filterWiz"),
                setText(venture).get("prodWiz"));
    }

    @Test(dataProvider = "getVenturesDataToTestCreateAddress", dataProviderClass = StaticDataProvider.class)
    public void test_CreateShippingAddress(String venture,
                                           String name,
                                           String address,
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

    @Test(dataProvider = "getVenturesDataToTestEditShippingAddress", dataProviderClass = StaticDataProvider.class)
    public void test_EditShippingAddress(String venture,
                                         String name,
                                         String address,
                                         String phoneNumber) throws Exception {

        checkOutEditShippingAddress(venture,
                setText(venture).get("menuWiz"),
                setText(venture).get("categories"),
                setText(venture).get("filterWiz"),
                setText(venture).get("prodWiz"),
                name,
                address,
                phoneNumber);
    }

    @Test(dataProvider = "getVenturesDataToTestDifferentAddress", dataProviderClass = StaticDataProvider.class)
    public void test_DifferentBillingAddress(String venture,
                                             String name,
                                             String address,
                                             String phoneNumber) throws Exception {

        checkOutDifferenBillingtAddress(venture,
                setText(venture).get("menuWiz"),
                setText(venture).get("categories"),
                setText(venture).get("filterWiz"),
                setText(venture).get("prodWiz"),
                name,
                address,
                phoneNumber);
    }

    @Test(dataProvider = "getVenturesDataToTestEditBillingAddress", dataProviderClass = StaticDataProvider.class)
    public void test_EditBillingAddress(String venture,
                                        String name,
                                        String address,
                                        String phoneNumber) throws Exception {

        checkOutEditBillingAddress(venture,
                setText(venture).get("menuWiz"),
                setText(venture).get("categories"),
                setText(venture).get("filterWiz"),
                setText(venture).get("prodWiz"),
                name,
                address,
                phoneNumber);
    }



    @Test(dataProvider = "getVenturesDataToTestRemoveFromCart", dataProviderClass = StaticDataProvider.class)
    public void test_RemoveFromCart(String venture) throws Exception {

        checkOutRemoveFromCart(venture,
                setText(venture).get("menuWiz"),
                setText(venture).get("categories"),
                setText(venture).get("filterWiz"),
                setText(venture).get("prodWiz")
        );
    }

    @Test(dataProvider = "getVenturesDataToTestEditPaymentMethod", dataProviderClass = StaticDataProvider.class)
    public void test_EditPaymentMethod(String venture) throws Exception {

        checkOutEditPaymentMethod(venture,
                setText(venture).get("menuWiz"),
                setText(venture).get("categories"),
                setText(venture).get("filterWiz"),
                setText(venture).get("prodWiz")
        );
    }

    // Staging only
    @Test(dataProvider = "getVenturesDataToTestCoupon", dataProviderClass = StaticDataProvider.class)
    public void test_Coupon(String venture, String coupon) throws Exception {

        checkOutCoupon(venture,
                setText(venture).get("menuWiz"),
                setText(venture).get("categories"),
                setText(venture).get("filterWiz"),
                setText(venture).get("prodWiz"),
                coupon);
    }

    @Test(dataProvider = "getVenturesDataToTestShareOrder", dataProviderClass = StaticDataProvider.class)
    public void test_ShareOrder(String venture) throws Exception {

        checkOutShareOrder(venture,
                setText(venture).get("menuWiz"),
                setText(venture).get("categories"),
                setText(venture).get("filterWiz"),
                setText(venture).get("prodWiz")
        );
    }

    // Staging only
    @Test(dataProvider = "getVenturesDataToTestSavedCreditCard", dataProviderClass = StaticDataProvider.class)
    public void test_SavedCreditCard(String venture) throws Exception {

        checkOutSavedCreditCard(venture,
                setText(venture).get("menuWiz"),
                setText(venture).get("categories"),
                setText(venture).get("filterWiz"),
                setText(venture).get("prodWiz")
        );
    }

    @Test(dataProvider = "getVenturesDataToTestNewAccount", dataProviderClass = StaticDataProvider.class)
    public void test_NewAccount(String venture) throws Exception {

        checkOutNewAccount(venture,
                setText(venture).get("menuWiz"),
                setText(venture).get("categories"),
                setText(venture).get("filterWiz"),
                setText(venture).get("prodWiz")
        );
    }

    @Test(dataProvider = "getVenturesDataToTestNewBillingAddress", dataProviderClass = StaticDataProvider.class)
    public void test_NewBillingAddress(String venture,
                                       String name,
                                       String address,
                                       String phoneNumber) throws Exception {

        checkoutNewBillingAddress(venture,
                setText(venture).get("menuWiz"),
                setText(venture).get("categories"),
                setText(venture).get("filterWiz"),
                setText(venture).get("prodWiz"),
                name,
                address,
                phoneNumber);
    }

    @Test(dataProvider = "getVenturesDataToTestBankTransferDifferentBillingAddress", dataProviderClass = StaticDataProvider.class)
    public void test_BankTransferEditBillingAddress(String venture,
                                                    int bankIndex,
                                                    String senderName,
                                                    String name,
                                                    String address,
                                                    String phoneNumber) throws Exception {

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
