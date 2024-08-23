package org.chementsova.model;

public enum TicketClass {
    CLA ("cla"),
    STD ("std");

    private String value;

    TicketClass(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
