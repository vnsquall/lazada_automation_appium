package testsuite;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObject.Cart_Screen;
import pageObject.Init_Screen;
import pageObject.ProductDetail_Screen;
import pageObject.TopBar_Screen;
import util.AppiumSetupTest;

import java.util.List;
import java.util.Random;
import static util.Helper.driver;
import static util.Helper.randomSelectProduct;
import static util.VentureText.setText;


public class Cart_Test extends AppiumSetupTest {

    @DataProvider
    Object[][] getVenturesDataToTest() {
        return new Object[][]{
//                {"Singapore"}
                {"Malaysia"}
//                {"Philippines"}
//                {"Indonesia"},
//                {"Vietnam"}
        };
    }

    @Test(dataProvider = "getVenturesDataToTest", enabled = false)
    public void test_CartAddProduct(String venture) throws Exception {
        cartAddProduct(venture, setText(venture).get("menuWiz"), setText(venture).get("wishList"), setText(venture).get("emptyWL"),
                setText(venture).get("categories"), setText(venture).get("filterWiz"),
                setText(venture).get("prodWiz"), setText(venture).get("addWL"),setText(venture).get("emptyCart"));
    }

    @Test(dataProvider = "getVenturesDataToTest", enabled = false )
    public void test_CartDeleteProduct(String venture) throws Exception {
        cartDeleteProduct(venture, setText(venture).get("menuWiz"), setText(venture).get("wishList"), setText(venture).get("emptyWL"),
                setText(venture).get("categories"), setText(venture).get("filterWiz"),
                setText(venture).get("prodWiz"), setText(venture).get("addWL"),setText(venture).get("emptyCart") );
    }

    @Test(dataProvider = "getVenturesDataToTest" )
    public void test_CartChangeQuantity(String venture) throws Exception {
        cartChangeQuantity(venture, setText(venture).get("menuWiz"), setText(venture).get("wishList"), setText(venture).get("emptyWL"),
                setText(venture).get("categories"), setText(venture).get("filterWiz"),
                setText(venture).get("prodWiz"), setText(venture).get("addWL"), setText(venture).get("emptyCart"));
    }


    protected void cartAddProduct(String venture, String menuWiz, String wishList, String emptyWL,
                                  String categories, String filterWiz, String prodWiz, String addWL, String emptyCart) throws InterruptedException {
        // Select venture
        Init_Screen.select_Country(venture, menuWiz);
        TopBar_Screen.click_MenuBtn();
        Thread.sleep(1000);

        // Add random product to Cart
        randomSelectProduct(categories, filterWiz, prodWiz);
        String productName = ProductDetail_Screen.productName().getText();//  Get product name
        ProductDetail_Screen.click_AddToCartBtn();//  Click on Add to cart
//        ProductDetail_Screen.click_GoToCartBtn();// Click on Go to cart
        TopBar_Screen.click_MyCartBtn();

        // Verify the myCart appears the product
        Assert.assertEquals(productName, Cart_Screen.productName().getText());// Verify product name is correct
    }

    protected void cartDeleteProduct(String venture, String menuWiz, String wishList, String emptyWL,
                                     String categories, String filterWiz, String prodWiz, String addWL, String emptyCart) throws InterruptedException {

        // Select venture
        Init_Screen.select_Country(venture, menuWiz);
        TopBar_Screen.click_MenuBtn();
        Thread.sleep(1000);

        // Add random product to Cart
        randomSelectProduct(categories, filterWiz, prodWiz);
        ProductDetail_Screen.click_AddToCartBtn();//  Click on Add to cart
        TopBar_Screen.click_MyCartBtn();

        // Remove product from myCart
        Cart_Screen.click_deleteProductBtn();
        Cart_Screen.click_DeleteConfirm();

        // Verify Empty Cart text
        Assert.assertTrue(driver.getPageSource().toString().contains(emptyCart));
    }

    protected void cartChangeQuantity(String venture, String menuWiz, String wishList, String emptyWL,
                                      String categories, String filterWiz, String prodWiz, String addWL, String emptyCart) throws InterruptedException {
        // Select venture
        Init_Screen.select_Country(venture, menuWiz);
        TopBar_Screen.click_MenuBtn();
        Thread.sleep(1000);

        // Add random product to Cart
        randomSelectProduct(categories, filterWiz, prodWiz);
        ProductDetail_Screen.click_AddToCartBtn();//  Click on Add to cart
//        ProductDetail_Screen.click_GoToCartBtn();// Click on Go to cart
        TopBar_Screen.click_MyCartBtn();

        // Change quantity
        int quantity = changeQuantity();

        // After change the quantity -> verify the quantity is correct
        Assert.assertEquals(Integer.parseInt(Cart_Screen.quantity().getText().replaceAll("\\s", "")),
                quantity + 1);
    }

    protected int changeQuantity() {

        Cart_Screen.click_quantityBtn();// Click on quantity
        List<WebElement> arrResult = Cart_Screen.sizes();// Click on quantity
        Random a = new Random();
        int randQuantity = a.nextInt(arrResult.size());
        arrResult.get(randQuantity).click();
        return randQuantity;
    }


}
