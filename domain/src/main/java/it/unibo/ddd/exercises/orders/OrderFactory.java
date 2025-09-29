package it.unibo.ddd.exercises.orders;

import it.unibo.ddd.exercises.customers.Customer;

import java.util.Date;

public interface OrderFactory {
    Order newOrderFor(Customer customer, Date when);
}
