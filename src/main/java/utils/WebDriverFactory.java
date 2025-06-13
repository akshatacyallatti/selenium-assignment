package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;


/*
common setup to support different browser based on the input
*/
public class WebDriverFactory {
    public static WebDriver getDriver(String browser) {
        WebDriver driver = null;

        switch (browser.toLowerCase()) {
            case "chrome":
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--start-maximized");
                driver = new ChromeDriver(options);
                break;

            case "firefox":
                FirefoxOptions optionsObj = new FirefoxOptions();
                optionsObj.addArguments("--start-maximized");
                driver = new FirefoxDriver();
                break;

            // Add other browsers like Edge, Safari if needed

            default:
                throw new IllegalArgumentException("Unsupported browser: " + browser);
        }

        return driver;
    }
}
