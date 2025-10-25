package com.my.expense.util;

import com.my.expense.dto.ExpenseTypeDTO;
import com.my.expense.event.ExpenseTypeEvent;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import static com.my.expense.constant.ExpenseAppConstants.APPLICATION_NAME;

@Component
@AllArgsConstructor
public class EventPublisher {

    private final ApplicationEventPublisher eventPublisher;

    public void publishEvent(ExpenseTypeDTO expenseTypeDTO, String application,String operation) {
        eventPublisher.publishEvent(
                new ExpenseTypeEvent(expenseTypeDTO,application,
                        operation));
    }
}
