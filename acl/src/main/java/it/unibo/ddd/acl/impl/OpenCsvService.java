package it.unibo.ddd.acl.impl;

import com.opencsv.*;
import com.opencsv.exceptions.CsvValidationException;
import it.unibo.ddd.acl.CsvService;
import it.unibo.ddd.acl.Row;
import it.unibo.ddd.acl.Table;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class OpenCsvService implements CsvService {

    private final char separator;
    private final char quote;

    public OpenCsvService(char separator, char quote) {
        this.separator = separator;
        this.quote = quote;
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
        throw new IllegalStateException("This service does not support comments!");
    }

    private CSVParser buildParser() {
        return new CSVParserBuilder()
                .withSeparator(this.separator)
                .withQuoteChar(this.quote)
                .build();
    }

    private CSVReader buildReader(final Reader reader) {
        return new CSVReaderBuilder(reader)
                .withSkipLines(0)
                .withCSVParser(this.buildParser())
                .build();
    }

    private CSVWriter buildWriter(final Writer writer) {
        return new CSVWriter(writer);
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
        return this.parse(new FileReader(path));
    }

    @Override
    public Table parse(Reader reader) throws IOException {
        try (final CSVReader csvReader = this.buildReader(reader)) {
            List<Row> rows = new LinkedList<>();
            String[] line;
            while ((line = csvReader.readNext()) != null) {
                rows.add(Row.of(line));
            }
            return Table.withHeaders(rows);
        } catch (final CsvValidationException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void write(Table table, Writer writer) throws IOException {
        try (final CSVWriter csvWriter = this.buildWriter(writer)) {
            table.forEach(r -> csvWriter.writeNext(r.items().toArray(new String[0])));
        }
    }

    @Override
    public void save(Table table, File path) throws IOException {
        this.write(table, new FileWriter(path));
    }

    @Override
    public String format(Table table) {
        throw new IllegalStateException("Not present CSV formatter"); // not sure!
    }
}
