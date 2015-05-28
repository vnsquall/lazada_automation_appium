package testsuite;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObject.*;
import util.AppiumSetupTest;

import java.util.List;
import static util.Helper.*;
import static util.Helper.find;
import static util.Helper.text_exact;
import static util.VentureText.setText;


public class Search_Test extends AppiumSetupTest {


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
        homeSearchAndSortResults(venture,
                setText(venture).get("menuWiz"),
                searchQuery,
                setText(venture).get("filterWiz"));
    }

    @Test(dataProvider = "getVenturesDataToTestPopular")
    public void test_SearchPopular(String venture) throws Exception {
        popularSearch(venture,
                setText(venture).get("menuWiz"),
                setText(venture).get("categories"),
                setText(venture).get("filterWiz"),
                setText(venture).get("prodWiz"));
    }

    @Test(dataProvider = "getVenturesDataToTestHistory")
    public void test_SearchHistory(String venture, String searchQuery) throws Exception {
        historySearch(venture,
                setText(venture).get("menuWiz"),
                setText(venture).get("categories"),
                searchQuery);
    }

    protected void searchQuery(String venture, String menuWiz,
                               String searchQuery, String filterWiz) throws Exception {

        Init_Screen.select_Country(venture, menuWiz);
        Thread.sleep(2000);
        Home_Screen.input_search(searchQuery);
        Thread.sleep(2000);
//        driver.tap(1, 550, 140, 1); //work-around Tap on search button with screen size "768x1280@120DPI"
        driver.sendKeyEvent(20); //KEYCODE_DOWN for select all products with searchQuery
        Thread.sleep(2000);
        driver.sendKeyEvent(66); //KEYCODE_ENTER
        Thread.sleep(2000);
        text_exact(filterWiz).click();
    }

    protected void homeSearchAndSortResults(String venture, String menuWiz, String searchQuery, String filterWiz) throws Exception {
        searchQuery(venture, menuWiz, searchQuery, filterWiz);

        WebElement homeSort = driver.findElement(By.id(appPackage + ":id/sort"));
        homeSort.click();

        List<WebElement> sortBys = driver.findElements(By.className("android.widget.CheckBox")); //Sort By check box
        int sortSize = sortBys.size();
        for (int i = 0; i < sortSize; i++) {
            sortBys.get(i).click();
            homeSort.click();
        }
    }

    protected void popularSearch (String venture,
                                  String menuWiz,
                                  String categories,
                                  String filterWiz,
                                  String prodWiz) throws InterruptedException {

        Init_Screen.select_Country(venture, menuWiz);
        TopBar_Screen.click_MenuBtn();
        SideMenu_Screen.click_Menu(categories);
        TopBar_Screen.click_SearchBtn();
        Thread.sleep(4000);
        randClick(Home_Screen.popularSearch());
        text_exact(filterWiz).click();
        find(appPackage + ":id/general_container").click();
        find(appPackage + ":id/general_container").click();
        text_exact(prodWiz).click();

        // Verify product name is displayed
        Assert.assertNotNull(ProductDetail_Screen.productName());
    }

    protected void historySearch (String venture,
                                  String menuWiz,
                                  String categories,
                                  String searchQuery) throws InterruptedException {

        Init_Screen.select_Country(venture, menuWiz);
        TopBar_Screen.click_MenuBtn();
        SideMenu_Screen.click_Menu(categories);
        TopBar_Screen.click_SearchBtn();
        Home_Screen.input_search(searchQuery);
        Thread.sleep(3000);
//        Home_Screen.searchSubmit();

//        driver.sendKeyEvent(66); //KEYCODE_ENTER
//        back();
//        TopBar_Screen.click_SearchBtn();
//        Home_Screen.clear_search();


    }
}
