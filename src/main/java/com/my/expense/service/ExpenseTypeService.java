package com.my.expense.service;

import com.my.expense.entity.ExpenseType;

import java.util.List;
import java.util.Optional;

public interface ExpenseTypeService {
    List<ExpenseType> findAll();
    Optional<ExpenseType> findById(Long id);
    ExpenseType save(ExpenseType expenseType);
    void deleteById(Long id);
}
