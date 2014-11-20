package scenario;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import screenObjects.android_app.Init_Screen;
import util.AppiumSetupTest;

import java.util.Random;

import static util.Helper.*;

/**
 * Created by lazhcm10136 on 10/2/14.
 */
public class WishListScenario extends AppiumSetupTest {

    protected void wishListNotLogin(String venture, String menuWiz, String wishList, String emptyWL,
                                    String categories, String filterWiz, String prodWiz, String addWL) throws InterruptedException {

        Init_Screen.select_Country(venture, menuWiz);
        findElement("resourceID", "abs__home").click();
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

        // Select size if need
        if (isElementPresent(By.id(appPackage + ":id/product_variant_button"))) {
            selectorRandom(By.id(appPackage + ":id/product_variant_button"));

        }
        find(appPackage + ":id/btn_wishlist").click(); //Add to Wishlist

        //Verify the message appear:
        find_xpath_forText(appPackage, ":id/items_count", addWL);
        find(appPackage + ":id/button1").click(); //Click on OK button
    }

    protected void wishListDeleteProduct (String venture, String menuWiz, String wishList, String emptyWL,
                                          String categories, String filterWiz, String prodWiz, String addWL) throws InterruptedException {

        // Select venture
        Init_Screen.select_Country(venture, menuWiz);
        findElement("resourceID", "abs__home").click();
        Thread.sleep(1000);

        // Add random product to WishList
        randomSelectProduct(categories, appPackage, filterWiz, prodWiz);System.out.println(findElement("resourceID", "btn_wishlist").getText());
        findElement("resourceID", "btn_wishlist").click();//  Click on Add to wishList
        findElement("resourceID", "button1").click();// Click on OK button

        // Go to WishList
        findElement("resourceID", "abs__home").click();
        driver.findElementByAndroidUIAutomator("UiSelector().textContains(\""+wishList+"\")").click();

        // Delete product from WishList
        findElement("resourceID", "wishlist_item_bt_delete").click();// Click on OK button

        // Verify the WishList is empty
        WebElement deteleBtn = null;
        try { // try to find Delete button
//            deteleBtn = driver.findElementByAndroidUIAutomator("UiSelector().resourceId(\""+appPackage+":id/wishlist_item_bt_delete\")");
            deteleBtn = findElement("resourceID", "wishlist_item_bt_delete");
        }catch (org.openqa.selenium.NoSuchElementException e) {};

        Assert.assertNull(deteleBtn);
        Assert.assertTrue(findElement("resourceID", "wishlist_no_items_text").isDisplayed());
        Assert.assertEquals(emptyWL, findElement("resourceID", "wishlist_no_items_text").getText());
    }

    protected void wishListAddToCart (String venture, String menuWiz, String wishList, String emptyWL,
                                      String categories, String filterWiz, String prodWiz, String addWL) throws InterruptedException {

        // Select venture
        Init_Screen.select_Country(venture, menuWiz);
        findElement("resourceID", "abs__home").click();
        Thread.sleep(1000);

        // Random and add product to WishList
        randomSelectProduct(categories, appPackage, filterWiz, prodWiz);
        String productName = findElement("resourceID", "product_name").getText();
        findElement("resourceID", "btn_wishlist").click();
        findElement("resourceID", "button1").click();

        // Go to WishList
        findElement("resourceID", "abs__home").click();
        findElement("textcontains", wishList).click();

        // Add 1 product to Cart from WishList
        findElement("resourceID", "wishlist_item_bt_add").click();
        findElement("resourceID", "button1").click();

        // Verify product that product appears in myCart
        String myCardStr = findElement(
                "resourceID",
                "item_name"
        )
                .getText();
        System.out.println("myCardStr>>>> "+myCardStr);
        System.out.println("productName>>>> "+productName);

        Assert.assertTrue(myCardStr.contains(productName));
    }

    protected void wishListAddAllToCart (String venture, String menuWiz, String wishList, String emptyWL,
                                         String categories, String filterWiz, String prodWiz, String addWL) throws InterruptedException {

        // Select venture
        Init_Screen.select_Country(venture, menuWiz);
        findElement("resourceID", "abs__home").click();
        Thread.sleep(1000);

        // Select random product - first time has wizard
        randomSelectProduct(categories, appPackage, filterWiz, prodWiz);
        findElement("resourceID", "btn_wishlist").click();
        findElement("resourceID", "button1").click();

        Random ran = new Random();
        int numberOfProduct = ran.nextInt(10);
        for (int i = 0; i < numberOfProduct; i++) {

            addProductToWishListNoWizard(
                    venture,
                    menuWiz,
                    wishList,
                    emptyWL,
                    categories,
                    filterWiz,
                    prodWiz,
                    addWL,
                    appPackage);
        }

        // Go to WishList
        findElement("resourceID", "abs__home").click();
        findElement("textcontains", wishList).click();
        // Add all product to Cart
        findElement("resourceID", "wishlist_bt_add_all").click();// Click Add all to Cart
        findElement("resourceID", "cart_count").click();// Click on Go to Cart

    }

    protected void wishListShareItem (String venture, String menuWiz, String wishList, String emptyWL,
                                      String categories, String filterWiz, String prodWiz, String addWL,
                                      String sharerAppName, String phoneNumber, String sharerAppPackage) throws InterruptedException {

        // Select venture
        Init_Screen.select_Country(venture, menuWiz);
        findElement("resourceID", "abs__home").click();
        Thread.sleep(1000);

        // Select random product - first time has wizard
        randomSelectProduct(categories, appPackage, filterWiz, prodWiz);

        // Select the Application to share the Item: Bluetooth, Message, Email
        findElement("resourceID", "btn_share").click();
        findElement("text", sharerAppName).click();

        if (isElementPresent(By.id("android:id/button_once"))) { // If the Just once button still appears, push it
            findElement("resourceID", "button_once").click();
        }

        // Enter phone number
        findElement("resourceID", "recipients_editor").sendKeys(phoneNumber);
        findElement("resourceID", "send_button_sms").click();// Click Send message

        // Get back Lazada Application
        driver.navigate().back();

    }

    /**
     * Add 1 randomly product to WishList
     *
     * @param venture
     * @param menuWiz
     * @param wishList
     * @param emptyWL
     * @param categories
     * @param filterWiz
     * @param prodWiz
     * @param addWL
     * @param appPackage
     */
    public static void addProductToWishListNoWizard(String venture, String menuWiz, String wishList, String emptyWL,

                                                    String categories, String filterWiz, String prodWiz, String addWL, String appPackage) {

        findElement("resourceID", "abs__home").click();
        text_exact(categories).click();

        // Random selection Categories
        randClick(By.id(appPackage + ":id/category_name"));

        // Random selection sub-Categories
        randClick(By.id(appPackage + ":id/text"));

        // Get back to the Main Screen for viewing the product
        find(appPackage + ":id/general_container").click();
        find(appPackage + ":id/general_container").click();

        // Add product to WishList
        findElement("resourceID", "btn_wishlist").click();
        findElement("resourceID", "button1").click();


    }
}
