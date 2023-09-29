package services;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static java.net.HttpURLConnection.*;
import static org.hamcrest.Matchers.*;

public class OrderAssertions {

    @Step("Проверка значений ответа - код 201")
    public int createdSuccessOrder(ValidatableResponse response) {
        response.assertThat()
                .statusCode(HTTP_CREATED)
                .body(not(empty()))
        ;
        int trackId = response.extract().path("track");
        return trackId;
    }

    @Step("Проверка значений ответа - код 200")
    public void getOrdersList(ValidatableResponse response) {
        response.assertThat()
                .statusCode(HTTP_OK)
//                .body("ok", is(true))
        ;
    }

    @Step("Проверка значений ответа - код 200")
    public void canceledSuccessOrder(ValidatableResponse response) {
        response.assertThat()
                .statusCode(HTTP_OK)
                .body("ok", is(true))
                ;
    }
}
