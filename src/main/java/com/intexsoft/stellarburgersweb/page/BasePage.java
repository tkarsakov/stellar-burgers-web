package com.intexsoft.stellarburgersweb.page;

import com.intexsoft.stellarburgersweb.component.NavBar;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public abstract class BasePage {

    protected final WebDriver driver;

    private final NavBar navBar;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.navBar = new NavBar(driver);
        this.init(driver);
    }

    public NavBar getNavBar() {
        return navBar;
    }

    public void init(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public abstract Boolean isPageOpened();
}
