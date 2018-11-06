package org.radekbor;

public class Wrapper {

    public Wrapper(String value) {
        this.value = value;
    }

    private String value;

    @Override
    public String toString() {
        return String.format("Wrapper[%s]", value);
    }
}
