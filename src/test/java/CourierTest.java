import helpers.CourierGenerator;
import io.restassured.response.ValidatableResponse;
import models.Credentials;
import org.junit.After;
import org.junit.Test;
import services.CourierAssertions;
import services.CourierClient;

import java.util.Map;

public class CourierTest {

    private final CourierGenerator generator = new CourierGenerator();
    private final CourierClient client = new CourierClient();
    private final CourierAssertions check = new CourierAssertions();

    private int courierId;

    @After public void deleteCourier() {
        if (courierId > 0) {
            ValidatableResponse response = client.delete(courierId);
            check.deletedSuccessfully(response);
        }
    }

    @Test
    public void courier() {
        var courier = generator.random();
        ValidatableResponse creationResponse = client.create(courier);
        check.createdSuccessfully(creationResponse);

        Credentials creds = Credentials.from(courier);
        ValidatableResponse loginResponse = client.login(creds);
        courierId = check.loggedInSuccessfully(loginResponse);

        assert courierId > 100;
    }

    @Test public void creationFails() {
        var courier = generator.generic();
        courier.setPassword(null);

        ValidatableResponse loginResponse = client.create(courier);
        String message = check.creationFailed(loginResponse);
        assert !message.isBlank();
    }

    @Test public void loginFails() {
        ValidatableResponse loginResponse = client.login(Map.of("password", "null"));
        check.loginFailed(loginResponse);
    }
}
