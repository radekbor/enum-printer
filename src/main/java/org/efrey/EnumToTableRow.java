package org.efrey;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.maven.plugin.logging.Log;
import org.apache.maven.plugin.logging.SystemStreamLog;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EnumToTableRow {

    private final static Log log = new SystemStreamLog();

    private Predicate<Field> isEnumProperty = (Field field) -> {
        return !field.isEnumConstant() && !Modifier.isStatic(field.getModifiers());
    };


    public TableRow convert(Object enumConstant, List<String> fieldNames) {

        Field[] fields = enumConstant.getClass().getDeclaredFields();

        Map<String, Field> enumFields = Stream.of(fields)
                .filter(isEnumProperty)
                .peek(item -> log.debug(item.toString()))
                .collect(Collectors.toMap(Field::getName, Function.identity()));


        List<Object> columns = new ArrayList<>();
        for (String fieldName : fieldNames) {
            if (!enumFields.containsKey(fieldName)) {
                log.debug("Enum hasn't got field :" + fieldName);
                continue;
            }
            Field field = enumFields.get(fieldName);
            field.setAccessible(true);
            try {
                columns.add(field.get(enumConstant));
            } catch (Exception e) {
                log.debug("something get wrong");
            }
        }
        log.debug("columns size:" + columns.size());
        Object[] objects = convertColumns(columns);
        return new TableRow(objects);
    }

    private Object[] convertColumns(List<Object> columns) {
        Object[] result = new Object[columns.size()];
        for (int i = 0; i < columns.size(); i++) {
            log.debug("convertColumns columns:" + columns.toString());
            result[i] = columns.get(i);
        }
        return result;
    }
}
