package com.intexsoft.stellarburgersweb.page;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LogInPage extends BasePage {
    //Заголовок над формой логина
    @FindBy(xpath = "//h2[text()=\"Вход\"]")
    private WebElement logInHeader;
    //Поле для почты
    @FindBy(xpath = "//div/label[text()=\"Email\"]/following-sibling::input")
    private WebElement emailInput;
    //Поле для пароля
    @FindBy(xpath = "//input[@type=\"password\"]")
    private WebElement passwordInput;
    //Кнопка войти
    @FindBy(xpath = "//button[text()=\"Войти\"]")
    private WebElement logInButton;
    //Ссылка "Зарегистрироваться" под формой логина
    @FindBy(xpath = "//a[@href=\"/register\"]")
    private WebElement registerLink;
    //Ссылка "Восстановить пароль" под формой логина
    @FindBy(xpath = "//a[text()=\"Восстановить пароль\"]")
    private WebElement restorePasswordLink;

    public LogInPage(WebDriver driver) {
        super(driver);
    }

    @Step("Вводим почту {email} на странице логина")
    public LogInPage typeEmail(String email) {
        emailInput.sendKeys(email);
        return this;
    }

    @Step("Вводим пароль {password} на странице логина")
    public LogInPage typePassword(String password) {
        passwordInput.sendKeys(password);
        return this;
    }

    @Step("Нажимаем кнопку 'Войти'")
    public MainPage clickLogInButton() {
        logInButton.click();
        //Ждем пока закончится анимация после логина
        new WebDriverWait(driver, Duration.ofSeconds(2));
        return new MainPage(driver);
    }

    @Step("Кликаем на линк 'Зарегистрироваться'")
    public RegisterPage clickRegisterLink() {
        registerLink.click();
        return new RegisterPage(driver);
    }

    @Step("Проверяем открыта ли страница входа")
    public Boolean isPageOpened() {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOf(logInHeader));
        return logInHeader.getAttribute("innerHTML").equals("Вход");
    }

    @Step("Кликаем на ссылку 'Восстановить пароль' на странице входа")
    public RestorePasswordPage clickRestorePasswordLink() {
        restorePasswordLink.click();
        return new RestorePasswordPage(driver);
    }
}
