package com.intexsoft.stellarburgersweb;

import com.intexsoft.stellarburgersweb.page.LogInPage;
import com.intexsoft.stellarburgersweb.page.MainPage;
import com.intexsoft.stellarburgersweb.page.PersonalPage;
import org.junit.Assert;
import org.junit.Test;

public class PersonalPageTest extends BaseTest {

    @Test
    public void logInGoToPersonalPageExpectToSeePersonalPage() {
        MainPage mainPage = new MainPage(driver);
        LogInPage logInPage = mainPage.clickLogInButton();
        logInPage.typeEmail(createdUser.getEmail())
                .typePassword(createdUser.getPassword())
                .clickLogInButton();

        PersonalPage personalPage = mainPage.getNavBar().clickPersonalPageLink(true);
        Assert.assertTrue("Personal page isn't opened meaning login probably failed", personalPage.isPageOpened());
    }

    @Test
    public void goToPersonalPageWithoutLogInExpectToSeeLogInPage() {
        MainPage mainPage = new MainPage(driver);
        LogInPage logInPage = mainPage.getNavBar().clickPersonalPageLink(false);

        Assert.assertTrue("Log in page isn't open after clicking on personal page link without logging in", logInPage.isPageOpened());
    }
}