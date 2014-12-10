package testsuite;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import scenario.CheckOutScenario;
/**
 * Created by anhpham on 10/12/2014.
 */
public class StaticDataProvider {

    @DataProvider
    Object[][] getVenturesDataToTestCashOnDelivery() {
        return new Object[][]{
//                {"Singapore"},
//                {"Philippines"},
//                {"Indonesia"},
                {"Malaysia"},
//                {"Vietnam"},
//                {"Thailand"}
        };
    }

    //4000000000000002 737 06/2015 CC for using the OTP function
    @DataProvider
    Object[][] getVenturesDataToTestCreditCardInvalid() {
        return new Object[][]{
//                {"Singapore", "4400123456784444", "Mr Test", "123"},
                {"Malaysia", "4400123456784011", "dajdiaw", "777"},
//                {"Philippines", "5500005555555559", "Mr Test", "123"},
//                {"Indonesia", "4400123456784011", "Mr Test", "123"},
//                {"Vietnam", "4400123456784011", "hduaiwud", "222"},
//                {"Thailand", "4400123456784011", "Mr Test", "123"}

        };
    }

    //4000000000000002 737 06/2015 CC for using the OTP function
    @DataProvider
    Object[][] getVenturesDataToTestCreditCardInvalidGuest() {
        return new Object[][]{
//                {"Singapore", "4400123456784444", "Mr Test", "123"},
//                {"Malaysia", "4400123456784011", "dajdiaw", "777"},
//                {"Philippines", "5500005555555559", "Mr Test", "123"},
//                {"Indonesia", "4400123456784011", "Mr Test", "123"},
                {"Vietnam", "4400123456784011", "hduaiwud", "222", "djaw", "jhaojwd", "7897979797"},
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
//                {"Singapore"},
//                {"Thailand"},
                {"Vietnam"},
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
//                {"Singapore", "jdalwd", "dhakdhawd", "99999393939"},
//                {"Philippines", "jack", "Bom bai", "8788332222"},
//                {"Indonesia", "dhakwd", "daddad", "443235556544"},
                {"Malaysia", "jdjdjd", "jdjdda", "42424242424"},
//                {"Vietnam", "nicky", "LA", "38877737373" },
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
                {"Philippines"},
                {"Indonesia"},
                {"Malaysia"},
                {"Vietnam"},
                {"Thailand"}
        };
    }
}
