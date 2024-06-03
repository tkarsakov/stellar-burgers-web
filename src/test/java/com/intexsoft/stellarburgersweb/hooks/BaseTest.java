package com.intexsoft.stellarburgersweb.hooks;

import com.intexsoft.stellarburgersweb.api.Steps;
import com.intexsoft.stellarburgersweb.model.User;
import com.intexsoft.stellarburgersweb.service.PropertiesService;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import static com.intexsoft.stellarburgersweb.service.PropertiesFile.CONFIG;

public abstract class BaseTest {
    protected final Steps steps = new Steps();
    protected WebDriver driver;
    protected User createdUser;

    @Before
    public void driverSetUp() {
        URL seleniumUrl;
        String uri = PropertiesService.getProperty(CONFIG, "seleniumUrl");
        try {
            seleniumUrl = new URI(uri).toURL();
        } catch (MalformedURLException e) {
            throw new RuntimeException("Incorrect URL format.");
        } catch (URISyntaxException e) {
            throw new RuntimeException("Incorrect URI syntax");
        }

        String browserName = PropertiesService.getProperty(CONFIG, "browserName");
        Capabilities capabilities;
        switch (browserName) {
            case "chrome":
                capabilities = new ChromeOptions();
                break;
            case "firefox":
                capabilities = new FirefoxOptions();
                break;
            case "edge":
                capabilities = new EdgeOptions();
                break;
            default:
                throw new RuntimeException("Browser support not implemented or browserName specified incorrectly");
        }

        driver = new RemoteWebDriver(seleniumUrl, capabilities);
        driver.get(PropertiesService.getProperty(CONFIG, "url"));
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
