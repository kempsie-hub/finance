package com.my.expense.event.handler;

import com.my.expense.event.ExpenseTypeEvent;
import com.my.expense.exception.NotRetryableException;
import com.my.expense.exception.RetryableException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@KafkaListener(topics = "welcome1")
public class ExpenseTypeEventConsumer {

    @KafkaHandler
    public void consume(@Payload ExpenseTypeEvent expenseTypeEvent, @Header("messageId") String messageId, @Header(KafkaHeaders.RECEIVED_KEY) String key) {
        System.out.println("Message ID: " + messageId);
        System.out.println("Key: " + key);
        if("ABC".equalsIgnoreCase(expenseTypeEvent.application())){
            throw new NotRetryableException("Not a valid expense type event");
        }
        if("XYZ".equalsIgnoreCase(expenseTypeEvent.application())){
            log.error("Not a valid expense type event, throwing RetryableException");
            throw new RetryableException("Not a valid expense type event");
        }
        System.out.println("**********************************************");
        log.info("Consumed message from 'welcome' topic: {}", expenseTypeEvent);
        System.out.println("**********************************************");
        // Add further processing logic here as needed
    }

}
