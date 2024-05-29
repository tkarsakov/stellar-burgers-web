package com.intexsoft.stellarburgersweb.page;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPage extends BasePage {
    //Кнопка логина, которая видна незалогинненым пользователям под конструктором бургера
    @FindBy(xpath = "//button[text()=\"Войти в аккаунт\"]")
    private WebElement logInButton;
    //Кнопка "Оформить заказ", которая видна только залогиненным пользователям
    @FindBy(xpath = "//button[text()=\"Оформить заказ\"]")
    private WebElement createOrderButton;

    public MainPage(WebDriver driver) {
        super(driver);
    }

    @Step("Проверяем открыта ли главная страница")
    @Override
    public Boolean isPageOpened() {
        return super.isPageOpenedByPath("");
    }

    @Step("Кликаем на кнопку 'Войти в аккаунт' под конструктором бургеров")
    public LogInPage clickLogInButton() {
        logInButton.click();
        return new LogInPage(driver);
    }

    @Step("Проверяем залогинен ли пользователь путем проверки того, какую кнопку он видит на главной странице")
    public Boolean isUserSeeingCreateOrderButton() {
        return super.isElementPresent(createOrderButton);
    }
}
