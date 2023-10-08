import helpers.OrderGenerator;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
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

    @Test
    @DisplayName("Создание заказа")
    public void order() {
        var order = generator.generic(color);
        ValidatableResponse creationResponse = client.create(order);
        check.createdSuccessOrder(creationResponse);
    }
}