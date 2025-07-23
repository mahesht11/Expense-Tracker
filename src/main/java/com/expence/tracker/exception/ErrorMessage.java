package com.expence.tracker.exception;

import java.sql.Timestamp;

public record ErrorMessage(Integer statusCodeValue, String message, Timestamp timestamp) {
}
