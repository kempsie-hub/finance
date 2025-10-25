package com.my.expense.dto;

public record ExpenseTypeDTO(
    Long id,
    String name,
    String description,
    String status,
    String createdBy,
    String updatedBy,
    String createdDate,
    String updatedDate,
    boolean registrationRequired
) {}
