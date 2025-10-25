package com.my.expense.controller;

import com.my.expense.dto.ExpenseTypeDTO;
import com.my.expense.entity.ExpenseType;
import com.my.expense.service.ExpenseTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/expense-types")
public class ExpenseTypeController {

    private final ExpenseTypeService expenseTypeService;

    @Autowired
    public ExpenseTypeController(ExpenseTypeService expenseTypeService) {
        this.expenseTypeService = expenseTypeService;
    }

    @GetMapping
    public List<ExpenseType> getAllExpenseTypes() {
        return expenseTypeService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExpenseType> getExpenseTypeById(@PathVariable Long id) {
        Optional<ExpenseType> expenseType = expenseTypeService.findById(id);
        return expenseType.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ExpenseTypeDTO> createExpenseType(@RequestBody ExpenseType expenseType, @RequestParam String application) {
        return new ResponseEntity<>(expenseTypeService.save(expenseType, application), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExpenseTypeDTO> updateExpenseType(@PathVariable Long id, @RequestBody ExpenseType expenseTypeDetails,
                                                            @RequestParam String application) {
        Optional<ExpenseType> expenseType = expenseTypeService.findById(id);
        if (expenseType.isPresent()) {
            ExpenseType updatedExpenseType = expenseType.get();
            updatedExpenseType.setName(expenseTypeDetails.getName());
            updatedExpenseType.setDescription(expenseTypeDetails.getDescription());
            updatedExpenseType.setStatus(expenseTypeDetails.getStatus());
            updatedExpenseType.setCreatedBy(expenseTypeDetails.getCreatedBy());
            updatedExpenseType.setUpdatedBy(expenseTypeDetails.getUpdatedBy());
            updatedExpenseType.setCreatedDate(expenseTypeDetails.getCreatedDate());
            updatedExpenseType.setUpdatedDate(expenseTypeDetails.getUpdatedDate());
            return ResponseEntity.ok(expenseTypeService.save(updatedExpenseType, application));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExpenseType(@PathVariable Long id) {
        if (expenseTypeService.findById(id).isPresent()) {
            expenseTypeService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}