package scenario;

import org.openqa.selenium.By;
import util.AppiumSetupTest;

import static util.Helper.*;

/**
 * Created by lazhcm10136 on 10/2/14.
 */
public class WishListScenario extends AppiumSetupTest {

    protected void wishListNotLogin(String venture, String menuWiz, String wishList, String emptyWL,
                                    String categories, String filterWiz, String prodWiz, String addWL) throws InterruptedException {

        selectVenture(venture, menuWiz);
        find(appPackage + ":id/abs__home").click();
        Thread.sleep(1000);

        //Find & Click on Wishlist
        driver.findElement(By.xpath("//android.widget.TextView[@resource-id='" + appPackage + ":id/component_text' and @text='" + wishList + "']")).click();
        //Verify the Wishlist is EMPTY
        find_xpath_forText(appPackage, ":id/wishlist_no_items_text", emptyWL);
        find(appPackage + ":id/wishlist_no_items_bt_continue").click();
        //Verify Home Page is loaded
        isElementPresent(By.id(appPackage + ":id/product_features_title"));

        //Go to Categories & select random product
        find(appPackage + ":id/abs__home").click();
        Thread.sleep(1000);
        randomSelectProduct(categories, appPackage, filterWiz, prodWiz);
        find(appPackage + ":id/btn_wishlist").click(); //Add to Wishlist

        //Verify the message appear:
        find_xpath_forText(appPackage, ":id/items_count", addWL);
        find(appPackage + ":id/button1").click(); //Click on OK button


    }
}
