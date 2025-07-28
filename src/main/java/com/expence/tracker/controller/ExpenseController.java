package com.expence.tracker.controller;


import com.expence.tracker.dto.ExpenseRecord;
import com.expence.tracker.entity.Expense;
import com.expence.tracker.service.ExpenseService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Date;
import java.util.List;


@Slf4j
@RestController
@RequestMapping("/api/v1")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @GetMapping("/expenses")
    public ResponseEntity<List<Expense>> getAllExpenses(@RequestParam(required = false) Integer pageNo, @RequestParam(required = false) Integer pageSize, @RequestParam(required = false) String sortBy){
        log.info("Expense controller class : ");
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        return new ResponseEntity<>(expenseService.getAllExpenses(paging), HttpStatus.FOUND);
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
    public ResponseEntity<ExpenseRecord> saveExpense(@Valid @RequestBody ExpenseRecord record){
        log.info("Expense Controller class : saveExpense() : ");
        return new ResponseEntity<>(expenseService.saveExpense(record), HttpStatus.CREATED);
    }

    @PutMapping("/expenses/{id}")
    public ResponseEntity<ExpenseRecord> updateExpenseById(@RequestBody ExpenseRecord record, @PathVariable Long id){
        log.info("Expense Controller class : updateExpenseById() with the id : "+id);
        return new ResponseEntity<>(expenseService.updateExpenseById(record, id), HttpStatus.ACCEPTED);
    }

    @GetMapping("expenses/category")
    public ResponseEntity<List<Expense>> getAllExpencesByCategory(@RequestParam String category, Pageable page){
        return new ResponseEntity<>(expenseService.readByCategory(category, page), HttpStatus.FOUND);
    }

    @GetMapping("expenses/name")
    public ResponseEntity<List<Expense>> getAllExpencesByName(@RequestParam String name, Pageable page){
        return new ResponseEntity<>(expenseService.readByName(name, page), HttpStatus.FOUND);
    }
    @GetMapping("expenses/date")
    public ResponseEntity<List<Expense>> getAllExpenseByDates(@RequestParam(required = false) String startDate, @RequestParam(required = false) String endDate, Pageable page) throws ParseException {
        return new ResponseEntity<>(expenseService.readByDates(startDate, endDate, page), HttpStatus.ACCEPTED);
    }
}

