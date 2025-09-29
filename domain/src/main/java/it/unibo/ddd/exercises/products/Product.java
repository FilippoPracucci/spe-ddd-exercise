package it.unibo.ddd.exercises.products;

import it.unibo.ddd.exercises.money.Money;

public interface Product {
    ProductID getID();
    Money getPrice();
    void setPrice(Money price);
    double getAvailability();
    void setAvailability(double quantity);
}
