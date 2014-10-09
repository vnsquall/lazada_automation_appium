package scenario;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import util.AppiumSetupTest;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;

import static util.Helper.*;

/**
 * Created by lazhcm10136 on 10/2/14.
 */
public class WishListScenario extends AppiumSetupTest {

    protected void wishListNotLogin (String venture, String menuWiz, String wishList, String emptyWL,
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

    protected void wishListDeleteProduct (String venture, String menuWiz, String wishList, String emptyWL,

                                     String categories, String filterWiz, String prodWiz, String addWL) throws InterruptedException {


        // Select venture
        driver.findElement(By.xpath(("//android.widget.TextView[contains(@text, '" + venture + "')]"))).click();
        Thread.sleep(7000);
        driver.findElementByAndroidUIAutomator("UiSelector().text(\""+menuWiz+"\")").click();
        Thread.sleep(2000);
        // Select Category
        driver.findElementByAndroidUIAutomator("UiSelector().resourceId(\""+appPackage+":id/abs__home\")").click();
        driver.findElementByAndroidUIAutomator("UiSelector().text(\""+categories+"\")").click();
        driver.findElementByAndroidUIAutomator("UiSelector().text(\"Cameras\")").click();
        driver.findElementByAndroidUIAutomator("UiSelector().text(\""+filterWiz+"\")").click();
        find(appPackage + ":id/middle_productslist_list").click();

        // Random product to add to WishList
        List<WebElement> arrProduct = driver.findElementsByAndroidUIAutomator("UiSelector().resourceId(\""+appPackage+":id/image_view\")");
        Random ran = new Random();
        int productIndex  = ran.nextInt(arrProduct.size());
        arrProduct.get(productIndex).click();
        driver.findElementByAndroidUIAutomator("UiSelector().text(\""+prodWiz+"\")").click();
        find(appPackage + ":id/btn_wishlist").click();
        find(appPackage + ":id/button1").click();
        // Go to WishList
        driver.findElementByAndroidUIAutomator("UiSelector().resourceId(\""+appPackage+":id/abs__home\")").click();
        driver.findElementByAndroidUIAutomator("UiSelector().textContains(\""+wishList+"\")").click();
        // Delete product from WishList
        find(appPackage + ":id/wishlist_item_bt_delete").click();
        // Verify the WishList is empty
        WebElement deteleBtn = null;
        try {
//            deteleBtn = find(appPackage + ":id/");
            deteleBtn = driver.findElementByAndroidUIAutomator("UiSelector().resourceId(\""+appPackage+":id/wishlist_item_bt_delete\")");
        }catch (org.openqa.selenium.NoSuchElementException e) {};

        Assert.assertNull(deteleBtn);
    }
}
