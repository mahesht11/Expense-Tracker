package com.expence.tracker.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.sql.Timestamp;
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
    @NotNull(message = "Expense name is not be null")
    @Size(min=3, message = "name must be min 3 characters")
    private String name;

    private String description;

    @Column(name = "expense_amount")
    @NotNull(message = "amount is not be null")
    private BigDecimal amount;

    @NotNull(message = "category is not be null")
    private String category;

    @NotNull(message = "Date is not be null")
    private Date date;

    @Column(name="created_at", nullable = false, updatable = false)
    @CreationTimestamp
    private Timestamp createdAt;

    @Column(name="updated_at")
    @UpdateTimestamp
    private Timestamp updatedAt;
}
