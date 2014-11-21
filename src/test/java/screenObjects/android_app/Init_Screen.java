package screenObjects.android_app;


import static util.Helper.*;

/**
 * Created by anhpham on 17/11/2014.
 */
public class Init_Screen {

    private static String countryName = "text::[country]";



    public static void select_Country(String country, String menuWiz) throws InterruptedException {

        findElement(split(countryName)[0], country).click();
        if (country.equals("Thailand")) { // Special case

            find_ButtonText_Android("English").click();
            find_ButtonText_Android("English").click();
        }
        Thread.sleep(6000);
        find_TextView_Android(menuWiz).click();
        Thread.sleep(2000);
    }
}
