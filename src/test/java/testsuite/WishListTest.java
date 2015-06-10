package testsuite;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObject.*;
import util.AppiumSetupTest;
import java.util.NoSuchElementException;
import java.util.Random;
import static util.VentureText.setText;

public class WishListTest extends AppiumSetupTest {

    private Cart_Screen cart_screen;
    private Home_Screen home_screen;
    private Init_Screen init_screen;
    private ProductDetail_Screen productDetail_screen;
    private SideMenu_Screen sideMenu_screen;
    private TopBar_Screen topBar_screen;
    private WishList_Screen wishList_screen;

    @Override
    @BeforeMethod
    public void setUp() throws Exception {
        super.setUp();
        cart_screen = new Cart_Screen(driver);
        home_screen = new Home_Screen(driver);
        init_screen = new Init_Screen(driver);
        productDetail_screen = new ProductDetail_Screen(driver);
        sideMenu_screen = new SideMenu_Screen(driver);
        topBar_screen = new TopBar_Screen(driver);
        wishList_screen = new WishList_Screen(driver);
    }

    @DataProvider
    Object[][] getVenturesDataToTest() {
        return new Object[][]{
                {"Singapore"}
        };
    }

    @DataProvider
    Object[][] getDataToTest_WishListShareItem() {
        return new Object[][]{
                {"Malaysia", "Messaging", "0933081162", "com.android.mms"},
                {"Singapore", "Messaging", "0933081162", "com.android.mms"}
//                {"Philippines"}
//                {"Indonesia"},
//                {"Vietnam"}
        };
    }


    @Test(dataProvider = "getVenturesDataToTest")
    public void test_WishListNotLogin(String venture) throws Exception {
        String menuWiz = setText(venture).get("menuWiz");
        String categories = setText(venture).get("categories");
        String filterWiz = setText(venture).get("filterWiz");
        String prodWiz = setText(venture).get("prodWiz");
        String wishList = setText(venture).get("wishList");
        String emptyWL = setText(venture).get("emptyWL");
        String addWL = setText(venture).get("addWL");

        init_screen.select_Country(venture, menuWiz);
        topBar_screen.click_MenuBtn();
        Thread.sleep(1000);

        // Find & Click on Wish list
        sideMenu_screen.click_Menu(wishList);

        // Verify the Wish list is EMPTY
        init_screen.getHelper().find_TextView_Android(emptyWL);
        wishList_screen.click_ContinueBrowsingBtn();

        // Verify Home Page is loaded
        home_screen.loaded();

        // Go to Categories & select random product
        topBar_screen.click_MenuBtn();
        Thread.sleep(1000);
        init_screen.getHelper().randomSelectProduct(categories, filterWiz, prodWiz);

        // Add to Wish list
        productDetail_screen.click_AddToWishListBtn();
        if (!init_screen.getHelper().isElementPresent(productDetail_screen.OKBtn)) { // We need select size first

            init_screen.getHelper().selectorRandom(productDetail_screen.chooseSizeBtn());
            productDetail_screen.click_AddToWishListBtn();
        }

        // Verify the message appear:
        init_screen.getHelper().find_TextView_Android(addWL);
        productDetail_screen.click_OKBtn(); //Click on OK button
    }

    @Test(dataProvider = "getVenturesDataToTest")
    public void test_WishListDeleteProduct (String venture) throws InterruptedException {
        String menuWiz = setText(venture).get("menuWiz");
        String categories = setText(venture).get("categories");
        String filterWiz = setText(venture).get("filterWiz");
        String prodWiz = setText(venture).get("prodWiz");
        String wishList = setText(venture).get("wishList");
        String emptyWL = setText(venture).get("emptyWL");

        // Select venture
        init_screen.select_Country(venture, menuWiz);
        topBar_screen.click_MenuBtn();
        Thread.sleep(1000);

        // Add random product to WishList
        init_screen.getHelper().randomSelectProduct(categories, filterWiz, prodWiz);
//        addToWishList();
        productDetail_screen.click_AddToWishListBtn();

        // Add to Wish list
        if (init_screen.getHelper().hasSizeErrorMgs()) { // We need select size first

//            chooseSize();
            productDetail_screen.click_ChooseSizeBtn();
            init_screen.getHelper().randClick(productDetail_screen.sizes);
            Thread.sleep(2000);

            productDetail_screen.click_AddToWishListBtn();

        }
        productDetail_screen.click_OKBtn();

        // Go to WishList
        topBar_screen.click_MenuBtn();
        init_screen.getHelper().text(wishList).click();

        // Delete product from WishList
        wishList_screen.click_DeleteBtn();

        // Verify the WishList is empty
        WebElement deleteBtn = null;

        try { // try to find Delete button
            deleteBtn = wishList_screen.deleteBtn();
        } catch (NoSuchElementException e) {}

        Assert.assertNull(deleteBtn);
        Assert.assertTrue(wishList_screen.noItemMessage().isDisplayed());
        Assert.assertEquals(emptyWL, wishList_screen.noItemMessage().getText());
    }

