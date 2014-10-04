package scenario;

import org.openqa.selenium.By;
import util.AppiumSetupTest;
import static util.Helper.*;

/**
 * Created by lazhcm10136 on 10/2/14.
 */
public class WishListScenario extends AppiumSetupTest {
    protected void wishListNotLogin (String venture, String menuWiz, String categories, String filterWiz, String prodWiz) throws InterruptedException {
        selectVenture(venture, menuWiz);
        find(appPackage + ":id/abs__home").click();
        Thread.sleep(1000);

        //Find & Click on Wishlist
        driver.findElement(By.xpath("//android.widget.TextView[@resource-id='"+appPackage+":id/component_text' and @text='Wishlist']")).click();
        //Verify the Wishlist is EMPTY
        find_xpath_forText(appPackage, ":id/wishlist_no_items_text", "You have no items in your wishlist");
        find(appPackage + ":id/wishlist_no_items_bt_continue").click();
        //Verify Home Page is loaded
        isElementPresent(By.id("pt.rocket.lazada.dev:id/product_features_title"));

        //Go to Categories & select random product
        find(appPackage + ":id/abs__home").click();
        Thread.sleep(1000);
        randomSelectProduct(categories, appPackage, filterWiz, prodWiz);


    }
}
