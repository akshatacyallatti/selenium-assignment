package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class LoginPage {

    WebDriver driver;
    WebDriverWait wait;
    JavascriptExecutor js;

    // PageFactory elements
    @FindBy(xpath = "//*[@id='nav-link-accountList']")
    private WebElement signInButton;

    @FindBy(id = "ap_email_login")
    private WebElement emailInput;

    @FindBy(id = "continue")
    private WebElement continueButton;

    @FindBy(id = "ap_password")
    private WebElement passwordInput;

    @FindBy(id = "signInSubmit")
    private WebElement signInSubmitButton;

    @FindBy(xpath = "//span[@role='button']")
    private WebElement clickOnDropDown;

    @FindBy(xpath = "//*[@id='nav-orders']")
    private WebElement cartButton;

    @FindBy(xpath = "//a[@id='time-filter_3']")
    private WebElement dropDrownOption;

    @FindBy(linkText = "Books")
    private WebElement clickOnBooksButon;

    @FindBy(xpath = "//input[@id='buy-now-button']")
    private WebElement clickOnBuyNow;

    @FindBy(xpath = "//div[@aria-label='State Bank of India **5983']//input[@type='radio']")
    private WebElement upiOptionRadioButton;

    @FindBy(xpath = "//input[@data-csa-c-purchase-id='404-2645890-6138703']")
    private WebElement paymentMethod;

    @FindBy(xpath = "//div[@class='yohtmlc-product-title']/a")
    private WebElement clickOnItemLink;

    @FindBy(xpath = "//div[contains(@aria-label, 'State Bank of India')]//input[@type='radio']")
    private WebElement upiPaymentButton;

    @FindBy(xpath = "//input[@type='submit' and contains(@aria-labelledby, 'announce')]")
    private WebElement buttonToAddPaymentMethod;

    @FindBy(xpath = "(//div[@class='a-box ya-card--rich'])[4]")
    private WebElement addressOptionButton;

    @FindBy(css = "div.a-box-inner.a-padding-extra-large")
    private WebElement addAnotherAddressButton;

    @FindBy(id = "address-ui-widgets-enterAddressFullName")
    public WebElement fullNameInputField;

    @FindBy(id = "address-ui-widgets-enterAddressFullName")
    private WebElement fullAddressInputField;

    @FindBy(id = "address-ui-widgets-enterAddressPhoneNumber")
    public WebElement phoneNumberInputField;

    @FindBy(id = "address-ui-widgets-enterAddressPostalCode")
    public WebElement postalCodeInputField;

    @FindBy(id = "address-ui-widgets-enterAddressLine1")
    public WebElement addressLine1InputField;

    @FindBy(id = "address-ui-widgets-enterAddressLine2")
    public WebElement addressLine2InputField;

    @FindBy(xpath = "(//input[@class='a-button-input'])[3]")
    private WebElement buttonToAddAddressData;


    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }

    public void login(String username, String password) {
        driver.get("https://www.amazon.in");

        wait.until(ExpectedConditions.elementToBeClickable(signInButton)).click();
        wait.until(ExpectedConditions.visibilityOf(emailInput)).sendKeys(username);
        continueButton.click();

        wait.until(ExpectedConditions.visibilityOf(passwordInput)).sendKeys(password);
        signInSubmitButton.click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("nav-logo-sprites")));
    }

    public void getFirstProduct() {
        //  driver.get("https://www.amazon.in/gp/browse.html?node=976389031&ref_=nav_em_sbc_books_all_0_2_17_2");
        clickOnBooksButon.click();
        driver.findElement(By.xpath("//*[@id='s-refinements']/div[2]/ul/li/span/a/div/label/i")).click();
        List<WebElement> deliveryMessages = driver.findElements(By.xpath("//div[contains(@class, 'a-row') and contains(., 'FREE delivery')]"));
        System.out.println("Message " + ": " + deliveryMessages.get(3).getText());
    }

    public void clickOnCartButton() {
        cartButton.click();
    }

    public void selectPreviousYearFromDopDown() {
        clickOnDropDown.click();
        wait.until(ExpectedConditions.visibilityOf(dropDrownOption));
        dropDrownOption.click();
    }


    public void clickOnItemLink() {
        wait.until(ExpectedConditions.elementToBeClickable(clickOnItemLink)).click();

    }

    public void clickOnByNowAndHandleRedirect() throws InterruptedException {
        clickOnBuyNow.click();
        // Wait for redirection and page to load
        wait.until(driver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));

        String currentUrl = driver.getCurrentUrl();
        System.out.println("Redirected to: " + currentUrl);

        assert currentUrl != null;
        if (currentUrl.contains("pay")) {
            handlePaymentPage();
        } else {
            System.out.println("Unexpected page: " + currentUrl);
        }
    }

    private void handlePaymentPage() {
        By upiRadioLocator = By.xpath("//div[contains(@aria-label, 'State Bank of India')]//input[@type='radio']");
        By continueButtonLocator = By.xpath("//input[@type='submit' and contains(@aria-labelledby, 'announce')]");

        try {
            WebElement upiRadio = wait.until(ExpectedConditions.presenceOfElementLocated(upiRadioLocator));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", upiRadio);
            wait.until(ExpectedConditions.elementToBeClickable(upiRadio)).click();
            System.out.println("UPI option selected");

            WebElement continueButton = wait.until(ExpectedConditions.elementToBeClickable(continueButtonLocator));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", continueButton);
            continueButton.click();

        } catch (StaleElementReferenceException e) {
            WebElement upiRadio = driver.findElement(upiRadioLocator);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", upiRadio);
            upiRadio.click();
        } catch (TimeoutException e) {
            System.err.println("Timeout waiting for UPI option or continue button.");
            throw e;
        }
    }

    public boolean isLogoDisplayed() {
        try {
            WebElement logo = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nav-logo-sprites")));
            return logo.isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public void addAnotherAddress() {
        driver.navigate().back();
        signInButton.click();
        addressOptionButton.click();
        addAnotherAddressButton.click();
        wait.until(ExpectedConditions.visibilityOf(fullNameInputField));

        WebElement nameField = wait.until(ExpectedConditions.elementToBeClickable(fullAddressInputField));
        nameField.sendKeys("akshata");

        WebElement phoneField = phoneNumberInputField;
        phoneField.sendKeys("8105654439");

        WebElement pinField = postalCodeInputField;
        pinField.sendKeys("500032");

        WebElement addressLine = addressLine1InputField;
        addressLine.sendKeys("55-2-166, pg, hyd");

        WebElement area = addressLine2InputField;
        area.sendKeys("gachobowli");


        Assert.assertEquals(fullNameInputField.getAttribute("value"), "akshata", "Full Name not filled correctly");
        Assert.assertEquals(phoneNumberInputField.getAttribute("value"), "8105654439", "Phone number not filled correctly");
        Assert.assertEquals(postalCodeInputField.getAttribute("value"), "500032", "Postal code not filled correctly");
        Assert.assertEquals(addressLine1InputField.getAttribute("value"), "55-2-166, pg, hyd", "Address Line 1 not filled correctly");
        Assert.assertEquals(addressLine2InputField.getAttribute("value"), "gachobowli", "Address Line 2 not filled correctly");
        wait.until(ExpectedConditions.elementToBeClickable(buttonToAddAddressData)).click();
    }

    public WebElement getFirstNameFiled() {
        return fullNameInputField;
    }

    public WebElement getPhoneNumberInputField() {
        return phoneNumberInputField;
    }

    public WebElement getAddressLine1InputField() {
        return addressLine1InputField;
    }

    public WebElement getAddressLine2InputField() {
        return addressLine2InputField;
    }

    public WebElement getPostalCodeInputField() {
        return postalCodeInputField;
    }
}

