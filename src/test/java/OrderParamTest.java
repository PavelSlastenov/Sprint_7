import helpers.OrderGenerator;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import services.OrderAssertions;
import services.OrderClient;

import java.util.List;

@RunWith(Parameterized.class)
public class OrderParamTest {
    private final OrderGenerator generator = new OrderGenerator();
    private final OrderClient client = new OrderClient();
    private final OrderAssertions check = new OrderAssertions();

    private int orderId;

    private List<String> color;

    public OrderParamTest(List<String> color) {
        this.color = color;
    }

    @Parameterized.Parameters
    public static Object[][] dataGen() {
        return new Object[][] {
                {List.of("GRAY", "YELLOW")},
                {List.of("YELLOW")},
                {List.of("")},
        };
    }

    @After
    @DisplayName("Отмена заказа")
    public void cancelOrder() {
        ValidatableResponse response = client.cancel(orderId);
        check.canceledSuccessOrder(response);
    }

    @Test
    @DisplayName("Создание заказа")
    public void order() {
        var order = generator.generic(color);
        ValidatableResponse creationResponse = client.create(order);
        orderId = check.createdSuccessOrder(creationResponse);
    }
}
