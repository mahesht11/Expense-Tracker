package com.expence.tracker.service;

import com.expence.tracker.entity.Expense;
import com.expence.tracker.repos.ExpenseRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepo expenseRepo;


    public List<Expense> getAllExpenses() {
        log.info("Expense Service : ");
        return expenseRepo.findAll();
    }
}
