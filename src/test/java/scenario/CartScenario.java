package scenario;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import screenObjects.android_app.Cart_Screen;
import screenObjects.android_app.Init_Screen;
import screenObjects.android_app.ProductDetail_Screen;
import screenObjects.android_app.TopBar_Screen;
import util.AppiumSetupTest;

import java.util.List;
import java.util.Random;

import static util.Helper.*;

/**
 * Created by lazhcm10136 on 10/2/14.
 */
public class CartScenario extends AppiumSetupTest {

    protected void cartAddProduct(String venture, String menuWiz, String wishList, String emptyWL,
                                  String categories, String filterWiz, String prodWiz, String addWL, String emptyCart) throws InterruptedException {
        // Select venture
        Init_Screen.select_Country(venture, menuWiz);
        TopBar_Screen.click_HomeBtn();
        Thread.sleep(1000);

        // Add random product to Cart
        randomSelectProduct(categories, appPackage, filterWiz, prodWiz);
        String productName = ProductDetail_Screen.productName().getText();//  Get product name
        ProductDetail_Screen.click_AddToCartBtn();//  Click on Add to cart
        ProductDetail_Screen.click_GoToCartBtn();// Click on Go to cart

        // Verify the myCart appears the product
        Assert.assertEquals(productName, Cart_Screen.productName().getText());// Verify product name is correct
    }

    protected void cartDeleteProduct(String venture, String menuWiz, String wishList, String emptyWL,
                                     String categories, String filterWiz, String prodWiz, String addWL, String emptyCart) throws InterruptedException {

        // Select venture
        Init_Screen.select_Country(venture, menuWiz);
        TopBar_Screen.click_HomeBtn();
        Thread.sleep(1000);

        // Add random product to Cart
        randomSelectProduct(categories, appPackage, filterWiz, prodWiz);
        ProductDetail_Screen.click_AddToCartBtn();//  Click on Add to cart
        ProductDetail_Screen.click_GoToCartBtn();// Click on Go to cart

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
        TopBar_Screen.click_HomeBtn();
        Thread.sleep(1000);

        // Add random product to Cart
        randomSelectProduct(categories, appPackage, filterWiz, prodWiz);
        ProductDetail_Screen.click_AddToCartBtn();//  Click on Add to cart
        ProductDetail_Screen.click_GoToCartBtn();// Click on Go to cart

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
