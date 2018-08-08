package org.efrey;

import org.apache.maven.plugin.logging.Log;
import org.apache.maven.plugin.logging.SystemStreamLog;

import java.util.*;
import java.util.stream.Collectors;

public class EnumHandler {

    public static final String SINGLE_FILE_NAME = "main";

    private final static Log log = new SystemStreamLog();

    private final List<ColumnDefinition> columns;

    private EnumToTableRow enumToTableRow = new EnumToTableRow();

    public EnumHandler(Configuration configuration) {
        columns = configuration.getColumns();
    }

    /**
     * Generate Files
     */
    public Map<String, Table> generateTables(Class aClass) {
        Map<String, Table> result = new HashMap<>();
        if (!aClass.isEnum()) {
            throw new IllegalArgumentException("Class must be enum");
        }
        Object[] enumConstants = aClass.getEnumConstants();
//        if (configuration.getGroupping() != null) {
//            TODO handle it
//        }

        Table table = new Table(columns);


        List<String> sortedColumns = columns.stream()
                .sorted(Comparator.comparingInt(ColumnDefinition::getOrder))
                .map(ColumnDefinition::getFiledName)
                .collect(Collectors.toList());

        for (Object enumConstant : enumConstants) {
            TableRow row = enumToTableRow.convert(enumConstant, sortedColumns);
            table.addRow(row);
            log.info(enumConstant.toString());
            log.info(row.toString());
        }

        result.put(EnumHandler.SINGLE_FILE_NAME, table);
        return result;
    }
}
