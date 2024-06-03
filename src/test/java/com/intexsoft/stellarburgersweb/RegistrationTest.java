package com.intexsoft.stellarburgersweb;

import com.intexsoft.stellarburgersweb.hooks.BaseTest;
import com.intexsoft.stellarburgersweb.model.User;
import com.intexsoft.stellarburgersweb.page.LogInPage;
import com.intexsoft.stellarburgersweb.page.MainPage;
import com.intexsoft.stellarburgersweb.page.RegisterPage;
import org.junit.Assert;
import org.junit.Test;

public class RegistrationTest extends BaseTest {

    @Test
    public void testRegistrationFlowExpectSuccessfulRegistration() {
        User user = User.buildFakeUser();
        MainPage mainPage = new MainPage(driver);
        LogInPage logInPage = mainPage.clickLogInButton();
        RegisterPage registerPage = logInPage.clickRegisterLink();
        Assert.assertTrue("Cannot open registration page", registerPage.isPageOpened());

        registerPage.typeName(user.getName())
                .typeEmail(user.getEmail())
                .typePassword(user.getPassword())
                .clickRegister();

        String registrationTestMessage = "Log in page not opened after clicking register button therefore registration unsuccessful";
        Assert.assertTrue(registrationTestMessage, logInPage.isPageOpened());
    }

    @Test
    public void testPasswordErrorExpectPasswordErrorAppearing() {
        User user = User.buildFakeUser();
        user.setPassword("12345");
        MainPage mainPage = new MainPage(driver);
        LogInPage logInPage = mainPage.clickLogInButton();
        RegisterPage registerPage = logInPage.clickRegisterLink();
        Assert.assertTrue("Cannot open registration page", registerPage.isPageOpened());

        registerPage.typeName(user.getName())
                .typeEmail(user.getEmail())
                .typePassword(user.getPassword())
                .clickRegister();
        Assert.assertTrue("Incorrect password error is not present", registerPage.isIncorrectPasswordErrorPresent());
    }
}
