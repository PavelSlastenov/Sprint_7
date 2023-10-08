package services;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import models.Order;

public class OrderClient extends Client {
    protected final String ROOT = "/orders";

    @Step("Создание заказа")
    public ValidatableResponse create(Order order) {
        return spec()
                .body(order)
                .when()
                .post(ROOT)
                .then().log().all();
    }

    @Step("Получение списка заказов")
    public ValidatableResponse getOrderList() {
        return spec()
                .when()
                .get(ROOT)
                .then().log().all();
    }

    @Step("Отмена заказа")
    public ValidatableResponse cancel(int orderId) {
        String json = String.format("{\"track\": "+ orderId +"}");

        return spec()
                .body(json)
                .when()
                .put(ROOT + "/cancel")
                .then().log().all();
    }
}
