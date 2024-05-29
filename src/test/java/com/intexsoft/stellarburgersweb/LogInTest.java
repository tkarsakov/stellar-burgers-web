package com.intexsoft.stellarburgersweb;

import com.intexsoft.stellarburgersweb.page.LogInPage;
import com.intexsoft.stellarburgersweb.page.MainPage;
import com.intexsoft.stellarburgersweb.page.RegisterPage;
import com.intexsoft.stellarburgersweb.page.RestorePasswordPage;
import org.junit.Assert;
import org.junit.Test;

public class LogInTest extends BaseTest {

    @Test
    public void logInFromMainPageLogInButtonExpectSuccessfulLogin() {
        MainPage mainPage = new MainPage(driver);
        LogInPage logInPage = mainPage.clickLogInButton();

        logInPage.typeEmail(createdUser.getEmail())
                .typePassword(createdUser.getPassword())
                .clickLogInButton();

        Assert.assertTrue("Main page isn't open after logging in", mainPage.isPageOpened());
        Assert.assertTrue("User isn't logged in or can't see 'Create Order' button which they are supposed to see", mainPage.isUserSeeingCreateOrderButton());
    }

    @Test
    public void logInUsingPersonalPageButtonExpectSuccessfulLogin() {
        MainPage mainPage = new MainPage(driver);
        LogInPage logInPage = mainPage.getNavBar().clickPersonalPageLink(false);

        logInPage.typeEmail(createdUser.getEmail())
                .typePassword(createdUser.getPassword())
                .clickLogInButton();

        Assert.assertTrue("Main page isn't open after logging in", mainPage.isPageOpened());
        Assert.assertTrue("User isn't logged in or can't see 'Create Order' button which they are supposed to see", mainPage.isUserSeeingCreateOrderButton());
    }

    @Test
    public void logInUsingRegistrationPageLogInLinkExpectSuccessfulLogin() {
        MainPage mainPage = new MainPage(driver);
        LogInPage logInPage = mainPage.clickLogInButton();
        RegisterPage registerPage = logInPage.clickRegisterLink();
        registerPage.clickLogInLink();

        logInPage.typeEmail(createdUser.getEmail())
                .typePassword(createdUser.getPassword())
                .clickLogInButton();

        Assert.assertTrue("Main page isn't open after logging in", mainPage.isPageOpened());
        Assert.assertTrue("User isn't logged in or can't see 'Create Order' button which they are supposed to see", mainPage.isUserSeeingCreateOrderButton());
    }

    @Test
    public void logInUsingRestorePasswordPageLogInLinkExpectSuccessfulLogin() {
        MainPage mainPage = new MainPage(driver);
        LogInPage logInPage = mainPage.clickLogInButton();
        RestorePasswordPage restorePasswordPage = logInPage.clickRestorePasswordLink();
        restorePasswordPage.clickLogInLink();

        logInPage.typeEmail(createdUser.getEmail())
                .typePassword(createdUser.getPassword())
                .clickLogInButton();

        Assert.assertTrue("Main page isn't open after logging in", mainPage.isPageOpened());
        Assert.assertTrue("User isn't logged in or can't see 'Create Order' button which they are supposed to see", mainPage.isUserSeeingCreateOrderButton());
    }
}
