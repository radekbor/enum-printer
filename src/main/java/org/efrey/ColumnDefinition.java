package org.efrey;

public class ColumnDefinition {

    private final String filedName;
    private final String columnName;
    private final int order;

    public ColumnDefinition(String filedName, String columnName, int order) {
        this.filedName = filedName;
        this.columnName = columnName;
        this.order = order;
    }

    public int getOrder() {
        return order;
    }

    public String getColumnName() {
        return columnName;
    }

    public String getFiledName() {
        return filedName;
    }
}
