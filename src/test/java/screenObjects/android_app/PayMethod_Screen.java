package screenObjects.android_app;

import org.openqa.selenium.WebElement;

import static util.Helper.findByUISelector;
import static util.Helper.split;

/**
 * Created by anhpham on 17/11/2014.
 */
public class PayMethod_Screen {

    public static String radioCODDisabled = "xpath:://*[@class='payment-method-option radio'and@value='CashOnDelivery'and@disabled='disabled']";
    public static String labelCOD = "xpath:://label[@for='cashondelivery']";
    public static String labelCreditCard = "xpath:://label[@class='creditcards']";
    public static String continueButton = "xpath:://button[@class='orange-button']";
    public static String creditNumber = "xpath:://*[@id='PaymentMethodForm_parameter_cc_number']";
    public static String creditCustomerName = "xpath:://*[@id='orange-PaymentMethodForm_parameter_cc_holder']";
    public static String creditSecurityCode = "xpath:://*[@id='PaymentMethodForm_parameter_cc_security_code']";
    public static String creditMonth = "xpath:://*[@id='PaymentMethodForm_parameter_cc_exp_month']";
    public static String creditYear = "xpath:://*[@id='PaymentMethodForm_parameter_cc_exp_year']";
    public static String labelBankTransfer = "xpath:://label[@for='manualbanktransferid']";
    public static String bankNames = "xpath:://*[@name='PaymentMethodForm[parameter][bankNamePrimary]']";
    public static String bankTransferSenderName = "xpath:://*[@id='PaymentMethodForm_parameter_senderName']";


    public static void click_CashOnDeliveryRadio() {

        findByUISelector(split(labelCOD)[0], split(labelCOD)[1]).click();
    }

    public static void click_ContinueBtn() {

        findByUISelector(split(continueButton)[0], split(continueButton)[1]).click();
    }

    public static void click_CreditCardRadio() {

        findByUISelector(split(labelCreditCard)[0], split(labelCreditCard)[1]).click();
    }

    public static void input_CreditNumber(String creditNumber) {

        findByUISelector(split(PayMethod_Screen.creditNumber)[0], split(PayMethod_Screen.creditNumber)[1]).click();
        findByUISelector(split(PayMethod_Screen.creditNumber)[0], split(PayMethod_Screen.creditNumber)[1]).sendKeys(creditNumber);
    }

    public static void input_CreditName(String customerName) {

        findByUISelector(split(creditCustomerName)[0], split(creditCustomerName)[1]).click();
        findByUISelector(split(creditCustomerName)[0], split(creditCustomerName)[1]).sendKeys(customerName);
    }

    public static void input_CreditSecurityCode(String securityCode) {

        findByUISelector(split(creditSecurityCode)[0], split(creditSecurityCode)[1]).click();
        findByUISelector(split(creditSecurityCode)[0], split(creditSecurityCode)[1]).sendKeys(securityCode);
    }

    public static WebElement creditCardMonthCbx () {

        return findByUISelector(split(creditMonth)[0], split(creditMonth)[1]);
    }

    public static WebElement creditCardYearCbx () {

        return findByUISelector(split(creditYear)[0], split(creditYear)[1]);
    }

    public static void click_BankTransferRadio () {

        findByUISelector(split(labelBankTransfer)[0], split(labelBankTransfer)[1]).click();
    }

    public static void input_SenderName(String senderName) {

        findByUISelector(split(bankTransferSenderName)[0], split(bankTransferSenderName)[1]).sendKeys(senderName);
    }


}
