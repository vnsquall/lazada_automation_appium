package scenario;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import screenObjects.android_app.Init_Screen;
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
        findElement("resourceID", "abs__home").click();
        Thread.sleep(1000);

        // Add random product to Cart
        randomSelectProduct(categories, appPackage, filterWiz, prodWiz);
        String productName = findElement("resourceID", "product_name").getText();//  Get product name
        findElement("resourceID", "shop").click();//  Click on Add to cart
        findElement("resourceID", "button1").click();// Click on Go to cart

        // Verify the myCart appears the product
        Assert.assertEquals(productName, findElement("resourceID", "item_name").getText());// Verify product name is correct
    }

    protected void cartDeleteProduct(String venture, String menuWiz, String wishList, String emptyWL,
                                     String categories, String filterWiz, String prodWiz, String addWL, String emptyCart) throws InterruptedException {

        // Select venture
        Init_Screen.select_Country(venture, menuWiz);
        findElement("resourceID", "abs__home").click();
        Thread.sleep(1000);

        // Add random product to Cart
        randomSelectProduct(categories, appPackage, filterWiz, prodWiz);
        findElement("resourceID", "shop").click();//  Click on Add to cart
        findElement("resourceID", "button1").click();// Click on Go to cart

        // Remove product from myCart
        findElement("resourceID", "delete_button").click();// Click on Remove this product
        findElement("resourceID", "button1").click();// Click on Remove item
        // Verify Empty Cart text
        Assert.assertTrue(driver.getPageSource().toString().contains(emptyCart));
    }

    protected void cartChangeQuantity(String venture, String menuWiz, String wishList, String emptyWL,
                                      String categories, String filterWiz, String prodWiz, String addWL, String emptyCart) throws InterruptedException {
        // Select venture
        Init_Screen.select_Country(venture, menuWiz);
        findElement("resourceID", "abs__home").click();
        Thread.sleep(1000);

        // Add random product to Cart
        randomSelectProduct(categories, appPackage, filterWiz, prodWiz);
        findElement("resourceID", "shop").click();//  Click on Add to cart
        findElement("resourceID", "button1").click();// Click on Go to cart

        // Change quantity of product
        findElement("resourceID", "changequantity_button").click();// Click on quantity
        List<WebElement> arrResult = findsByUISelector("resourceID", "item_text");// Click on quantity
        Random a = new Random();
        int randQuantity = a.nextInt(arrResult.size());
        arrResult.get(randQuantity).click();

        // After change the quantity -> verify the quantity is correct
        Assert.assertEquals(Integer.parseInt(findElement("resourceID", "changequantity_button").getText().replaceAll("\\s", "")),
                randQuantity + 1);
    }


}
