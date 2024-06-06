package com.intexsoft.stellarburgersweb.page;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RestorePasswordPage extends BasePage {
    //Ссылка "Войти" под формой восстановления пароля
    @FindBy(xpath = "//a[text()=\"Войти\"]")
    private WebElement logInLink;

    public RestorePasswordPage(WebDriver driver) {
        super(driver);
    }

    @Step("Проверяем открыта ли страница восстановления пароля")
    @Override
    public Boolean isPageOpened() {
        return super.isPageOpenedByPath("forgot-password");
    }

    @Step("Кликаем на ссылку 'Войти' под формой восстановления пароля")
    public LogInPage clickLogInLink() {
        logInLink.click();
        return new LogInPage(driver);
    }
}
