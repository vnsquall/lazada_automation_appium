package pageObject;

import io.appium.java_client.android.AndroidDriver;

public class Init_Screen extends CommonPageObject {

    public Init_Screen (AndroidDriver driver){
        super(driver);
    }

    public static String countryName = "text::[country]";

    public  void select_Country(String country, String menuWiz) throws InterruptedException {

        getHelper().findElement(getHelper().split(countryName)[0], country).click();
        if (country.equals("Thailand")) { // Special case

            getHelper().find_ButtonText_Android("English").click();
        }

        // Click on the Menu wizard
        click_MenuWizard(menuWiz);

    }

    public void click_MenuWizard(String menuWiz) throws InterruptedException {

        getHelper().tapOnElement(getHelper().find_TextView_Android(menuWiz));
        Thread.sleep(6000);
        while (getHelper().isPageContains(menuWiz)) { // Special case: Make sure the Menu wizard have been closed
            getHelper().tapOnElement(getHelper().find_TextView_Android(menuWiz));
        }
        Thread.sleep(2000);
    }
}
