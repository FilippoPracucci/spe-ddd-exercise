package it.unibo.ddd.acl;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

public interface CsvService {
    char getSeparatorChar(); // default to ,
    char getQuoteChar(); // default to "
    char getCommentChar(); // default to #

    // reading
    Table parseFromString(String input);
    Table parseFromFile(File path) throws IOException;
    Table parse(Reader reader) throws IOException;

    // writing
    void write(Table table, Writer writer) throws IOException;
    void save(Table table, File path) throws IOException;
    String format(Table table);
}
