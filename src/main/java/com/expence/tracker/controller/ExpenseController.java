package com.expence.tracker.controller;


import com.expence.tracker.entity.Expense;
import com.expence.tracker.service.ExpenseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
