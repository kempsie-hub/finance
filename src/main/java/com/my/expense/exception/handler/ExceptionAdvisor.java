package com.my.expense.exception.handler;

import com.my.expense.exception.ExceptionDetails;
import com.my.expense.exception.ExpenseTypeException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class ExceptionAdvisor {

    @ExceptionHandler(ExpenseTypeException.class)
    public ResponseEntity<ExceptionDetails> handleExpenseTypeException(ExpenseTypeException ex, WebRequest webRequest) {
        ExceptionDetails exceptionDetails = new ExceptionDetails(LocalDateTime.now(),
                ex.getMessage(),
                webRequest.getDescription(false));
        return ResponseEntity.status(ex.getHttpStatus()).body(exceptionDetails);
    }

}
