package scenario;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import util.AppiumSetupTest;
import util.UISelectorType;

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
        selectVenture(venture, menuWiz);
        find(appPackage + ":id/abs__home").click();
        Thread.sleep(1000);

        // Select random product
        randomSelectProduct(categories, appPackage, filterWiz, prodWiz);
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
            deteleBtn = driver.findElementByAndroidUIAutomator("UiSelector().resourceId(\""+appPackage+":id/wishlist_item_bt_delete\")");
        }catch (org.openqa.selenium.NoSuchElementException e) {};

        Assert.assertNull(deteleBtn);
    }

    protected void wishListAddToCart (String venture, String menuWiz, String wishList, String emptyWL,

                                          String categories, String filterWiz, String prodWiz, String addWL) throws InterruptedException {

        // Select venture
        selectVenture(venture, menuWiz);
        findByUISelectorType("resourceID","abs__home", appPackage).click();
        Thread.sleep(1000);

        // Random and add product to WishList
        randomSelectProduct(categories, appPackage, filterWiz, prodWiz);
        String productName = findByUISelectorType("resourceID", "product_name",appPackage).getText();
        findByUISelectorType("resourceID", "btn_wishlist",appPackage).click();
        findByUISelectorType("resourceID", "button1",appPackage).click();

        // Go to WishList
        findByUISelectorType("resourceID","abs__home", appPackage).click();
        findByUISelectorType("textcontains",wishList, appPackage).click();
        // Add 1 product to Cart from WishList
        findByUISelectorType("resourceID","wishlist_item_bt_add", appPackage).click();
        findByUISelectorType("resourceID","button1", appPackage).click();
        // Verify product that product appears in myCart
        String myCardStr = findByUISelectorType(
                "resourceID",
                "item_name",
                appPackage)
                .getText();
        System.out.println("myCardStr>>>> "+myCardStr);
        System.out.println("productName>>>> "+productName);

        Assert.assertTrue(myCardStr.contains(productName));
    }

    protected void wishListAddAllToCart (String venture, String menuWiz, String wishList, String emptyWL,

                                      String categories, String filterWiz, String prodWiz, String addWL) throws InterruptedException {

        // Select venture
        selectVenture(venture, menuWiz);
        find(appPackage + ":id/abs__home").click();
        Thread.sleep(1000);

        // Select random product
        randomSelectProduct(categories, appPackage, filterWiz, prodWiz);
        find(appPackage + ":id/btn_wishlist").click();
        find(appPackage + ":id/button1").click();


        for (int i = 0; i < 3; i++) {

            addProductToWishList( venture,  menuWiz,  wishList,  emptyWL,

                     categories,  filterWiz,  prodWiz,  addWL);
        }

        // Go to WishList
        driver.findElementByAndroidUIAutomator("UiSelector().resourceId(\""+appPackage+":id/abs__home\")").click();
        driver.findElementByAndroidUIAutomator("UiSelector().textContains(\""+wishList+"\")").click();
        // Add all product to Cart
        find(appPackage + ":id/wishlist_bt_add_all").click();
        find(appPackage + ":id/cart_count").click();

    }

    public void addProductToWishList (String venture, String menuWiz, String wishList, String emptyWL,

                                      String categories, String filterWiz, String prodWiz, String addWL) {
        // Select Category
        driver.findElementByAndroidUIAutomator("UiSelector().resourceId(\""+appPackage+":id/abs__home\")").click();
        driver.findElementByAndroidUIAutomator("UiSelector().text(\""+categories+"\")").click();
        driver.findElementByAndroidUIAutomator("UiSelector().text(\"Cameras\")").click();
        find(appPackage + ":id/middle_productslist_list").click();

        // Random product to add to WishList
        List<WebElement> arrProduct = driver.findElementsByAndroidUIAutomator("UiSelector().resourceId(\""+appPackage+":id/image_view\")");
        Random ran = new Random();
        int productIndex  = ran.nextInt(arrProduct.size());
        arrProduct.get(productIndex).click();
        find(appPackage + ":id/btn_wishlist").click();
        find(appPackage + ":id/button1").click();
    }

}
