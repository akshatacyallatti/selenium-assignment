package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import utils.Constants;


import java.time.Duration;
import java.util.List;

public class ProductPage {

    WebDriver driver;
    WebDriverWait wait;
    JavascriptExecutor js;

    /*
        Initialize @FindBy elements using page factory concept
    */
    public ProductPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        this.js = (JavascriptExecutor) driver;
    }


    /*
        Required locators to identify the web elements
    */
    @FindBy(xpath = "(//a[@data-testid='product-card-link'])[5]")
    WebElement thirdDeal;

    @FindBy(css = "span.a-dropdown-label")
    WebElement quantityDropdown;

    @FindBy(xpath = "(//input[@id='add-to-cart-button'])[2]")
    WebElement addToCartButton;

    @FindBy(id = "nav-cart")
    WebElement cartIcon;

    @FindBy(xpath = "//*[@id='nav-cart-count']")
    WebElement cartCount;

    @FindBy(id = "twotabsearchtextbox")
    WebElement searchBox;

    @FindBy(css = "div.s-main-slot div[data-component-type='s-search-result']")
    WebElement searchOResults;

    @FindBy(css = "div.s-main-slot div[data-component-type='s-search-result']")
    WebElement mobilesList;

    @FindBy(xpath = "(//a[@class='a-button-text'])[3]")
    WebElement goToCartButton;

    @FindBy(xpath = "//span[@data-a-selector='value']")
    WebElement quantityOfTheItems;


    /*
        To get the Deal
    */
    public void clickThirdDeal() {
        thirdDeal.click();
    }

    /*
        To add minimum Quantity to the cart
    */
    public void addMinimumQuantityToCart() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(addToCartButton));
            js.executeScript("arguments[0].click();", addToCartButton);
        } catch (Exception e) {
            System.out.println("Error while adding to cart: " + e.getMessage());
        }
    }

    public void verifyQuantityInCart() {
        try {
            driver.findElement(By.id("nav-cart")).click();
            WebElement quantityElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//span[@data-a-selector='value']")));

            String expected = quantityElement.getText().trim();
            int actualQty = Integer.parseInt(driver.findElement(By.id("nav-cart-count")).getText().trim());

            Assert.assertEquals(actualQty, Integer.parseInt(expected), "Quantity in cart does not match expected.");
        } catch (Exception e) {
            System.out.println("Error verifying cart quantity: " + e.getMessage());
        }
    }

    public void searchForMobiles() {
        searchBox.sendKeys("Mobiles" + Keys.ENTER);
        wait.until(ExpectedConditions.visibilityOf(searchOResults));
        List<WebElement> items = driver.findElements(By.cssSelector("div.s-main-slot div[data-component-type='s-search-result']"));
        if (!items.isEmpty()) {
            WebElement lastItem = items.get(items.size() - 1);
            js.executeScript("arguments[0].scrollIntoView(true);", lastItem);
            wait.until(ExpectedConditions.visibilityOf(lastItem));
            String title = lastItem.findElement(By.cssSelector("h2 span")).getText();
            System.out.println("Last visible item title: " + title);
        } else {
            System.out.println("No items found.");
        }
    }

    public void navigateToMobilesThenHome() {
        driver.navigate().to("https://www.amazon.in/s?k=Mobiles&ref=nb_sb_noss");
        driver.navigate().to(utils.ConfigReader.get(Constants.BASEURL));
    }

}
