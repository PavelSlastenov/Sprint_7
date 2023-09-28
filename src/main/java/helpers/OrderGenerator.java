package helpers;

import models.Courier;
import models.Order;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.List;

public class OrderGenerator {
    public Order generic(List<String> color) {
        return new Order("Lion", "Lalala", "Planet", "Mars", "2346578", 14, "2020-06-06", "Saske, come back to Konoha", color);
    }

//    public Order random() {
//        return new Order(RandomStringUtils.randomAlphanumeric(10), "P@ssw0rd123", "Sparrow");
//    }
}
