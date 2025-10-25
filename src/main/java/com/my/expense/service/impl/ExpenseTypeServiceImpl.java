package com.my.expense.service.impl;

import com.my.expense.dto.ExpenseTypeDTO;
import com.my.expense.entity.ExpenseType;
import com.my.expense.mapper.ExpenseTypeMapperUtil;
import com.my.expense.repository.ExpenseTypeRepository;
import com.my.expense.service.ExpenseTypeService;
import com.my.expense.util.EventPublisher;
import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.transaction.KafkaTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

import static com.my.expense.constant.ExpenseAppConstants.EXPENSE_TYPE_CREATED;
import static com.my.expense.constant.ExpenseAppConstants.EXPENSE_TYPE_UPDATED;

@Service
@AllArgsConstructor
public class ExpenseTypeServiceImpl implements ExpenseTypeService {

    private final ExpenseTypeRepository expenseTypeRepository;

    private final EventPublisher eventPublisher;

    private final ExpenseTypeMapperUtil mapperUtil;

    private final KafkaTransactionManager<String, Object> kafkaTransactionManager;

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Override
    public List<ExpenseType> findAll() {
        return expenseTypeRepository.findAll();
    }

    @Override
    public Optional<ExpenseType> findById(Long id) {
        return expenseTypeRepository.findById(id);
    }

    @Override
    @Transactional(value = "kafkaTransactionManager", rollbackFor = Exception.class)
    public ExpenseTypeDTO save(ExpenseType expenseType, String application) {
        AtomicReference<ExpenseTypeDTO> expenseTypeDTO = new AtomicReference<>();

            String operation = (expenseType.getId() == null) ? EXPENSE_TYPE_CREATED: EXPENSE_TYPE_UPDATED;
            ExpenseType savedExpenseType = expenseTypeRepository.save(expenseType);
            expenseTypeDTO.set(mapperUtil.mapToExpenseTypeDTO(savedExpenseType));
            eventPublisher.publishEvent(expenseTypeDTO.get(), application, operation);
            eventPublisher.publishEvent(expenseTypeDTO.get(), application, EXPENSE_TYPE_UPDATED);


        return expenseTypeDTO.get();
    }

    @Override
    public void deleteById(Long id) {
        expenseTypeRepository.deleteById(id);
    }
}