package it.unibo.ddd.cqrs.impl;

import it.unibo.ddd.Variation;
import it.unibo.ddd.cqrs.CounterWriter;

import java.util.LinkedList;
import java.util.List;

public class CounterWriterImpl implements CounterWriter {

    private final List<Variation> commands = new LinkedList<>();

    @Override
    public void setValue(final Variation delta) {
        this.commands.add(delta);
    }

    @Override
    public Iterable<Variation> getVariations() {
        return this.commands;
    }
}
