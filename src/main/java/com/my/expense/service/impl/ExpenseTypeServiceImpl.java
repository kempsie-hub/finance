package com.my.expense.service.impl;

import com.my.expense.entity.ExpenseType;
import com.my.expense.repository.ExpenseTypeRepository;
import com.my.expense.service.ExpenseTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExpenseTypeServiceImpl implements ExpenseTypeService {

    private final ExpenseTypeRepository expenseTypeRepository;

    @Autowired
    public ExpenseTypeServiceImpl(ExpenseTypeRepository expenseTypeRepository) {
        this.expenseTypeRepository = expenseTypeRepository;
    }

    @Override
    public List<ExpenseType> findAll() {
        return expenseTypeRepository.findAll();
    }

    @Override
    public Optional<ExpenseType> findById(Long id) {
        return expenseTypeRepository.findById(id);
    }

    @Override
    public ExpenseType save(ExpenseType expenseType) {
        return expenseTypeRepository.save(expenseType);
    }

    @Override
    public void deleteById(Long id) {
        expenseTypeRepository.deleteById(id);
    }
}