package com.my.expense.event;

import com.my.expense.dto.ExpenseTypeDTO;

public record ExpenseTypeEvent(ExpenseTypeDTO expenseTypeDTO, String application, String operation) {
}
