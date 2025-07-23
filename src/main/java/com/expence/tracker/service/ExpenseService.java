package com.expence.tracker.service;

import com.expence.tracker.dto.ExpenseRecord;
import com.expence.tracker.entity.Expense;
import com.expence.tracker.exception.ExpenseException;
import com.expence.tracker.repos.ExpenseRepo;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Builder(toBuilder = true)
public class ExpenseService {

    @Autowired
    private ExpenseRepo expenseRepo;


    public List<Expense> getAllExpenses(Pageable pageable) {
        log.info("Expense Service : ");
        return expenseRepo.findAll(pageable).toList();
    }

    public ExpenseRecord getExpenseById(Long id) {
        log.info("Expense Service : getExpenseById() :"+id);
       Expense expense = expenseRepo.findById(id).stream().findFirst().orElseThrow(() -> new ExpenseException("there is no object available with this id : "+id));
        if(expense.getId().equals(id)){
            ExpenseRecord record = new ExpenseRecord(expense.getId(), expense.getName(), expense.getDescription(), expense.getAmount(), expense.getCategory(),expense.getDate());
            return record;
        }
        return null;
    }

    public String deleteExpenseById(Long id) {
        log.info("Expense Service : deleteExpenseById():"+id);
        Optional<Expense> expense = expenseRepo.findById(id);
        if(expense.isPresent()){
            expenseRepo.deleteById(id);
            return "deleted successfully by id : "+id;
        }else{
            throw new ExpenseException("there is no object available with this id : "+id);
        }

    }

    public ExpenseRecord saveExpense(ExpenseRecord record) {
        Expense expense = new Expense();
        expense.setName(record.name());
        expense.setCategory(record.category());
        expense.setAmount(record.amount());
        expense.setDescription(record.description());
        expense.setDate(new Date());
       Expense expense1 =  expenseRepo.saveAndFlush(expense);
       return new ExpenseRecord(expense1.getId(),expense1.getName(),expense1.getDescription(), expense1.getAmount(),expense1.getCategory(), expense1.getDate());
    }

    public ExpenseRecord updateExpenseById(ExpenseRecord record, Long id) {
        log.info("Expense Service : updateExpenseById():"+id);
        Optional<Expense> expense = expenseRepo.findById(id);
        if(expense.isPresent()){
            expense.get().setName((record.name()==null||record.name().isBlank())?expense.get().getName():record.name());
            expense.get().setCategory((record.category()==null||record.category().isBlank())?expense.get().getCategory():record.category());
            expense.get().setAmount(record.amount()==null?expense.get().getAmount():record.amount());
            expense.get().setDescription((record.description()==null||record.description().isBlank())?expense.get().getDescription():record.description());
            expense.get().setDate((record.date()==null)?expense.get().getDate():record.date());
            Expense expense1 =  expenseRepo.saveAndFlush(expense.get());
            return new ExpenseRecord(expense1.getId(),expense1.getName(),expense1.getDescription(), expense1.getAmount(),expense1.getCategory(), expense1.getDate());
        }else{
            throw new ExpenseException("There is no object available with this id : "+id);
        }
    }
}
