package com.intexsoft.stellarburgersweb.api;

import com.intexsoft.stellarburgersweb.model.User;
import com.intexsoft.stellarburgersweb.util.JSONUtil;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import java.util.List;
import java.util.Optional;

import static com.intexsoft.stellarburgersweb.api.Method.DELETE;
import static com.intexsoft.stellarburgersweb.api.Method.POST;


public class Steps {
    private static final String AUTH_USER = "https://stellarburgers.nomoreparties.site/api/auth/user";
    private static final String AUTH_REGISTER = "https://stellarburgers.nomoreparties.site/api/auth/register";

    private final RequestFactory requestFactory = new RequestFactory();

    @Step("Регистрация пользователя {user}")
    public Response registerUser(User user) {
        RequestParameter requestBody = new RequestParameter(ParameterType.BODY);
        RequestParameter requestHeader = new RequestParameter(ParameterType.HEADER);
        requestHeader.addParameters("Content-Type", "application/json");
        requestBody.addParameters(JSONUtil.convertToJSONString(user));
        return requestFactory.sendRequest(POST, AUTH_REGISTER, List.of(requestHeader, requestBody));
    }

    @Step("Удаление текущего пльзователя с токеном {accessToken}")
    public Optional<Response> deleteCreatedUser(String accessToken) {
        if (accessToken != null) {
            RequestParameter requestHeader = new RequestParameter(ParameterType.HEADER);
            requestHeader.addParameters("Authorization", accessToken);
            return Optional.of(requestFactory.sendRequest(DELETE, AUTH_USER, List.of(requestHeader)));
        }
        return Optional.empty();
    }
}
