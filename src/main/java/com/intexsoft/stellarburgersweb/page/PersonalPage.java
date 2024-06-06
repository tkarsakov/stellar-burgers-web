package com.intexsoft.stellarburgersweb.page;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PersonalPage extends BasePage {
    //Кнопка "Выход" на личной странице
    @FindBy(xpath = "//button[text()=\"Выход\"]")
    private WebElement logOutButton;

    public PersonalPage(WebDriver driver) {
        super(driver);
    }

    @Step("Проверяем открыта ли личная страница по наличию ссылки 'Выход'")
    @Override
    public Boolean isPageOpened() {
        return super.isElementPresent(logOutButton);
    }

    @Step("Кликаем на кнопку 'Выйти' на личной странице пользователя")
    public LogInPage clickLogOutButton() {
        new WebDriverWait(driver, Duration.ofSeconds(2)).until(ExpectedConditions.visibilityOf(logOutButton));
        logOutButton.click();
        return new LogInPage(driver);
    }
}
