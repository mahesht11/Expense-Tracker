package com.expence.tracker.repos;

import com.expence.tracker.entity.Expense;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ExpenseRepo extends JpaRepository<Expense, Long> {
   Page<Expense> findAllByName(String name, Pageable page);

    Page<Expense> findAllByCategory(String category, Pageable page);

    Page<Expense> findByDateBetween(Date startDate, Date endDate, Pageable page);
}
