package it.unibo.ddd.acl.impl;

import it.unibo.ddd.acl.CsvService;
import it.unibo.ddd.acl.Row;
import it.unibo.ddd.acl.Table;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.util.stream.Collectors;

public class CommonCsvService implements CsvService {

    private final char separator;
    private final char quote;
    private final char comment;

    public CommonCsvService(char separator, char quote, char comment) {
        this.separator = separator;
        this.quote = quote;
        this.comment = comment;
    }

    @Override
    public char getSeparatorChar() {
        return this.separator;
    }

    @Override
    public char getQuoteChar() {
        return this.quote;
    }

    @Override
    public char getCommentChar() {
        return this.comment;
    }

    private CSVFormat getFormat() {
        return CSVFormat.DEFAULT.builder()
                .setSkipHeaderRecord(false)
                .setRecordSeparator(this.separator)
                .setQuote(this.quote)
                .setCommentMarker(this.comment)
                .get();
    }

    private Row recordToRow(final CSVRecord record) {
        return Row.of(record.toList());
    }

    @Override
    public Table parseFromString(String input) {
        try {
            return this.parse(new StringReader(input));
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Table parseFromFile(File path) throws IOException {
        try (final FileReader reader = new FileReader(path)) {
            return parse(reader);
        }
    }

    @Override
    public Table parse(Reader reader) throws IOException {
        final CSVParser parser = this.getFormat().parse(reader);
        return Table.withHeaders(
                parser.stream().map(this::recordToRow).collect(Collectors.toList())
        );
    }

    @Override
    public void write(Table table, Writer writer) throws IOException {
        final CSVPrinter printer = this.getFormat().print(writer);
        printer.printRecords(table.stream().map(r -> r.items().toArray()));
    }

    @Override
    public void save(Table table, File path) throws IOException {
        try (final Writer writer = new FileWriter(path)) {
            write(table, writer);
        }
    }

    @Override
    public String format(Table table) {
        return CSVFormat.DEFAULT.format(table.getEntries());
    }
}
