package screenObjects.android_app;

import org.openqa.selenium.WebElement;
import static util.Helper.findElement;
import static util.Helper.split;

/**
 * Created by anhpham on 17/11/2014.
 */
public class PayMethod_Screen {

    public static String radioCODDisabled = "xpath:://*[@class='payment-method-option radio'and@value='CashOnDelivery'and@disabled='disabled']";
    public static String labelCOD = "xpath:://label[@for='cashondelivery']";
    public static String labelCreditCard = "xpath:://label[@class='creditcards']";
    public static String labelPaypal = "xpath:://label[@for='paypal']";
    public static String labelBankTransfer = "xpath:://label[@for='manualbanktransferid']";
    public static String creditNumber = "xpath:://*[@id='PaymentMethodForm_parameter_cc_number']";
    public static String creditCustomerName = "xpath:://*[@id='PaymentMethodForm_parameter_cc_holder']";
    public static String creditSecurityCode = "xpath:://*[@id='PaymentMethodForm_parameter_cc_security_code']";
    public static String creditMonth = "xpath:://*[@id='PaymentMethodForm_parameter_cc_exp_month']";
    public static String creditYear = "xpath:://*[@id='PaymentMethodForm_parameter_cc_exp_year']";
    public static String bankNames = "xpath:://*[@name='PaymentMethodForm[parameter][bankNamePrimary]']";
    public static String bankTransferSenderName = "xpath:://*[@id='PaymentMethodForm_parameter_senderName']";
    public static String continueButton = "xpath:://button[@class='orange-button']";
    public static String paymentMethods = "xpath:://*[starts-with(@class,'payment-methods')]";
    public static String creditCards = "xpath:://*[starts-with(@class,'payment-methods')]"; // Saved credit card



    public static void click_CashOnDeliveryRadio() {

        findElement(split(labelCOD)[0], split(labelCOD)[1]).click();
    }

    public static void click_ContinueBtn() {

        findElement(continueButton).click();
    }

    public static void click_CreditCardRadio() {

        findElement(labelCreditCard).click();
    }

    public static void click_PaypalRadio() {

        findElement(labelPaypal).click();
    }

    public static void input_CreditNumber(String _creditNumber) {

        findElement(creditNumber).click();
        findElement(creditNumber).sendKeys(_creditNumber);
    }

    public static void input_CreditName(String customerName) {

        findElement(creditCustomerName).click();
        findElement(creditCustomerName).sendKeys(customerName);
    }

    public static void input_CreditSecurityCode(String securityCode) {

        findElement(creditSecurityCode).click();
        findElement(creditSecurityCode).sendKeys(securityCode);
    }

    public static WebElement creditCardMonthCbx () {

        return findElement(creditMonth);
    }

    public static WebElement creditCardYearCbx () {

        return findElement(creditYear);
    }

    public static void click_BankTransferRadio () {

        findElement(labelBankTransfer).click();
    }

    public static void input_SenderName(String senderName) {

        findElement(bankTransferSenderName).sendKeys(senderName);
    }




}
