package testsuite;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObject.*;
import util.AppiumSetupTest;
import java.util.List;
import static util.VentureText.setText;


public class CategoryTest extends AppiumSetupTest {

    private Init_Screen init_screen;
    @Override
    @BeforeMethod
    public void setUp() throws Exception {
        super.setUp();
        init_screen = new Init_Screen(driver);
    }

    @DataProvider
    Object[][] getVenturesDataToTest() {
        return new Object[][]{
                {"Singapore"},
                {"Malaysia"},
                {"Philippines"},
                {"Indonesia"},
                {"Vietnam"},
                {"Thailand"}
        };
    }

    @Test(dataProvider = "getVenturesDataToTest")
    public void test_BrowseCategories(String venture) throws Exception {
        String menuWiz = setText(venture).get("menuWiz");
        String filterWiz = setText(venture).get("filterWiz");

        init_screen.select_Country(venture, menuWiz);
        Thread.sleep(2000);
        driver.findElement(By.id(appPackage + ":id/abs__home")).click();
        //List of Menu Items
        List<WebElement> menuItems = driver.findElements(By.id(appPackage + ":id/component_text"));
        menuItems.get(1).click(); // Categories
        Thread.sleep(1000);

        //List of TOP Categories
        List<WebElement> topCategories = driver.findElements(By.id(appPackage + ":id/category_name"));
        int topSize = topCategories.size();
        for (int x = 0; x < topSize; x++) {
            topCategories.get(x).click();
            Thread.sleep(1000);
            if (x == 0) {
                init_screen.getHelper().text_exact(filterWiz).click();
                Thread.sleep(1000);
            }
            driver.findElement(By.xpath("//android.widget.TextView[@resource-id='" + appPackage + ":id/title' and @text='All']")).click();
            Thread.sleep(1000);
        }

        //Scroll DOWN for last Categories
        init_screen.getHelper().swipeDown();

        //List of the rest Categories
        List<WebElement> restCategories = driver.findElements(By.id(appPackage + ":id/category_name"));
        int restSize = restCategories.size();
        for (int y = 4; y < restSize; y++) {
            restCategories.get(y).click();
            Thread.sleep(1000);
            driver.findElement(By.xpath("//android.widget.TextView[@resource-id='" + appPackage + ":id/title' and @text='All']")).click();
            Thread.sleep(1000);
            init_screen.getHelper().swipeDown();
        }

        //Scroll UP for first Categories
        init_screen.getHelper().swipeUp();
        topCategories.get(1).click();
        driver.findElement(By.xpath("//android.widget.TextView[@resource-id='" + appPackage + ":id/title' and @text='All']")).click();
        init_screen.getHelper().swipeDown();
        init_screen.getHelper().swipeDown();
    }

}
