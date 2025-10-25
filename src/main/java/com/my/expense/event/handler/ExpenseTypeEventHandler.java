package com.my.expense.event.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.my.expense.event.ExpenseTypeEvent;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.transaction.KafkaTransactionManager;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static com.my.expense.constant.ExpenseAppConstants.EXPENSE_TYPE_UPDATED;

@Slf4j
@Component
@AllArgsConstructor
public class ExpenseTypeEventHandler {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    private final ObjectMapper objectMapper;

    private final KafkaTransactionManager<String, Object> kafkaTransactionManager;

//    @Async
    @EventListener
//    @Transactional(value = "kafkaTransactionManager", rollbackFor = Exception.class)
    public void handleExpenseTypeEvent(ExpenseTypeEvent event) {
        try {
            ProducerRecord<String, Object> record = new ProducerRecord<>("welcome1", event.expenseTypeDTO().id().toString(), event);
            record.headers().add("messageId", UUID.randomUUID().toString().getBytes());
           if (event.operation().equalsIgnoreCase(EXPENSE_TYPE_UPDATED)) {
                throw new RuntimeException("unsupported operation " + EXPENSE_TYPE_UPDATED);
            }
          //  boolean isSuccessful = kafkaTemplate.executeInTransaction(t-> {
                kafkaTemplate.send("welcome1", event.expenseTypeDTO().id().toString(), event)
                        .whenComplete((result, ex) -> {
                                    if (ex != null) {
                                        log.error("Failed to send ExpenseTypeEvent to Kafka: {}", ex.getMessage());
                                    } else {
                                        log.info("ExpenseTypeEvent sent to Kafka topic {} partition {} with offset {}",
                                                result.getRecordMetadata().topic(),
                                                result.getRecordMetadata().partition(),
                                                result.getRecordMetadata().offset());
                                    }
                                }

                        );

              //  return true;
          //  });
        } catch (Exception e) {
            log.error("Error processing ExpenseTypeEvent: {}", e.getMessage());
            throw new RuntimeException(e);
        }
        // Example handling: log event details
       // System.out.println("Received ExpenseTypeEvent: " + event);
        // Add further processing logic here as needed
    }

}

