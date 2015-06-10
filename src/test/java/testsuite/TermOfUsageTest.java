package testsuite;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObject.Init_Screen;
import pageObject.SideMenu_Screen;
import pageObject.TopBar_Screen;
import util.AppiumSetupTest;
import static util.VentureText.setText;

public class TermOfUsageTest extends AppiumSetupTest {

    private Init_Screen init_screen;
    private SideMenu_Screen sideMenu_screen;
    private TopBar_Screen topBar_screen;

    @Override
    @BeforeMethod
    public void setUp() throws Exception {
        super.setUp();
        init_screen = new Init_Screen(driver);
        sideMenu_screen = new SideMenu_Screen(driver);
        topBar_screen= new TopBar_Screen(driver);
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

    /**
     * Verify text in Term of usage
     */
    @Test(dataProvider = "getVenturesDataToTest")
    public void test_VerifyText(String venture) throws Exception {
        String menuWiz = setText(venture).get("menuWiz");
        String term = setText(venture).get("term");

        // Select country and go to Term of usage
        init_screen.select_Country(venture, menuWiz);
        topBar_screen.click_MenuBtn();
        sideMenu_screen.click_Menu(term);
        init_screen.getHelper().switchToWebView();

        String pageSource = driver.getPageSource();
        System.out.println(pageSource);

        // Verify text
        if (venture.equals("Singapore")) {

            Assert.assertTrue(pageSource.contains("Welcome to the Lazada.com.sg website and/or the LAZADA mobile app"));
        }

        if (venture.equals("Malaysia")) {

            Assert.assertTrue(pageSource.contains("Welcome to the lazada.com.my website (the \"Site\"). These terms and conditions"));
        }

        if (venture.equals("Philippines")) {

            Assert.assertTrue(pageSource.contains("Welcome to the lazada.com.ph website (the \"Site\"). These terms and conditions"));
        }

        if (venture.equals("Vietnam")) {

            Assert.assertTrue(pageSource.contains("Cám ơn quý khách đã truy cập vào website Lazada.vn được vận hành bởi MENA Style Fashion"));
        }

        if (venture.equals("Thailand")) {

            Assert.assertTrue(pageSource.contains("ยินดีต้อนรับสู่ www.lazada.co.th ข้อตกลงและเงื่อนไขเหล่านี้ใช้ได้กับเว็ปไซต์ของลาซาด้า และกับทุกภาคส่วน รว"));
        }

        if (venture.equals("Indonesia")) {

            Assert.assertTrue(pageSource.contains("Halaman ini, bersama dokumen yang disebutkan di dalamnya, menginformasikan syarat dan ketentuan (Syarat dan Ketentuan ini) dimana kami memberikan produk (masing-masing Produk) apapun yang terdaftar di situs www.lazada.co.id"));
        }
    }


}
