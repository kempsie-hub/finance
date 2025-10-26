package com.my.expense.service;

import com.my.expense.enums.FinanceAction;

public interface SmsService {

    void sendSms(FinanceAction action, String expenseType);
}
