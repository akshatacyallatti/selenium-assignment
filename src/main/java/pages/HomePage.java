package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;


public class HomePage extends ProductPage {

    public HomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

}
