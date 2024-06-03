package com.intexsoft.stellarburgersweb.hooks;

import com.intexsoft.stellarburgersweb.model.User;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;

import java.util.Optional;

public abstract class BaseAuthTest extends BaseTest {
    private String accessToken;

    @Before
    public void setUpUser() {
        createdUser = User.buildFakeUser();
        Response response = steps.registerUser(createdUser);
        Assert.assertEquals("Unexpected response to user creation request", 200, response.statusCode());
        accessToken = response.path("accessToken");
    }

    @After
    public void tearDownUser() {
        Optional<Response> responseOptional = steps.deleteCreatedUser(accessToken);
        if (responseOptional.isPresent()) {
            Response response = responseOptional.get();
            Assert.assertEquals("Unexpected response to user deletion request", response.statusCode(), 202);
        }
    }
}
