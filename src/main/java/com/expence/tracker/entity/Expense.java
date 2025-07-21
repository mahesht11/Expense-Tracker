package com.expence.tracker.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Table(name="expense")
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="expense_name")
    private String name;

    private String description;

    @Column(name = "expense_amount")
    private BigDecimal amount;

    private String category;

    private Date date;


}
