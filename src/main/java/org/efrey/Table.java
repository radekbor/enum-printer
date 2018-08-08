package org.efrey;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Table {

    private String[] columnNames;

    private List<TableRow> rowList;

    public Table(List<ColumnDefinition> columns) {
        List<String> columnsNamesList = columns.stream()
                .sorted(Comparator.comparingInt(ColumnDefinition::getOrder))
                .map(ColumnDefinition::getColumnName)
                .collect(Collectors.toList());

        this.columnNames = columnsNamesList.toArray(new String[columnsNamesList.size()]);
        this.rowList = new ArrayList<>();
    }

    /* Getters */
    public String[] getColumnNames() {
        return columnNames.clone();
    }

    public List<TableRow> getRows() {
        return Collections.unmodifiableList(rowList);
    }

    public void addRow(TableRow row) {
        rowList.add(row);
    }
}
