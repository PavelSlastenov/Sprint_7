package services;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static java.net.HttpURLConnection.*;
import static org.hamcrest.Matchers.*;

public class CourierAssertions {
    @Step("Проверка значений ответа - код 201")
    public void createdSuccessfully(ValidatableResponse response) {
        response.assertThat()
                .statusCode(HTTP_CREATED)
                .body("ok", is(true));

    }

    @Step("Проверка значений ответа - код 200")
    public int loggedInSuccessfully(ValidatableResponse response) {
        return response.assertThat()
                .statusCode(HTTP_OK)
                .body("id", greaterThan(0))
                .extract()
                .path("id");

    }

    @Step("Проверка значений ответа - код 400")
    public String creationWithoutPasswordFieldFailed(ValidatableResponse response) {
        return response.assertThat()
                .statusCode(HTTP_BAD_REQUEST)
                .body("message", notNullValue())
                .extract()
                .path("message");
    }

    @Step("Проверка значений ответа - код 409")
    public String reCreationFailed(ValidatableResponse response) {
        return response.assertThat()
                .statusCode(HTTP_CONFLICT)
                .body("message", equalTo("Этот логин уже используется. Попробуйте другой."))
                .extract()
                .path("message");
    }

    @Step("Проверка значений ответа - код 400")
    public void loginFailed(ValidatableResponse response) {
        response.assertThat()
                .statusCode(HTTP_BAD_REQUEST)
                .body("message", notNullValue());
    }

    @Step("Проверка значений ответа - код 200")
    public void deletedSuccessfully(ValidatableResponse response) {
        response.assertThat()
                .statusCode(HTTP_OK)
                .body("ok", is(true));
    }
}
