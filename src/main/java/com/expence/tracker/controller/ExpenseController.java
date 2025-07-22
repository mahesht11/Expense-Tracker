package com.expence.tracker.controller;


import com.expence.tracker.dto.ExpenseRecord;
import com.expence.tracker.entity.Expense;
import com.expence.tracker.service.ExpenseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController
@RequestMapping("/api/v1")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @GetMapping("/expenses")
    public ResponseEntity<List<Expense>> getAllExpenses(){
        log.info("Expense controller class : ");
        return new ResponseEntity<>(expenseService.getAllExpenses(), HttpStatus.FOUND);
    }

    @GetMapping("/expenses/{id}")
    public ResponseEntity<ExpenseRecord> getExpenseById(@PathVariable Long id){
        log.info("Expense Controller class : getExpenseById() : "+id);
        return new ResponseEntity<>(expenseService.getExpenseById(id), HttpStatus.FOUND);
    }

    @DeleteMapping("/expenses")
    public ResponseEntity<String> deleteExpenseById(@RequestParam Long id){
        log.info("Expense Controller class : deleteExpenseById() : "+id);
        return new ResponseEntity<>(expenseService.deleteExpenseById(id), HttpStatus.FOUND);
    }

    @PostMapping("/expenses")
    public ResponseEntity<ExpenseRecord> saveExpense(@RequestBody ExpenseRecord record){
        log.info("Expense Controller class : saveExpense() : ");
        return new ResponseEntity<>(expenseService.saveExpense(record), HttpStatus.CREATED);
    }
}

