package it.unibo.ddd.exercises.money;

public interface CurrencyFactory {
    Currency newCurrency(String name, String symbol, String acronym);
}
