package org.example.csv_worker;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Writer;

public class CsvBufferedWriter extends BufferedWriter {

    public CsvBufferedWriter(Writer out) {
        super(out);
    }

    @Override
    public void write(String c) {
        try {
            super.write(c);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void newLine() {
        try {
            super.newLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void writeWithNewLine(String c) {
        this.newLine();
        this.write(c);
    }
}
