package helpers;

import models.Order;

import java.util.List;

public class OrderGenerator {
    public Order generic(List<String> color) {
        return new Order("Lion", "Lalala", "Planet", "Mars", "2346578", 14, "2020-06-06", "Saske, come back to Konoha", color);
    }
}
