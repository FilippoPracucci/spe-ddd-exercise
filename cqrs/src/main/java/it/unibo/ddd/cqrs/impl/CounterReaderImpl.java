package it.unibo.ddd.cqrs.impl;

import it.unibo.ddd.Variation;
import it.unibo.ddd.cqrs.CounterReader;
import it.unibo.ddd.cqrs.CounterWriter;

import java.util.Date;

public class CounterReaderImpl implements CounterReader {

    private final CounterWriter counterWriter;

    public CounterReaderImpl(final CounterWriter counterWriter) {
        this.counterWriter = counterWriter;
    }

    @Override
    public long getValue(final Date at) {
        long value = 0L;
        for (final Variation variation: this.counterWriter.getVariations()) {
            if (variation.before() != value) {
                continue;
            }
            if (variation.when().after(at)) {
                return value;
            }
            value = variation.after();
        }
        return value;
    }
}
