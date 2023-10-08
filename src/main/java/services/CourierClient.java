package services;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import models.Courier;
import models.Credentials;

import java.util.Map;

public class CourierClient extends Client {
    protected final String ROOT = "/courier";

    @Step("Создание курьера")
    public ValidatableResponse create(Courier courier) {
        return spec()
                .body(courier)
                .when()
                .post(ROOT)
                .then().log().all();
    }

    @Step("Авторизация курьера")
    public ValidatableResponse login(Credentials creds) {
        return spec()
                .body(creds)
                .when()
                .post(ROOT + "/login")
                .then().log().all();
    }

    @Step("Авторизация курьера")
    public ValidatableResponse login(Map<String, String> creds) {
        return spec()
                .body(creds)
                .when()
                .post(ROOT + "/login")
                .then().log().all();
    }

    @Step("Удвление курьера")
    public ValidatableResponse delete(int courierId) {
        String json = String.format("{\"id\": \"%d\"}", courierId);

        return spec()
                .body(json)
                .when()
                .delete(ROOT + "/" + courierId)
                .then().log().all();
    }
}
