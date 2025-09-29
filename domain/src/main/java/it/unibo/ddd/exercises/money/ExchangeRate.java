package it.unibo.ddd.exercises.money;

import java.util.Date;

public interface ExchangeRate {
    Currency getSourceCurrency();
    Currency getDestinationCurrency();
    Double getRate();
    Date getMoment();
}
