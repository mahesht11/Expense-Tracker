package com.expence.tracker.dto;

import java.math.BigDecimal;

public record ExpenseRecord(Long id, String name, String description, BigDecimal amount, String category) {
}
