package org.endava.automation.testing.Utils;

import java.io.IOException;
import org.endava.automation.testing.Enums.DriverTypeEnum;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;

public class BaseTest {

    protected WebDriver driver;
    protected String baseUrl;
    protected SoftAssert softAssert;

    @BeforeClass
    protected void beforeClass() throws IOException {
        baseUrl = PropertiesReader
            .readFromProperties(ConfigurationConstants.MY_PROPERTIES_PATH, ConfigurationConstants.BASE_URL_PROPERTY);
        Log.info("Base URL: " + baseUrl);
        String driverType = PropertiesReader
            .readFromProperties(ConfigurationConstants.MY_PROPERTIES_PATH, ConfigurationConstants.DRIVER_TYPE_PROPERTY);
        Log.info("Web Driver Type: " + driverType);
        Log.info("Creating WebDriver instance...");
        driver = DriverFactory.createDriverForBrowserWithValue(DriverTypeEnum.parse(driverType));
        Log.info("WebDriver instance is created.");
        beforeClassExtended();
    }

    protected void beforeClassExtended() throws IOException {
    }

    @BeforeMethod
    protected void beforeMethod() {
        softAssert = new SoftAssert();
    }

    @AfterMethod
    protected void afterMethod() {
        softAssert.assertAll();
    }

    protected void afterClassExtended() {
    }

    @AfterClass(alwaysRun = true)
    protected void afterClass() {
        driver.quit();
    }
}
