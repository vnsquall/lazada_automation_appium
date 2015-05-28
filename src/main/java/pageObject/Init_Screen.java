package pageObject;


import static util.Helper.*;

public class Init_Screen {

    public static String countryName = "text::[country]";



    public static void select_Country(String country, String menuWiz) throws InterruptedException {

        findElement(split(countryName)[0], country).click();
        if (country.equals("Thailand")) { // Special case

            find_ButtonText_Android("English").click();
        }

        // Click on the Menu wizard
        click_MenuWizard(menuWiz);

    }

    public static void click_MenuWizard(String menuWiz) throws InterruptedException {

        tapOnElement(find_TextView_Android(menuWiz));
        Thread.sleep(6000);
        while (isPageContains(menuWiz)) { // Special case: Make sure the Menu wizard have been closed
            tapOnElement(find_TextView_Android(menuWiz));
        }
        Thread.sleep(2000);
    }
}
