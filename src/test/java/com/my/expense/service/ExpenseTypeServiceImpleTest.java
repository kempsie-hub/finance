package com.my.expense.service;

import com.my.expense.dto.ExpenseTypeDTO;
import com.my.expense.entity.ExpenseType;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.util.Map;

@DirtiesContext
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@EmbeddedKafka(partitions = 3, count = 3, controlledShutdown = true)
@SpringBootTest(
        properties = {
                "spring.kafka.producer.bootstrap-servers=${spring.embedded.kafka.brokers}",
                "spring.kafka.admin.properties.bootstrap.servers=${spring.embedded.kafka.brokers}",
                "spring.kafka.consumer.bootstrap-servers=${spring.embedded.kafka.brokers}"
        }
)
public class ExpenseTypeServiceImpleTest {

    @Autowired
    ExpenseTypeService expenseTypeService;

    @Autowired
    private EmbeddedKafkaBroker embeddedKafkaBroker;

    @Test
    void testMessagePublish(){
        //Act
        ExpenseType expenseType = ExpenseType.builder()
                .id(1L)
                .name("Travel")
                .description("Travel related expenses")
                .build();
        expenseTypeService.save(expenseType,"test");
        Assumptions.assumeTrue(true);


    }

    private Map<String,Object> getConsumerProperties(){
        return Map.of(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,embeddedKafkaBroker.getBrokersAsString(),
                ConsumerConfig.GROUP_ID_CONFIG,"expenseTypeGroup",
                ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,"earliest"
                );
    }

}
