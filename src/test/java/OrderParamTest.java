import helpers.CourierGenerator;
import helpers.OrderGenerator;
import io.restassured.response.ValidatableResponse;
import models.Credentials;
import models.Order;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import services.CourierAssertions;
import services.CourierClient;
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
    public void cancelOrder() {
        ValidatableResponse response = client.cancel(orderId);
        check.canceledSuccessOrder(response);
    }

    @Test
    public void order() {
        var order = generator.generic(color);
        ValidatableResponse creationResponse = client.create(order);
        orderId = check.createdSuccessOrder(creationResponse);

//        Order orders = Order.from(order);
//        ValidatableResponse loginResponse = client.login(creds);
//        orderId = check.createdSuccessOrder(loginResponse);

//        assert order > 100;
    }

    @Test public void checkEm() {
        System.out.println(color);
    }
}
