package org.chementsova.model;

public enum TicketType {
    MONTH ("MONTH"),
    WEEK ("WEEK"),
    YEAR ("YEAR"),
    DAY ("DAY");

    private String value;

    TicketType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}