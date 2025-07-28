package com.expence.tracker.exception;

import io.swagger.v3.oas.annotations.Hidden;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Hidden
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ExpenseNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleExpenseException(ExpenseNotFoundException ex, WebRequest request){
        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.NOT_FOUND.value(), ex.getMessage(), new Timestamp(System.currentTimeMillis()));
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleExpenseException(UserNotFoundException ex, WebRequest request){
        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.NOT_FOUND.value(), ex.getMessage(), new Timestamp(System.currentTimeMillis()));
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorMessage> handleBadRequestException(BadRequestException ex, WebRequest request){
        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.BAD_REQUEST.value(), ex.getMessage(), new Timestamp(System.currentTimeMillis()));
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> handleInternalException(Exception ex, WebRequest request){
        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage(), new Timestamp(System.currentTimeMillis()));
        return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", new Timestamp(System.currentTimeMillis()));
        body.put("statusCodeValue", HttpStatus.BAD_REQUEST.value());
        List<String> errors = ex.getBindingResult()
                .getFieldErrors().stream()
                .map(x -> x.getDefaultMessage())
                .collect(Collectors.toList());
        body.put("message", errors);
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

}
