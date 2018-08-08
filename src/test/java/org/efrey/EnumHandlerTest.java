package org.efrey;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class EnumHandlerTest {

    @Test
    void shouldReturnProperTable() {
        // Given
        Configuration configuration = Configuration.Builder()
                .addColumn(new ColumnDefinition("abbrevation", "ABBREVATION", 1))
                .addColumn(new ColumnDefinition("name", "ENUM NAME", 2))
                .addColumn(new ColumnDefinition("value", "VALUE", 3))
                .build();
        EnumHandler enumHandler = new EnumHandler(configuration);

        // When
        Map<String, Table> tables = enumHandler.generateTables(SimpleEnum.class);

        // Then
        assertThat(tables).containsKey(EnumHandler.SINGLE_FILE_NAME);
        Table table = tables.get(EnumHandler.SINGLE_FILE_NAME);

        assertThat(table.getColumnNames()).containsSequence("ABBREVATION", "ENUM NAME", "VALUE");
        assertThat(table.getRows()).contains(
                new TableRow('A', "Value1", 1L),
                new TableRow('B', "Value2", 2L),
                new TableRow('C', "Value3", 3L)
        );
    }

}

