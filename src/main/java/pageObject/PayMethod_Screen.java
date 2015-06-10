package pageObject;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;

public class PayMethod_Screen extends CommonPageObject{
    
    public PayMethod_Screen(AndroidDriver driver) {
        super(driver);
    }

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


    public void click_CashOnDeliveryRadio() {

        getHelper().findElement(labelCOD).click();
    }

    public void click_ContinueBtn() {

        getHelper().findElement(continueButton).click();
    }

    public void click_CreditCardRadio() {

        getHelper().findElement(labelCreditCard).click();
    }

    public void click_PaypalRadio() {

        getHelper().findElement(labelPaypal).click();
    }

    public void input_CreditNumber(String _creditNumber) {

        getHelper().findElement(creditNumber).click();
        getHelper().findElement(creditNumber).sendKeys(_creditNumber);
    }

    public void input_CreditName(String customerName) {

        getHelper().findElement(creditCustomerName).click();
        getHelper().findElement(creditCustomerName).sendKeys(customerName);
    }

    public void input_CreditSecurityCode(String securityCode) {

        getHelper().findElement(creditSecurityCode).click();
        getHelper().findElement(creditSecurityCode).sendKeys(securityCode);
    }

    public WebElement creditCardMonthCbx () {

        return getHelper().findElement(creditMonth);
    }

    public WebElement creditCardYearCbx () {

        return getHelper().findElement(creditYear);
    }

    public void click_BankTransferRadio () {

        getHelper().findElement(labelBankTransfer).click();
    }

    public void input_SenderName(String senderName) {

        getHelper().findElement(bankTransferSenderName).sendKeys(senderName);
    }

}
