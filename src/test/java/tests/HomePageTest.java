package tests;

import org.testng.Assert;
import org.testng.annotations.*;
import pages.HomePage;
import pages.ProductPage;
import utils.Constants;

public class HomePageTest extends BaseTest {

    private ProductPage productPage;

    @BeforeClass
    public void setUpPages() {
        productPage = new ProductPage(driver);
    }

//    @Test(priority = 1, description = "Verify navigation to Today's Deals page")
//    public void testNavigateToTodaysDeals() {
//        driver.findElement(Constants.TODAYSDEAL_BY).click();
//        String currentUrl = driver.getCurrentUrl();
//        Assert.assertTrue(currentUrl.contains(Constants.DEALS),
//                "Failed to navigate to Today's Deals page.");
//    }
//
//    @Test(priority = 2, description = "Verify clicking the third deal item")
//    public void testClickThirdDeal() {
//        productPage.clickThirdDeal();
//    }

//    @AfterClass
//    public void tearDown() {
//        if (driver != null) {
//            driver.quit();
//        }
//    }
}
