package it.unibo.ddd.exercises.money;

import java.math.BigDecimal;

public interface Money {
    BigDecimal getValue();
    Currency getCurrency();
}
