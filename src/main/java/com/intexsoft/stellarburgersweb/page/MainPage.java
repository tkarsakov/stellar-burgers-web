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

public class MainPage extends BasePage {

    @FindBy(xpath = "//button[text()=\"Войти в аккаунт\"]")
    private WebElement logInButton;

    public MainPage(WebDriver driver) {
        super(driver);
    }

    @Step("Проверяем открыта ли главная страница")
    @Override
    public Boolean isPageOpened() {
        String mainPageUrl = PropertiesService.getProperty(CONFIG, "url");
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.urlToBe(mainPageUrl));
        return mainPageUrl.equals(driver.getCurrentUrl());
    }

    public LogInPage clickLogInButton() {
        logInButton.click();
        return new LogInPage(driver);
    }
}
