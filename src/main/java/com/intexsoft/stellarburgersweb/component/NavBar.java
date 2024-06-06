package com.intexsoft.stellarburgersweb.component;

import com.intexsoft.stellarburgersweb.page.BasePage;
import com.intexsoft.stellarburgersweb.page.LogInPage;
import com.intexsoft.stellarburgersweb.page.MainPage;
import com.intexsoft.stellarburgersweb.page.PersonalPage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class NavBar {

    private WebDriver driver;
    //Кнопка "Конструктор" на навбаре
    @FindBy(xpath = "//nav//a/p[text()=\"Конструктор\"]")
    private WebElement constructorLink;
    //Кнопка "Лента заказов" на навбаре
    @FindBy(xpath = "//nav//a/p[text()=\"Лента Заказов\"]")
    private WebElement orderFeedLink;
    //Логотип-ссылка по центру
    @FindBy(xpath = "//div[contains(@class,\"AppHeader_header__logo\")]")
    private WebElement mainPageLogoLink;
    //Кнопка "Личный кабинет" на навбаре
    @FindBy(xpath = "//nav//a/p[text()=\"Личный Кабинет\"]")
    private WebElement personalPageLink;

    public NavBar(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @SuppressWarnings("unchecked")
    @Step("Кликаем на ссылку 'Личный кабинет' в навбаре")
    public <T extends BasePage> T clickPersonalPageLink(Boolean isUserLoggedIn) {
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(personalPageLink));
        personalPageLink.click();
        if (isUserLoggedIn) {
            return (T) new PersonalPage(driver);
        } else {
            return (T) new LogInPage(driver);
        }
    }

    @Step("Кликаем на ссылку 'Конструктор' в навбаре")
    public MainPage clickConstructorLink() {
        constructorLink.click();
        return new MainPage(driver);
    }
}
