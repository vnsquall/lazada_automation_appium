package testsuite;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObject.*;
import util.AppiumSetupTest;
import java.util.List;
import static util.VentureText.setText;

public class SearchTest extends AppiumSetupTest {

    private Home_Screen home_screen;
    private Init_Screen init_screen;
    private ProductDetail_Screen productDetail_screen;
    private SideMenu_Screen sideMenu_screen;
    private TopBar_Screen topBar_screen;

    @Override
    @BeforeMethod
    public void setUp() throws Exception {
        super.setUp();
        home_screen = new Home_Screen(driver);
        init_screen = new Init_Screen(driver);
        productDetail_screen = new ProductDetail_Screen(driver);
        sideMenu_screen = new SideMenu_Screen(driver);
        topBar_screen = new TopBar_Screen(driver);
    }

    @DataProvider
    Object[][] getVenturesDataToTest() {
        return new Object[][]{
                {"Singapore", "Asus"},
                {"Malaysia", "Adidas"},
                {"Philippines", "Nike"},
                {"Indonesia", "Cari"},
                {"Vietnam", "Apple"}
        };
    }


    @DataProvider
    Object[][] getVenturesDataToTestPopular() {
        return new Object[][]{
                {"Singapore"},
                {"Malaysia"},
                {"Philippines"},
                {"Indonesia"},
                {"Vietnam"}
        };
    }

    @DataProvider
    Object[][] getVenturesDataToTestHistory() {
        return new Object[][]{
                {"Singapore", "Asus"},
//                {"Malaysia", "Adidas"},
//                {"Philippines", "Nike"},
//                {"Indonesia", "Cari"},
//                {"Vietnam", "Apple"}
        };
    }

    @Test(dataProvider = "getVenturesDataToTest")
    public void test_SearchInHomePage(String venture, String searchQuery) throws Exception {
        String menuWiz = setText(venture).get("menuWiz");
        String filterWiz = setText(venture).get("filterWiz");

//        searchQuery(venture, menuWiz, searchQuery, filterWiz);
        init_screen.select_Country(venture, menuWiz);
        Thread.sleep(2000);
        home_screen.input_search(searchQuery);
        Thread.sleep(2000);
//        driver.tap(1, 550, 140, 1); //work-around Tap on search button with screen size "768x1280@120DPI"
        driver.sendKeyEvent(20); //KEYCODE_DOWN for select all products with searchQuery
        Thread.sleep(2000);
        driver.sendKeyEvent(66); //KEYCODE_ENTER
        Thread.sleep(2000);
        init_screen.getHelper().text_exact(filterWiz).click();

        WebElement homeSort = driver.findElement(By.id(appPackage + ":id/sort"));
        homeSort.click();

        List<WebElement> sortBys = driver.findElements(By.className("android.widget.CheckBox")); //Sort By check box
        int sortSize = sortBys.size();
        for (int i = 0; i < sortSize; i++) {
            sortBys.get(i).click();
            homeSort.click();
        }
    }

    @Test(dataProvider = "getVenturesDataToTestPopular")
    public void test_SearchPopular(String venture) throws Exception {
        String menuWiz = setText(venture).get("menuWiz");
        String categories = setText(venture).get("categories");
        String filterWiz = setText(venture).get("filterWiz");
        String prodWiz = setText(venture).get("prodWiz");

        init_screen.select_Country(venture, menuWiz);
        topBar_screen.click_MenuBtn();
        sideMenu_screen.click_Menu(categories);
        topBar_screen.click_SearchBtn();
        Thread.sleep(4000);
        init_screen.getHelper().randClick(home_screen.popularSearch());
        init_screen.getHelper().text_exact(filterWiz).click();
        init_screen.getHelper().find(appPackage + ":id/general_container").click();
        init_screen.getHelper().find(appPackage + ":id/general_container").click();
        init_screen.getHelper().text_exact(prodWiz).click();

        // Verify product name is displayed
        Assert.assertNotNull(productDetail_screen.productName());
    }

    @Test(dataProvider = "getVenturesDataToTestHistory")
    public void test_SearchHistory(String venture, String searchQuery) throws Exception {
        String menuWiz = setText(venture).get("menuWiz");
        String categories = setText(venture).get("categories");

        init_screen.select_Country(venture, menuWiz);
        topBar_screen.click_MenuBtn();
        sideMenu_screen.click_Menu(categories);
        topBar_screen.click_SearchBtn();
        home_screen.input_search(searchQuery);
        Thread.sleep(3000);
    }

//    protected void searchQuery(String venture, String menuWiz,
//                               String searchQuery, String filterWiz) throws Exception {
//
//        init_screen.select_Country(venture, menuWiz);
//        Thread.sleep(2000);
//        home_screen.input_search(searchQuery);
//        Thread.sleep(2000);
////        driver.tap(1, 550, 140, 1); //work-around Tap on search button with screen size "768x1280@120DPI"
//        driver.sendKeyEvent(20); //KEYCODE_DOWN for select all products with searchQuery
//        Thread.sleep(2000);
//        driver.sendKeyEvent(66); //KEYCODE_ENTER
//        Thread.sleep(2000);
//        helper.text_exact(filterWiz).click();
//    }
}
