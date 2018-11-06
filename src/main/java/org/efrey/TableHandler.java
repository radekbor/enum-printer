package org.efrey;

import org.codehaus.plexus.util.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class TableHandler {


    public static final Collector<CharSequence, ?, String> JOINING_CELL = Collectors.joining(";");
    private final String path;

    public TableHandler(String path) {
        this.path = path;
    }


    private String cellsToRow(String[] row) {
        return Arrays.stream(row).collect(JOINING_CELL);
    }

    public void saveTable(String name, Table table) {

        StringBuilder stringBuilder = new StringBuilder();
        String header = cellsToRow(table.getColumnNames());
        stringBuilder.append(header).append("\n");

        String data = table.getRows().stream()
                .map(row -> {
                    String[] cells = convert(row.getColumns());
                    return cellsToRow(cells);
                })
                .map(row -> row.concat(";\n"))
                .collect(Collectors.joining());

        stringBuilder.append(data);

        try {
            FileUtils.fileWrite(new File(path.concat(name).concat(".csv")), stringBuilder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String[] convert(Object[] columns) {
        String[] result = new String[columns.length];
        for (int i = 0; i < columns.length; i++) {
            result[i] = columns[i].toString();
        }
        return result;
    }
}
