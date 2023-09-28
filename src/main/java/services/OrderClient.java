package services;

import io.restassured.response.ValidatableResponse;
import models.Order;

import java.util.List;

public class OrderClient extends Client {
    protected final String ROOT = "/orders";

    public ValidatableResponse create(Order order) {
        return spec()
                .body(order)
                .when()
                .post(ROOT)
                .then().log().all();
    }

    public ValidatableResponse getOrderList(Order order) {
        return spec()
                .body(order)
                .when()
                .get(ROOT)
                .then().log().all();
    }

    public ValidatableResponse cancel(int orderId) {
        String json = String.format("{\"track\": "+ orderId +"}");

        return spec()
                .body(json)
                .when()
                .put(ROOT + "/cancel")
                .then().log().all();
    }
}
