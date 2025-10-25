package com.my.expense.config;

import com.my.expense.exception.NotRetryableException;
import com.my.expense.exception.RetryableException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.util.backoff.FixedBackOff;

@Slf4j
@Configuration
public class KafkaConfig {


/*    @Bean
    public DefaultErrorHandler defaultErrorHandler(KafkaTemplate<Object, Object> kafkaTemplate) {
      log.info("_____________________________________________________"+kafkaTemplate);
        DeadLetterPublishingRecoverer recoverer = new DeadLetterPublishingRecoverer(kafkaTemplate);

        // Retry 5 times with a 2-second delay before sending to the DLT
        FixedBackOff fixedBackOff = new FixedBackOff(2000L, 5L);
        return new DefaultErrorHandler(recoverer, fixedBackOff);
    }*/

    @Bean
    public ConcurrentKafkaListenerContainerFactory kafkaListenerContainerFactory(ConsumerFactory<String, Object> consumerFactory,
                                                                            KafkaTemplate<String, Object> kafkaTemplate) {
        ConcurrentKafkaListenerContainerFactory<String, Object> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory);
        DefaultErrorHandler errorHandler = new DefaultErrorHandler(new DeadLetterPublishingRecoverer(kafkaTemplate), new FixedBackOff(5000L, 3));
        errorHandler.addNotRetryableExceptions(NotRetryableException.class);
        errorHandler.addRetryableExceptions(RetryableException.class);
        factory.setCommonErrorHandler(errorHandler);
        factory.setConsumerFactory(consumerFactory);
        return factory;
    }




}
