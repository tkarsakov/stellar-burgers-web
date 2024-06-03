package com.intexsoft.stellarburgersweb;

import com.intexsoft.stellarburgersweb.hooks.BaseTest;
import com.intexsoft.stellarburgersweb.page.MainPage;
import com.intexsoft.stellarburgersweb.page.common.ConstructorSection;
import io.qameta.allure.Flaky;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static com.intexsoft.stellarburgersweb.page.common.ConstructorSection.*;

@RunWith(Parameterized.class)
public class MainPageTest extends BaseTest {
    private final ConstructorSection section;

    public MainPageTest(ConstructorSection section) {
        this.section = section;
    }

    @Parameterized.Parameters
    public static Object[][] getSections() {
        return new Object[][]{
                {SAUCE},
                {FILLING},
                {BUN}
        };
    }

    @Test
    @Flaky
    public void clickOnSectionsExpectCorrectSectionDisplayed() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickOnConstructorSection(section);
        Assert.assertTrue(mainPage.isSectionSelected(section));
        Assert.assertTrue(mainPage.isCorrectSectionDisplayed(section));
    }


}
