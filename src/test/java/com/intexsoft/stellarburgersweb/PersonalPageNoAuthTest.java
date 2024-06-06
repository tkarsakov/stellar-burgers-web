package com.intexsoft.stellarburgersweb;

import com.intexsoft.stellarburgersweb.hooks.BaseTest;
import com.intexsoft.stellarburgersweb.page.LogInPage;
import com.intexsoft.stellarburgersweb.page.MainPage;
import org.junit.Assert;
import org.junit.Test;

public class PersonalPageNoAuthTest extends BaseTest {
    @Test
    public void goToPersonalPageWithoutLogInExpectToSeeLogInPage() {
        MainPage mainPage = new MainPage(driver);
        LogInPage logInPage = mainPage.getNavBar().clickPersonalPageLink(false);

        Assert.assertTrue("Log in page isn't open after clicking on personal page link without logging in", logInPage.isPageOpened());
    }
}
