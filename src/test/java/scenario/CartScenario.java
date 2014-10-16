package scenario;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import util.AppiumSetupTest;

import java.util.List;
import java.util.Random;

import static util.Helper.*;

/**
 * Created by lazhcm10136 on 10/2/14.
 */
public class CartScenario extends AppiumSetupTest {

    protected void cartAddProduct (String venture, String menuWiz, String wishList, String emptyWL,
                                   String categories, String filterWiz, String prodWiz, String addWL, String emptyCart) throws InterruptedException {
        // Select venture
        selectVenture(venture, menuWiz);
        findByUISelector("resourceID", "abs__home", appPackage).click();
        Thread.sleep(1000);

        // Add random product to Cart
        randomSelectProduct(categories, appPackage, filterWiz, prodWiz);
        String productName = findByUISelector("resourceID", "product_name", appPackage).getText();//  Get product name
        findByUISelector("resourceID", "shop", appPackage).click();//  Click on Add to cart
        findByUISelector("resourceID", "button1", appPackage).click();// Click on Go to cart


        // Verify the myCart appears the product
        Assert.assertEquals(productName, findByUISelector("resourceID", "item_name", appPackage).getText());// Verify product name is correct


    }

    protected void cartDeleteProduct (String venture, String menuWiz, String wishList, String emptyWL,

                                      String categories, String filterWiz, String prodWiz, String addWL, String emptyCart) throws InterruptedException {

        // Select venture
        selectVenture(venture, menuWiz);
        findByUISelector("resourceID", "abs__home", appPackage).click();
        Thread.sleep(1000);

        // Add random product to Cart
        randomSelectProduct(categories, appPackage, filterWiz, prodWiz);
        findByUISelector("resourceID", "shop", appPackage).click();//  Click on Add to cart
        findByUISelector("resourceID", "button1", appPackage).click();// Click on Go to cart

        // Remove product from myCart
        findByUISelector("resourceID", "delete_button", appPackage).click();// Click on Remove this product
        findByUISelector("resourceID", "button1", appPackage).click();// Click on Remove item
        // Verify Empty Cart text
        Assert.assertTrue(driver.getPageSource().toString().contains(emptyCart));



    }

    protected void cartChangeQuantity (String venture, String menuWiz, String wishList, String emptyWL,

                                       String categories, String filterWiz, String prodWiz, String addWL, String emptyCart) throws InterruptedException {
        // Select venture
        selectVenture(venture, menuWiz);
        findByUISelector("resourceID", "abs__home", appPackage).click();
        Thread.sleep(1000);

        // Add random product to Cart
        randomSelectProduct(categories, appPackage, filterWiz, prodWiz);
        findByUISelector("resourceID", "shop", appPackage).click();//  Click on Add to cart
        findByUISelector("resourceID", "button1", appPackage).click();// Click on Go to cart

        // Change quantity of product
        findByUISelector("resourceID", "changequantity_button", appPackage).click();// Click on quantity
        List<WebElement> arrResult = findsByUISelector("resourceID", "item_text", appPackage);// Click on quantity
        Random  a = new Random();
        int randQuantity = a.nextInt(arrResult.size());
        arrResult.get(randQuantity).click();

        // After change the quantity -> verify the quantity is correct
        Assert.assertEquals(Integer.parseInt(findByUISelector("resourceID", "changequantity_button", appPackage).getText().replaceAll("\\s","")),
                randQuantity+1);
    }


}
