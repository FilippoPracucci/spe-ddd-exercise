package it.unibo.ddd.exercises.orders;

import it.unibo.ddd.exercises.customers.Customer;

import java.util.Date;
import java.util.function.Predicate;

public interface OrderRegistry {
    Iterable<Order> getAllOrders();

    Order findOrderByID(OrderID id);

    Iterable<Order> findOrderByCustomer(Predicate<Customer> customer);

    Iterable<Order> findOrderByTime(Predicate<Date> predicate);

    void addNewOrder(Order order);

    void updateOrder(Order order);

    void removeOrder(Order order);
}
