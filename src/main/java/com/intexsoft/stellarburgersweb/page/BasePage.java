package com.intexsoft.stellarburgersweb.page;

import com.intexsoft.stellarburgersweb.component.NavBar;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public abstract class BasePage {
    protected WebDriver driver;

    private NavBar navBar;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public NavBar getNavBar() {
        return navBar;
    }
}
