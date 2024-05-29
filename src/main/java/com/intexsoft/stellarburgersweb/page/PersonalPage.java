package com.intexsoft.stellarburgersweb.page;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PersonalPage extends BasePage {
    //Ссылка выхода на личной странице
    @FindBy(xpath = "//button[text()=\"Выход\"]")
    private WebElement logOutLink;

    public PersonalPage(WebDriver driver) {
        super(driver);
    }

    @Step("Проверяем открыта ли личная страница по наличию ссылки 'Выход'")
    @Override
    public Boolean isPageOpened() {
        return super.isElementPresent(logOutLink);
    }
}
