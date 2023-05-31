package com.fabian.pruebatecnica.exeptions;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MedicamentNotFoundException extends RuntimeException{
    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<String> MedicamentNotFoundException(RuntimeException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}
