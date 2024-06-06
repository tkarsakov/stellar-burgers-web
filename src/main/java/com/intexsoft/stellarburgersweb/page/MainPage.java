package com.intexsoft.stellarburgersweb.page;

import com.intexsoft.stellarburgersweb.page.common.ConstructorSection;
import io.qameta.allure.Step;
import org.apache.commons.lang.NotImplementedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPage extends BasePage {
    //Форматируемая строка, которая указывает на заголовки секций в разделе "Собери бургер"
    private final String formattableSectionHeaderXpath = "//h2[text()=\"%s\"]";
    //Кнопка логина, которая видна незалогинненым пользователям под конструктором бургера
    @FindBy(xpath = "//button[text()=\"Войти в аккаунт\"]")
    private WebElement logInButton;
    //Кнопка "Оформить заказ", которая видна только залогиненным пользователям
    @FindBy(xpath = "//button[text()=\"Оформить заказ\"]")
    private WebElement createOrderButton;
    //Три секции в конструкторе
    @FindBy(xpath = "//span[text()=\"Булки\"]/parent::div")
    private WebElement bunSection;
    @FindBy(xpath = "//span[text()=\"Соусы\"]/parent::div")
    private WebElement sauceSection;
    @FindBy(xpath = "//span[text()=\"Начинки\"]/parent::div")
    private WebElement fillingSection;

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

    @Step("Кликаем на секцию {constructorSection}")
    public void clickOnConstructorSection(ConstructorSection constructorSection) {
        try {
            switch (constructorSection) {
                case BUN:
                    sauceSection.click();
                    bunSection.click();
                    //Вынужденный слип, пока прокручивается скроллбар
                    Thread.sleep(2000);
                    break;
                case SAUCE:
                    sauceSection.click();
                    Thread.sleep(2000);
                    break;
                case FILLING:
                    fillingSection.click();
                    Thread.sleep(2000);
                    break;
                default:
                    throw new NotImplementedException("Provided section isn't supported in this method");
            }
        } catch (InterruptedException e) {
            throw new RuntimeException("Thread sleep interrupted");
        }
    }

    @Step("Проверям выбрана ли секция {section}")
    public Boolean isSectionSelected(ConstructorSection section) {
        WebElement element;
        switch (section) {
            case BUN:
                element = bunSection;
                break;
            case SAUCE:
                element = sauceSection;
                break;
            case FILLING:
                element = fillingSection;
                break;
            default:
                throw new NotImplementedException("Section identifier not implemented");
        }
        String classList = element.getAttribute("class");
        return classList.contains("tab_tab_type_current__2BEPc");
    }

    @Step("Сверяем текст элемента, который ниже секции (должен быть заголовок секции)")
    public Boolean isCorrectSectionDisplayed(ConstructorSection section) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String script = String.format(
                "let sectionDiv = document.evaluate(\"//span[text()='%s']/parent::div\", document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue;" +
                        "let sectionRect = sectionDiv.getBoundingClientRect();" +
                        "let bottom = sectionRect.bottom;" +
                        "let left = sectionRect.left;" +
                        "let sectionHeader = document.elementFromPoint(left, bottom+3).innerHTML;" +
                        "return sectionHeader;", section.getDesc());
        String sectionHeaderText = (String) js.executeScript(script);
        return section.getDesc().equals(sectionHeaderText);
    }
}
