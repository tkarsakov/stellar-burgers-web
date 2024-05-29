package com.intexsoft.stellarburgersweb.api;

import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.List;

import static io.restassured.RestAssured.given;

public class RequestFactory {
    public Response sendRequest(Method method, String endpointURL, List<RequestParameter> requestParameterList) {
        RequestSpecification requestSpecification = createRequest(requestParameterList);
        return requestSpecification
                .request(method.name(), endpointURL).
                then()
                .extract()
                .response();
    }

    public RequestSpecification createRequest(List<RequestParameter> requestParameterList) {
        RequestSpecification requestSpecification = given();
        for (RequestParameter requestParameter : requestParameterList) {
            inputParameterIntoSpec(requestSpecification, requestParameter);
        }
        return requestSpecification;
    }

    private void inputParameterIntoSpec(RequestSpecification requestSpecification, RequestParameter requestParameter) {
        switch (requestParameter.getParameterType()) {
            case HEADER:
                for (int i = 0; i < requestParameter.getParameters().size(); i += 2) {
                    requestSpecification.header(new Header(requestParameter.getParameters().get(i), requestParameter.getParameters().get(i + 1)));
                }
                break;
            case BODY:
                requestSpecification.body(requestParameter.getParameters().get(0));
                break;
            default:
                throw new RuntimeException("Parameter type not implemented");
        }
    }
}