    @Test(dataProvider = "getVenturesDataToTest")
    public void test_WishListAddToCart (String venture) throws InterruptedException {
        String menuWiz = setText(venture).get("menuWiz");
        String categories = setText(venture).get("categories");
        String filterWiz = setText(venture).get("filterWiz");
        String prodWiz = setText(venture).get("prodWiz");
        String wishList = setText(venture).get("wishList");

        // Select venture
        init_screen.select_Country(venture, menuWiz);
        topBar_screen.click_MenuBtn();
        Thread.sleep(1000);

        // Random and add product to WishList
        init_screen.getHelper().randomSelectProduct(categories, filterWiz, prodWiz);
        String productName = productDetail_screen.productName().getText();
//        addToWishList();
        productDetail_screen.click_AddToWishListBtn();

        // Add to Wish list
        if (init_screen.getHelper().hasSizeErrorMgs()) { // We need select size first

//            chooseSize();
            productDetail_screen.click_ChooseSizeBtn();
            init_screen.getHelper().randClick(productDetail_screen.sizes);
            Thread.sleep(2000);

            productDetail_screen.click_AddToWishListBtn();

        }
        productDetail_screen.click_OKBtn();

        // Go to WishList
        topBar_screen.click_MenuBtn();
        sideMenu_screen.click_Menu(wishList);

        // Add 1 product to Cart from WishList
        wishList_screen.click_AddToCartBtn();

        // Go to myCart
        topBar_screen.click_MyCartBtn();

        // Verify product that product appears in myCart
        String myCardStr = cart_screen.productName().getText();
        Assert.assertTrue(myCardStr.contains(productName));
    }

    @Test(dataProvider = "getVenturesDataToTest")
    public void test_WishListAddAllToCart (String venture) throws InterruptedException {
        String menuWiz = setText(venture).get("menuWiz");
        String categories = setText(venture).get("categories");
        String filterWiz = setText(venture).get("filterWiz");
        String prodWiz = setText(venture).get("prodWiz");
        String wishList = setText(venture).get("wishList");

        // Select venture
        init_screen.select_Country(venture, menuWiz);
        topBar_screen.click_MenuBtn();
        Thread.sleep(1000);

        // Select random product - first time has wizard
        init_screen.getHelper().randomSelectProduct(categories, filterWiz, prodWiz);
//        addToWishList();

        productDetail_screen.click_AddToWishListBtn();

        // Add to Wish list
        if (init_screen.getHelper().hasSizeErrorMgs()) { // We need select size first

//            chooseSize();
            productDetail_screen.click_ChooseSizeBtn();
            init_screen.getHelper().randClick(productDetail_screen.sizes);
            Thread.sleep(2000);

            productDetail_screen.click_AddToWishListBtn();

        }
        productDetail_screen.click_OKBtn();

        Random ran = new Random();
        int numberOfProduct = ran.nextInt(10);
        for (int i = 0; i < numberOfProduct; i++) {

//            addProductToWishListNoWizard(
//                    categories,
//                    appPackage);

            topBar_screen.click_MenuBtn();
            init_screen.getHelper().text_exact(categories).click();

            // Random selection Categories
            init_screen.getHelper().randClick(By.id(appPackage + ":id/category_name"));

            // Random selection sub-Categories
            init_screen.getHelper().randClick(By.id(appPackage + ":id/text"));

            // Get back to the Main Screen for viewing the product
            init_screen.getHelper().find(appPackage + ":id/general_container").click();
            init_screen.getHelper().find(appPackage + ":id/general_container").click();

            // Add product to WishList
//        addToWishList();
            productDetail_screen.click_AddToWishListBtn();

            // Add to Wish list
            if (init_screen.getHelper().hasSizeErrorMgs()) { // We need select size first

//                chooseSize();
                productDetail_screen.click_ChooseSizeBtn();
                init_screen.getHelper().randClick(productDetail_screen.sizes);
                Thread.sleep(2000);

                productDetail_screen.click_AddToWishListBtn();

            }
            productDetail_screen.click_OKBtn();
        }

        // Go to WishList
        topBar_screen.click_MenuBtn();
        sideMenu_screen.click_Menu(wishList);

        // Add all product to Cart
        wishList_screen.click_AddAllToCart();// Click Add all to Cart
        topBar_screen.click_MyCartBtn();// Click on Go to Cart
    }

    @Test(dataProvider = "getDataToTest_WishListShareItem")
    public void test_WishListShareItem(String venture, String sharerAppName) throws InterruptedException {
        String menuWiz = setText(venture).get("menuWiz");
        String categories = setText(venture).get("categories");
        String filterWiz = setText(venture).get("filterWiz");
        String prodWiz = setText(venture).get("prodWiz");

        // Select venture
        init_screen.select_Country(venture, menuWiz);
        topBar_screen.click_MenuBtn();
        Thread.sleep(1000);

        // Select random product - first time has wizard
        init_screen.getHelper().randomSelectProduct(categories, filterWiz, prodWiz);

        // Select the Application to share the Item: Bluetooth, Message, Email
        productDetail_screen.click_ShareBtn();
        productDetail_screen.click_AppSharer(sharerAppName);
    }

}
