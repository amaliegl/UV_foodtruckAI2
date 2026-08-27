package dk.zealand.service;

import dk.zealand.domain.Dish;
import dk.zealand.domain.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderService {
    public static final int MAX_ORDERS = 10;

    private final List<Order> orders = new ArrayList<>();
    private long nextOrderId = 1;

    public Order createOrder(Dish dish, int quantity) {
        if (orders.size() >= MAX_ORDERS) {
            return null;
        }

        Order order = new Order(nextOrderId++, dish, quantity);
        orders.add(order);
        return order;
    }

    public List<Order> getOrders() {
        return List.copyOf(orders);
    }

    public int getOrderCount() {
        return orders.size();
    }
}
