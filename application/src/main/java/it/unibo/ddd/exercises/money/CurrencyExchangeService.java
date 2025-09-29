package it.unibo.ddd.exercises.money;

import java.util.Date;

public interface CurrencyExchangeService {
    ExchangeRate getCurrentExchangeRateAmong(Currency source, Currency destination);

    ExchangeRate getExchangeRateAmong(Currency source, Currency destination, Date when);
}
