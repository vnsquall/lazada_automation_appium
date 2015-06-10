package testsuite;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObject.*;
import util.AppiumSetupTest;
import java.util.List;
import java.util.Random;
import static util.VentureText.setText;

public class CartTest extends AppiumSetupTest {

    private Cart_Screen cart_screen;
    private Init_Screen init_screen;
    private ProductDetail_Screen productDetail_screen;
    private TopBar_Screen topBar_screen;

    @Override
    @BeforeMethod
    public void setUp() throws Exception {
        super.setUp();
        init_screen = new Init_Screen(driver);
        topBar_screen = new TopBar_Screen(driver);
        cart_screen = new Cart_Screen(driver);
        productDetail_screen = new ProductDetail_Screen(driver);
    }
    @DataProvider
    Object[][] getVenturesDataToTest() {
        return new Object[][]{
                {"Singapore"},
                {"Malaysia"},
                {"Philippines"},
                {"Indonesia"},
                {"Vietnam"}
        };
    }

    @Test(dataProvider = "getVenturesDataToTest")
    public void AddProductToCart(String venture) throws Exception {
        String menuWiz = setText(venture).get("menuWiz");
        String categories = setText(venture).get("categories");
        String filterWiz = setText(venture).get("filterWiz");
        String prodWiz = setText(venture).get("prodWiz");

        // Select venture
        init_screen.select_Country(venture, menuWiz);
        topBar_screen.click_MenuBtn();
        Thread.sleep(1000);

        // Add random product to Cart
        productDetail_screen.getHelper().randomSelectProduct(categories, filterWiz, prodWiz);
        String productName = productDetail_screen.productName().getText();//  Get product name
        productDetail_screen.click_AddToCartBtn();//  Click on Add to cart
        topBar_screen.click_MyCartBtn();

        // Verify the myCart appears the product
        Assert.assertEquals(productName, cart_screen.productName().getText());// Verify product name is correct
    }

    @Test(dataProvider = "getVenturesDataToTest", enabled = false )
    public void DeleteProductFromCart(String venture) throws Exception {
        String menuWiz = setText(venture).get("menuWiz");
        String categories = setText(venture).get("categories");
        String filterWiz = setText(venture).get("filterWiz");
        String prodWiz = setText(venture).get("prodWiz");
        String emptyCart = setText(venture).get("emptyCart");

        // Select venture
        init_screen.select_Country(venture, menuWiz);
        topBar_screen.click_MenuBtn();
        Thread.sleep(1000);

        // Add random product to Cart
        productDetail_screen.getHelper().randomSelectProduct(categories, filterWiz, prodWiz);
        productDetail_screen.click_AddToCartBtn();//  Click on Add to cart
        topBar_screen.click_MyCartBtn();

        // Remove product from myCart
        cart_screen.click_deleteProductBtn();
        cart_screen.click_DeleteConfirm();

        // Verify Empty Cart text
        Assert.assertTrue(productDetail_screen.getHelper().isPageContains(emptyCart));
    }

    @Test(dataProvider = "getVenturesDataToTest" )
    public void ChangeQuantity(String venture) throws Exception {
        String menuWiz = setText(venture).get("menuWiz");
        String categories = setText(venture).get("categories");
        String filterWiz = setText(venture).get("filterWiz");
        String prodWiz = setText(venture).get("prodWiz");

        // Select venture
        init_screen.select_Country(venture, menuWiz);
        topBar_screen.click_MenuBtn();
        Thread.sleep(1000);

        // Add random product to Cart
        productDetail_screen.getHelper().randomSelectProduct(categories, filterWiz, prodWiz);
        productDetail_screen.click_AddToCartBtn();//  Click on Add to cart
        topBar_screen.click_MyCartBtn();

        // Change quantity
        cart_screen.click_quantityBtn();// Click on quantity
        List<WebElement> arrResult = cart_screen.sizes();// Click on quantity
        Random a = new Random();
        int randQuantity = a.nextInt(arrResult.size());
        arrResult.get(randQuantity).click();

        // After change the quantity -> verify the quantity is correct
        Assert.assertEquals(Integer.parseInt(cart_screen.quantity().getText().replaceAll("\\s", "")),
                randQuantity + 1);
    }

}
