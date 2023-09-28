package services;

import io.restassured.response.ValidatableResponse;

import static java.net.HttpURLConnection.*;
import static org.hamcrest.Matchers.*;

public class OrderAssertions {

    public int createdSuccessOrder(ValidatableResponse response) {
        response.assertThat()
                .statusCode(HTTP_CREATED)
                .body(not(empty()))
        ;
        int trackId = response.extract().path("track");
        return trackId;
    }

    public void getOrdersList(ValidatableResponse response) {
        response.assertThat()
                .statusCode(HTTP_OK)
//                .body("ok", is(true))
        ;
    }

    public void canceledSuccessOrder(ValidatableResponse response) {
        response.assertThat()
                .statusCode(HTTP_OK)
                .body("ok", is(true))
                ;
    }
}
