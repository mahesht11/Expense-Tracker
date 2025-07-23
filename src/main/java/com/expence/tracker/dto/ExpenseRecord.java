package com.expence.tracker.dto;

import java.math.BigDecimal;
import java.util.Date;

public record ExpenseRecord(Long id, String name, String description, BigDecimal amount, String category, Date date) {
}
