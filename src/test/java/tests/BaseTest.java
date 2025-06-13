package tests;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import utils.ConfigReader;
import utils.Constants;
import utils.ScreenshotUtils;
import utils.WebDriverFactory;

import java.time.Duration;


/*
Base setup to invoke webDriverFactory to get the required browsers
*/
public class BaseTest {

    protected WebDriver driver;

    @BeforeClass
    public void setUp() {
        driver = WebDriverFactory.getDriver(ConfigReader.get(Constants.BROWSER));
        driver.manage().timeouts().implicitlyWait(
                Duration.ofSeconds(Integer.parseInt(ConfigReader.get("timeout"))));
        driver.get(ConfigReader.get(Constants.BASEURL));
    }

    @AfterMethod
    public void takeScreenshotOnFailure(ITestResult result) {
        if (ITestResult.FAILURE == result.getStatus()) {
            String testName = result.getName();
            System.out.println("Test " + testName + " failed. Taking screenshot...");
            ScreenshotUtils.takeScreenshot(driver, testName + "_failure");
        }
    }

    @AfterClass
    public void tearDown() {
        if (driver != null)
            driver.quit();
    }
}
