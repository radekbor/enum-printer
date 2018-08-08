package org.efrey;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class EnumToTableRowTest {

    EnumToTableRow converter = new EnumToTableRow();

    @Test
    public void shouldConvertEnumToTableWhenAllColumnsExist() {
        // Act
        TableRow row = converter.convert(SimpleEnum.A, Arrays.asList("name", "value", "abbrevation"));

        // Assert
        assertThat(row).isEqualTo(new TableRow("Value1", 1L, 'A'));
    }

}