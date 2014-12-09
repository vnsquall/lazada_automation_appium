package scenario;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import screenObjects.android_app.*;
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
        TopBar_Screen.click_HomeBtn();
        Thread.sleep(1000);

        // Find & Click on Wish list
        SideMenu_Screen.click_Menu(wishList);

        // Verify the Wish list is EMPTY
        find_TextView_Android(emptyWL);
        WishList_Screen.click_ContinueBrowsingBtn();

        // Verify Home Page is loaded
        Home_Screen.loaded();

        // Go to Categories & select random product
        TopBar_Screen.click_HomeBtn();
        Thread.sleep(1000);
        randomSelectProduct(categories, appPackage, filterWiz, prodWiz);

        // Add to Wish list
        ProductDetail_Screen.click_AddToWishListBtn();
        if (!isElementPresent(ProductDetail_Screen.OKBtn)) { // We need select size first

            selectorRandom(ProductDetail_Screen.chooseSizeBtn());
            ProductDetail_Screen.click_AddToWishListBtn();
        }

        // Verify the message appear:
        find_TextView_Android(addWL);
        ProductDetail_Screen.click_OKBtn(); //Click on OK button
    }

    protected void addToWishList () throws InterruptedException {

        // Add to Wish list
        if (hasSize()) { // We need select size first

            chooseSize();
        }
        ProductDetail_Screen.click_AddToWishListBtn();
        TopBar_Screen.click_MyCartBtn();
        ProductDetail_Screen.click_OKBtn();
    }

    /**
     * Random and select size clothes, shoes, ...
     */
    protected void chooseSize () throws InterruptedException {

        ProductDetail_Screen.click_ChooseSizeBtn();
        randClick(ProductDetail_Screen.sizes);
        Thread.sleep(2000);

    }

    protected void wishListDeleteProduct (String venture, String menuWiz, String wishList, String emptyWL,
                                          String categories, String filterWiz, String prodWiz, String addWL) throws InterruptedException {

        // Select venture
        Init_Screen.select_Country(venture, menuWiz);
        TopBar_Screen.click_HomeBtn();
        Thread.sleep(1000);

        // Add random product to WishList
        randomSelectProduct(categories, appPackage, filterWiz, prodWiz);
        addToWishList();

        // Go to WishList
        TopBar_Screen.click_HomeBtn();
        SideMenu_Screen.click_Menu(wishList);

        // Delete product from WishList
        WishList_Screen.click_DeleteBtn();

        // Verify the WishList is empty
        WebElement deleteBtn = null;

        try { // try to find Delete button
            deleteBtn = WishList_Screen.deleteBtn();
        } catch (org.openqa.selenium.NoSuchElementException e) {};

        Assert.assertNull(deleteBtn);
        Assert.assertTrue(WishList_Screen.noItemMessage().isDisplayed());
        Assert.assertEquals(emptyWL, WishList_Screen.noItemMessage().getText());
    }

    protected void wishListAddToCart (String venture, String menuWiz, String wishList, String emptyWL,
                                      String categories, String filterWiz, String prodWiz, String addWL) throws InterruptedException {

        // Select venture
        Init_Screen.select_Country(venture, menuWiz);
        TopBar_Screen.click_HomeBtn();
        Thread.sleep(1000);

        // Random and add product to WishList
        randomSelectProduct(categories, appPackage, filterWiz, prodWiz);
        String productName = ProductDetail_Screen.productName().getText();
        addToWishList();

        // Go to WishList
        TopBar_Screen.click_HomeBtn();
        SideMenu_Screen.click_Menu(wishList);

        // Add 1 product to Cart from WishList
        WishList_Screen.click_AddToCartBtn();
        WishList_Screen.click_OKBtn();

        // Verify product that product appears in myCart
        String myCardStr = Cart_Screen.productName().getText();
        Assert.assertTrue(myCardStr.contains(productName));
    }

    protected void wishListAddAllToCart (String venture, String menuWiz, String wishList, String emptyWL,
                                         String categories, String filterWiz, String prodWiz, String addWL) throws InterruptedException {

        // Select venture
        Init_Screen.select_Country(venture, menuWiz);
        TopBar_Screen.click_HomeBtn();
        Thread.sleep(1000);

        // Select random product - first time has wizard
        randomSelectProduct(categories, appPackage, filterWiz, prodWiz);
        addToWishList();

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
        TopBar_Screen.click_HomeBtn();
        SideMenu_Screen.click_Menu(wishList);

        // Add all product to Cart
        WishList_Screen.click_AddAllToCart();// Click Add all to Cart
        TopBar_Screen.click_MyCartBtn();// Click on Go to Cart

    }

    protected void wishListShareItem (String venture, String menuWiz, String wishList, String emptyWL,
                                      String categories, String filterWiz, String prodWiz, String addWL,
                                      String sharerAppName, String phoneNumber, String sharerAppPackage) throws InterruptedException {

        // Select venture
        Init_Screen.select_Country(venture, menuWiz);
        TopBar_Screen.click_HomeBtn();
        Thread.sleep(1000);

        // Select random product - first time has wizard
        randomSelectProduct(categories, appPackage, filterWiz, prodWiz);

        // Select the Application to share the Item: Bluetooth, Message, Email
        ProductDetail_Screen.click_ShareBtn();
        ProductDetail_Screen.click_AppSharer(sharerAppName);

    }

    /**
     * Add 1 randomly product to WishList - No clicking on menu wizard
     */
    public void addProductToWishListNoWizard(String venture, String menuWiz, String wishList, String emptyWL,

                                             String categories, String filterWiz, String prodWiz, String addWL, String appPackage) throws InterruptedException {

        TopBar_Screen.click_HomeBtn();
        text_exact(categories).click();

        // Random selection Categories
        randClick(By.id(appPackage + ":id/category_name"));

        // Random selection sub-Categories
        randClick(By.id(appPackage + ":id/text"));

        // Get back to the Main Screen for viewing the product
        find(appPackage + ":id/general_container").click();
        find(appPackage + ":id/general_container").click();

        // Add product to WishList
        addToWishList();

    }
}
