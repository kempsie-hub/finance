package com.my.expense.service;

import com.my.expense.dto.ExpenseTypeDTO;
import com.my.expense.entity.ExpenseType;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface ExpenseTypeService {
    List<ExpenseType> findAll();
    Optional<ExpenseType> findById(Long id);
    ExpenseTypeDTO save(ExpenseType expenseType, String application);
    void deleteById(Long id);
}
