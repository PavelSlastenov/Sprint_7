import helpers.OrderGenerator;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Test;
import services.OrderAssertions;
import services.OrderClient;

import java.util.Collections;

public class OrderTest {
    private final OrderGenerator generator = new OrderGenerator();
    private final OrderClient client = new OrderClient();
    private final OrderAssertions check = new OrderAssertions();

    private int orderId;

//    @After
//    @DisplayName("Отмена заказа")
//    public void cancelOrder() {
//        ValidatableResponse response = client.cancel(orderId);
//        check.canceledSuccessOrder(response);
//    }

    @Test
    @DisplayName("Получение списка заказов")
    public void  getOrderList() {
        var order = generator.generic(Collections.singletonList(""));
        ValidatableResponse creationResponse = client.create(order);
        orderId = check.createdSuccessOrder(creationResponse);

        ValidatableResponse loginResponse = client.getOrderList(orderId);
        check.getOrdersList(creationResponse);
//        assert !message.isBlank();
    }
}
