package com.my.expense.enums;

public enum FinanceAction {
    EXPENSE_TYPE_CREATED("Expense Type Created"),
    EXPENSE_TYPE_UPDATED("Expense Type Updated"),
    EXPENSE_TYPE_DELETED("Expense Type Deleted"),
    EXPENSE_CREATED("Expense Created"),
    EXPENSE_UPDATED("Expense Updated"),
    EXPENSE_DELETED("Expense Deleted");

    private final String value;

    FinanceAction(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}