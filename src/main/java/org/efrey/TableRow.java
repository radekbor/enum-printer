package org.efrey;

import java.util.Arrays;

public class TableRow {

    private Object[] columns;

    public TableRow(Object... columns) {
        this.columns = Arrays.copyOf(columns, columns.length);
    }

    /* Getters */
    public Object[] getColumns() {
        return Arrays.copyOf(columns, columns.length);
    }

    @Override
    public String toString() {
        return Arrays.toString(columns);
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof TableRow)) {
            return false;
        }
        TableRow tableRow = (TableRow) object;

        return Arrays.equals(this.columns, tableRow.columns);
    }
}
