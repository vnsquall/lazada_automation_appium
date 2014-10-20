package scenario;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import util.AppiumSetupTest;

import java.util.Random;

import static util.Helper.*;

/**
 * Created by lazhcm10136 on 10/2/14.
 */
public class WishListScenario extends AppiumSetupTest {

    protected void wishListNotLogin(String venture, String menuWiz, String wishList, String emptyWL,
                                    String categories, String filterWiz, String prodWiz, String addWL) throws InterruptedException {

        selectVenture(venture, menuWiz);
        findByUISelector("resourceID", "abs__home", appPackage).click();
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
        findByUISelector("resourceID", "abs__home", appPackage).click();
        Thread.sleep(1000);

        // Add random product to WishList
        randomSelectProduct(categories, appPackage, filterWiz, prodWiz);System.out.println(findByUISelector("resourceID", "btn_wishlist", appPackage).getText());
        findByUISelector("resourceID", "btn_wishlist", appPackage).click();//  Click on Add to wishList
        findByUISelector("resourceID", "button1", appPackage).click();// Click on OK button

        // Go to WishList
        findByUISelector("resourceID", "abs__home", appPackage).click();
        driver.findElementByAndroidUIAutomator("UiSelector().textContains(\""+wishList+"\")").click();

        // Delete product from WishList
        findByUISelector("resourceID", "wishlist_item_bt_delete", appPackage).click();// Click on OK button

        // Verify the WishList is empty
        WebElement deteleBtn = null;
        try { // try to find Delete button
//            deteleBtn = driver.findElementByAndroidUIAutomator("UiSelector().resourceId(\""+appPackage+":id/wishlist_item_bt_delete\")");
            deteleBtn = findByUISelector("resourceID", "wishlist_item_bt_delete", appPackage);
        }catch (org.openqa.selenium.NoSuchElementException e) {};

        Assert.assertNull(deteleBtn);
        Assert.assertTrue(findByUISelector("resourceID", "wishlist_no_items_text",appPackage).isDisplayed());
        Assert.assertEquals(emptyWL, findByUISelector("resourceID", "wishlist_no_items_text", appPackage).getText());
    }

    protected void wishListAddToCart (String venture, String menuWiz, String wishList, String emptyWL,
                                      String categories, String filterWiz, String prodWiz, String addWL) throws InterruptedException {

        // Select venture
        selectVenture(venture, menuWiz);
        findByUISelector("resourceID", "abs__home", appPackage).click();
        Thread.sleep(1000);

        // Random and add product to WishList
        randomSelectProduct(categories, appPackage, filterWiz, prodWiz);
        String productName = findByUISelector("resourceID", "product_name", appPackage).getText();
        findByUISelector("resourceID", "btn_wishlist", appPackage).click();
        findByUISelector("resourceID", "button1", appPackage).click();

        // Go to WishList
        findByUISelector("resourceID", "abs__home", appPackage).click();
        findByUISelector("textcontains", wishList, appPackage).click();

        // Add 1 product to Cart from WishList
        findByUISelector("resourceID", "wishlist_item_bt_add", appPackage).click();
        findByUISelector("resourceID", "button1", appPackage).click();

        // Verify product that product appears in myCart
        String myCardStr = findByUISelector(
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
        findByUISelector("resourceID", "abs__home", appPackage).click();
        Thread.sleep(1000);

        // Select random product - first time has wizard
        randomSelectProduct(categories, appPackage, filterWiz, prodWiz);
        findByUISelector("resourceID", "btn_wishlist", appPackage).click();
        findByUISelector("resourceID", "button1", appPackage).click();

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
        findByUISelector("resourceID", "abs__home", appPackage).click();
        findByUISelector("textcontains", wishList, appPackage).click();
        // Add all product to Cart
        findByUISelector("resourceID", "wishlist_bt_add_all", appPackage).click();// Click Add all to Cart
        findByUISelector("resourceID", "cart_count", appPackage).click();// Click on Go to Cart

    }

    protected void wishListShareItem (String venture, String menuWiz, String wishList, String emptyWL,
                                      String categories, String filterWiz, String prodWiz, String addWL,
                                      String sharerAppName, String phoneNumber, String sharerAppPackage) throws InterruptedException {

        // Select venture
        selectVenture(venture, menuWiz);
        findByUISelector("resourceID", "abs__home", appPackage).click();
        Thread.sleep(1000);

        // Select random product - first time has wizard
        randomSelectProduct(categories, appPackage, filterWiz, prodWiz);

        // Select the Application to share the Item: Bluetooth, Message, Email
        findByUISelector("resourceID", "btn_share", appPackage).click();
        findByUISelector("text", sharerAppName, appPackage).click();

        if (isElementPresent(By.id("android:id/button_once"))) { // If the Just once button still appears, push it
            findByUISelector("resourceID", "button_once", "android").click();
        }

        // Enter phone number
        findByUISelector("resourceID", "recipients_editor", sharerAppPackage).sendKeys(phoneNumber);
        findByUISelector("resourceID", "send_button_sms", sharerAppPackage).click();// Click Send message

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

        findByUISelector("resourceID", "abs__home", appPackage).click();
        text_exact(categories).click();

        // Random selection Categories
        randClick(By.id(appPackage + ":id/category_name"));

        // Random selection sub-Categories
        randClick(By.id(appPackage + ":id/text"));

        // Get back to the Main Screen for viewing the product
        find(appPackage + ":id/general_container").click();
        find(appPackage + ":id/general_container").click();

        // Add product to WishList
        findByUISelector("resourceID", "btn_wishlist", appPackage).click();
        findByUISelector("resourceID", "button1", appPackage).click();


    }
}
