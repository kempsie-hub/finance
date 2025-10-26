package com.my.expense.service.impl;

import com.my.expense.entity.ExpenseType;
import com.my.expense.enums.FinanceAction;
import com.my.expense.repository.ExpenseTypeRepository;
import com.my.expense.service.ExpenseTypeService;
import com.my.expense.service.SmsService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ExpenseTypeServiceImpl implements ExpenseTypeService {

    private final SmsService smsService;

    private final ExpenseTypeRepository expenseTypeRepository;

    @Override
    public List<ExpenseType> findAll() {
        smsService.sendSms(FinanceAction.EXPENSE_TYPE_CREATED, "Electricity Bill");
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