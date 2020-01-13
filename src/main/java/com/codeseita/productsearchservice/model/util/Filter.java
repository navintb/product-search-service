package com.codeseita.productsearchservice.model.util;

public class Filter {

    private String property;
    private Operation operation;
    private Object value;

    public static enum Operation{
        GREATER_THAN_OR_EQUAL_TO, LESS_THAN_OR_EQUAL_TO, IN, EQUALS
    }

    public static Filter by(String property) {
        Filter filter = new Filter();
        filter.property = property;
        return filter;
    }

    public Filter with(Object value) {
        this.value = value;
        return this;
    }

    public Filter on(Operation operation) {
        this.operation = operation;
        return this;
    }

    public String getProperty() {
        return property;
    }

    public Operation getOperation() {
        return operation;
    }

    public Object getValue() {
        return value;
    }
}