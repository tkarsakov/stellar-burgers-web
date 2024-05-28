package com.intexsoft.stellarburgersweb.component;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NavBar {

    private WebDriver driver;

    @FindBy(xpath = "//nav//a/p[text()=\"Конструктор\"]")
    private WebElement constructorLink;

    @FindBy(xpath = "//nav//a/p[text()=\"Лента Заказов\"]")
    private WebElement orderFeedLink;

    @FindBy(xpath = "//div[contains(@class,\"AppHeader_header__logo\")]")
    private WebElement mainPageLogoLink;

    @FindBy(xpath = "//nav//a/p[text()=\"Личный Кабинет\"]")
    private WebElement personalPageLink;

    public NavBar(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
}
