import helpers.CourierGenerator;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import models.Credentials;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import services.CourierAssertions;
import services.CourierClient;

import java.util.Map;

public class CourierTest {

    private final CourierGenerator generator = new CourierGenerator();
    private final CourierClient client = new CourierClient();
    private final CourierAssertions check = new CourierAssertions();

    private int courierId;

    @Test
    @DisplayName("Создание и авторизация курьера")
    public void courier() {
        var courier = generator.random();
        ValidatableResponse creationResponse = client.create(courier);
        check.createdSuccessfully(creationResponse);

        Credentials creds = Credentials.from(courier);
        ValidatableResponse loginResponse = client.login(creds);
        courierId = check.loggedInSuccessfully(loginResponse);

        Assert.assertTrue(courierId > 0);
    }

    @Test
    @DisplayName("Создание курьера - без обязательного поля password")
     public void creationWithoutPasswordFieldFails() {
        var courier = generator.generic();
        courier.setPassword(null);

        ValidatableResponse loginResponse = client.create(courier);
        String message = check.creationWithoutPasswordFieldFailed(loginResponse);
        Assert.assertTrue(!message.isBlank());
    }

    @Test
    @DisplayName("Повторное создание курьера - уже есть в БД")
     public void reCreationFails() {
        var courier = generator.random();

        ValidatableResponse loginResponse = client.create(courier);
        check.createdSuccessfully(loginResponse);

        ValidatableResponse reLoginResponse = client.create(courier);
        check.reCreationFailed(reLoginResponse);
    }

    @Test
    @DisplayName("Ошибка при авторизации")
    public void loginFails() {
        ValidatableResponse loginResponse = client.login(Map.of("password", "null"));
        check.loginFailed(loginResponse);
    }

    @After
    @Test
    @DisplayName("Удаление курьера")
    public void deleteCourier() {
        if (courierId > 0) {
            ValidatableResponse response = client.delete(courierId);
            check.deletedSuccessfully(response);
        }
    }
}
