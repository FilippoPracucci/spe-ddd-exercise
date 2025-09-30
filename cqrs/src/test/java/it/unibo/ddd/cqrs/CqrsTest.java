package it.unibo.ddd.cqrs;

import it.unibo.ddd.Variation;
import it.unibo.ddd.cqrs.impl.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CqrsTest {

    private static final CounterWriter writer = new CounterWriterImpl();
    private final CounterReader reader = new CounterReaderImpl(writer);
    private static List<Variation> commands = new LinkedList<>();
    private final static long date = System.currentTimeMillis();

    @BeforeAll
    public static void setUp() {
        commands = List.of(
                Variation.of(0, 1, new Date(date)),
                Variation.of(1, 2, new Date(date + 1000)),
                Variation.of(2, 4, new Date(date + 5000))
        );
        commands.forEach(writer::setValue);
    }

    @Test
    public void testGetVariations() {
        assertEquals(commands, writer.getVariations());
    }

    @Test
    public void testGetValue() {
        assertEquals(0, reader.getValue(new Date(0)));
        assertEquals(2, reader.getValue(new Date(date + 1000)));
        assertEquals(1, reader.getValue(new Date(date + 500)));
    }

}
