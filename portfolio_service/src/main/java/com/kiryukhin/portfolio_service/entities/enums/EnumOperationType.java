package com.kiryukhin.portfolio_service.entities.enums;

public enum EnumOperationType {
    BUY ("buy"),
    SELL ("sell");
    private final String name;

    EnumOperationType(String name) {
        this.name = name;
    }

    public boolean equalsName(String otherName) {
        return name.equalsIgnoreCase(otherName);
    }

    public String toString() {
        return this.name;
    }
}
