package com.intexsoft.stellarburgersweb.page;

import com.intexsoft.stellarburgersweb.component.NavBar;
import com.intexsoft.stellarburgersweb.service.PropertiesService;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static com.intexsoft.stellarburgersweb.service.PropertiesFile.CONFIG;

public abstract class BasePage {

    protected final WebDriver driver;

    private final NavBar navBar;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.navBar = new NavBar(driver);
        this.init(driver);
    }

    public abstract Boolean isPageOpened();

    public NavBar getNavBar() {
        return navBar;
    }

    protected Boolean isElementPresent(WebElement element) {
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOf(element));
        if (element == null) {
            return false;
        } else {
            return element.isDisplayed();
        }
    }

    protected Boolean isPageOpenedByPath(String path) {
        String pageUrl = PropertiesService.getProperty(CONFIG, "url") + path;
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.urlToBe(pageUrl));
        return pageUrl.equals(driver.getCurrentUrl());
    }

    private void init(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
}
