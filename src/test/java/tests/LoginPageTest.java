package tests;

import org.testng.Assert;
import org.testng.annotations.*;
import pages.LoginPage;
import utils.ConfigReader;
import utils.Constants;


public class LoginPageTest extends BaseTest {

    LoginPage loginPage;

    @BeforeClass
    public void setUpPage() {
        loginPage = new LoginPage(driver);
    }

    @Test(priority = 1, description = "Login to Amazon with valid credentials")
    public void testLogin() {
        String username = ConfigReader.get(Constants.USERNAME);
        String password = ConfigReader.get(Constants.PASSWORD);

        loginPage.login(username, password);
        Assert.assertTrue(loginPage.isLogoDisplayed(), "Login failed - Amazon logo not present");
    }

    @Test(priority = 2, description = "Get the first product after login", dependsOnMethods = "testLogin")
    public void testGetFirstProduct() {
        loginPage.getFirstProduct();
    }

    @Test(priority = 3, description = "Click on Cart button", dependsOnMethods = "testLogin")
    public void testClickOnCartButton() {
        loginPage.clickOnCartButton();
    }

    @Test(priority = 4, description = "Select previous year from dropdown", dependsOnMethods = "testLogin")
    public void testSelectPreviousYearFromDropDown() {
        loginPage.selectPreviousYearFromDopDown();
    }

    @Test(priority = 5, description = "Click on item link", dependsOnMethods = "testGetFirstProduct")
    public void testClickOnItemLink() {
        loginPage.clickOnItemLink();

        String currentUrl = loginPage.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("product") || currentUrl.contains("dp"), "Did not navigate to product detail page");
    }

    @Test(priority = 6, description = "Click on Buy Now button", dependsOnMethods = "testClickOnItemLink")
    public void testClickOnBuyNow() throws InterruptedException {
        loginPage.clickOnByNowAndHandleRedirect();

        String currentUrl = loginPage.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("checkout") || currentUrl.contains("payment") || currentUrl.contains("address"),
                "Did not navigate to payment, checkout, or address page");
    }

    @Test(priority = 7, description = "go back and click on sign In button to get add address option", dependsOnMethods = "testClickOnBuyNow")
    public void testAddAnotherAddress() {
        loginPage.addAnotherAddress();
    }


}
