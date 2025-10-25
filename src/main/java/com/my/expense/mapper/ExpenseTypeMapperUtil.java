package com.my.expense.mapper;

import com.my.expense.dto.ExpenseTypeDTO;
import com.my.expense.entity.ExpenseType;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ExpenseTypeMapperUtil {
    private final ModelMapper modelMapper;

    public ExpenseTypeDTO mapToExpenseTypeDTO(ExpenseType expenseType) {
        if (expenseType == null) {
            return null;
        }
        return new ExpenseTypeDTO(
                1L,
                expenseType.getName(),
                expenseType.getDescription(),
                expenseType.getStatus(),
                expenseType.getCreatedBy(),
                expenseType.getUpdatedBy(),
                expenseType.getCreatedDate(),
                expenseType.getUpdatedDate(),
                expenseType.isRegistrationRequired()
        );
    }
}
