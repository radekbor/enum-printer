package org.efrey;

enum SimpleEnum {
    A("Value1", 1L, 'A'),
    B("Value2", 2L, 'B'),
    C("Value3", 3L, 'C');

    private final String name;
    private final long value;
    private final char abbrevation;

    SimpleEnum(String name, long value, char abbrevation) {
        this.name = name;
        this.value = value;
        this.abbrevation = abbrevation;
    }


}
