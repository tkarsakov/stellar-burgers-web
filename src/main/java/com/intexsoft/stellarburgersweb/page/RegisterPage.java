package com.intexsoft.stellarburgersweb.page;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegisterPage extends BasePage {
    //Поле для имени
    @FindBy(xpath = "//div/label[text()=\"Имя\"]/following-sibling::input")
    private WebElement nameInput;
    //Поле для почты
    @FindBy(xpath = "//div/label[text()=\"Email\"]/following-sibling::input")
    private WebElement emailInput;
    //Поле для пароля
    @FindBy(xpath = "//input[@type=\"password\"]")
    private WebElement passwordInput;
    //Кнопка "Зарегистрироваться"
    @FindBy(xpath = "//button[text()=\"Зарегистрироваться\"]")
    private WebElement registerButton;
    //Пэшка с ошибкой, которая всплывает, когда пароль меньше 6 символов
    @FindBy(xpath = "//p[text()=\"Некорректный пароль\"]")
    private WebElement incorrectPasswordErrorMessage;
    //Ссылка на страницу логина под формой
    @FindBy(xpath = "//a[text()=\"Войти\"]")
    private WebElement logInLink;

    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    @Step("Проверяем открыта ли страница регистрации")
    @Override
    public Boolean isPageOpened() {
        return super.isPageOpenedByPath("register");
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

    @Step("Кликаем на ссылку 'Войти' на странице регистрации")
    public LogInPage clickLogInLink() {
        logInLink.click();
        return new LogInPage(driver);
    }
}
