package screenObjects.android_app;


import static util.Helper.findElement;
import static util.Helper.split;

/**
 * Created by anhpham on 17/11/2014.
 */
public class Init_Screen {

    private static String countryName = "text::[country]";
    private static String wizardText = "text::[menuWiz]";



    public static void select_Country(String country, String menuWiz) throws InterruptedException {

        findElement(split(countryName)[0], country).click();
        if (country.equals("Thailand")) { // Special case

            findElement(split(countryName)[0], "English").click();
            findElement(split(countryName)[0], "English").click();
        }
        Thread.sleep(6000);
        findElement(split(wizardText)[0], menuWiz).click();
        Thread.sleep(2000);
    }
}
