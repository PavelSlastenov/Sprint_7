import helpers.OrderGenerator;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Assert;
import org.junit.Test;
import services.OrderAssertions;
import services.OrderClient;

import java.util.Collections;

public class OrderTest {
    private final OrderGenerator generator = new OrderGenerator();
    private final OrderClient client = new OrderClient();
    private final OrderAssertions check = new OrderAssertions();

    @Test
    @DisplayName("Получение списка заказов")
    public void  getOrderList() {
        var order = generator.generic(Collections.singletonList(""));
        ValidatableResponse creationResponse = client.create(order);
        check.createdSuccessOrder(creationResponse);

        ValidatableResponse response = client.getOrderList();
        check.getOrdersList(response);
        Assert.assertNotEquals(null, response);
    }
}
