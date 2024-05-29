package com.intexsoft.stellarburgersweb.page;

import com.intexsoft.stellarburgersweb.service.PropertiesService;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static com.intexsoft.stellarburgersweb.service.PropertiesFile.CONFIG;

public class RegisterPage extends BasePage {

    @FindBy(xpath = "//div/label[text()=\"Имя\"]/following-sibling::input")
    private WebElement nameInput;

    @FindBy(xpath = "//div/label[text()=\"Email\"]/following-sibling::input")
    private WebElement emailInput;

    @FindBy(xpath = "//input[@type=\"password\"]")
    private WebElement passwordInput;

    @FindBy(xpath = "//button[text()=\"Зарегистрироваться\"]")
    private WebElement registerButton;

    @FindBy(xpath = "//p[text()=\"Некорректный пароль\"]")
    private WebElement incorrectPasswordErrorMessage;

    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    @Step("Проверяем открыта ли страница регистрации")
    @Override
    public Boolean isPageOpened() {
        String registerPageUrl = PropertiesService.getProperty(CONFIG, "url") + "register";
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.urlToBe(registerPageUrl));
        return registerPageUrl.equals(driver.getCurrentUrl());
    }

    @Step("Вводим имя {name} нового пользователя")
    public RegisterPage typeName(String name) {
        nameInput.sendKeys(name);
        return this;
    }

    @Step("Вводим почту {email} нового пользователя")
    public RegisterPage typeEmail(String email) {
        emailInput.sendKeys(email);
        return this;
    }

    @Step("Вводим пароль {password} нового пользователя")
    public RegisterPage typePassword(String password) {
        passwordInput.sendKeys(password);
        return this;
    }

    @Step("Кликаем на кнопку 'Зарегистрироваться'")
    public LogInPage clickRegister() {
        registerButton.click();
        return new LogInPage(driver);
    }

    @Step("Проверяем присутствует ли ошибка введения пароля")
    public Boolean isIncorrectPasswordErrorPresent() {
        return incorrectPasswordErrorMessage.isDisplayed();
    }
}
