package scenario;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import screenObjects.android_app.Home_Screen;
import screenObjects.android_app.Init_Screen;
import util.AppiumSetupTest;

import java.util.List;

import static util.Helper.*;

/**
 * Created by lazhcm10136 on 10/8/14.
 */
public class SearchScenario extends AppiumSetupTest {
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

    protected void searchPopular () {

        Home_Screen.click_SearchTxt();
    }
}
