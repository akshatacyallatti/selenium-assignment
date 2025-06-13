package tests;

import org.testng.annotations.*;
import pages.ProductPage;
import utils.Constants;

public class ProductPageTest extends BaseTest {

    private ProductPage productPage;

    @BeforeClass
    public void setup() {
        productPage = new ProductPage(driver);
    }

    @Test(priority = 1, description = "Click the third deal on Today's Deals page")
    public void testClickThirdDeal() {
        driver.get(Constants.THIRD_PAGE_URL);
        productPage.clickThirdDeal();
    }

    @Test(priority = 2, description = "Add minimum quantity of product to cart and verify")
    public void testAddMinimumQuantityToCart() {
        productPage.addMinimumQuantityToCart();
        productPage.verifyQuantityInCart();
    }

    @Test(priority = 3, description = "Search for mobiles and scroll to last visible item")
    public void testSearchFunctionality() throws InterruptedException {
        productPage.searchForMobiles();
    }

    @Test(priority = 4, description = "Navigate to Mobiles page and return to home")
    public void testNavigation() {
        productPage.navigateToMobilesThenHome();
    }
}
