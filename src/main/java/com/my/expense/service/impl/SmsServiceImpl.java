package com.my.expense.service.impl;

import com.my.expense.config.TwilioConfigurationProperties;
import com.my.expense.service.SmsService;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import com.my.expense.enums.FinanceAction;

@Service
@AllArgsConstructor
public class SmsServiceImpl implements SmsService {

    private final TwilioConfigurationProperties twilioConfig;

    @PostConstruct
    public void setupTwilioClient() {
        Twilio.init(
                twilioConfig.getAccountSid(),
                twilioConfig.getAuthToken()
        );
    }

    @Override
    public void sendSms(FinanceAction action, String expenseType) {
        Message message = Message.creator(
                new com.twilio.type.PhoneNumber("+916364175505"),
                new com.twilio.type.PhoneNumber(twilioConfig.getPhoneNumber()),
                expenseType + " "+ action.getValue().toLowerCase()
        ).create();
        System.out.println("Sending SMS for action: " + action);
    }
}
