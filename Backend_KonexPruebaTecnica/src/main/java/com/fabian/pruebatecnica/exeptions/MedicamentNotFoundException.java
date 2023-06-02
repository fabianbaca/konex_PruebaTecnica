package com.fabian.pruebatecnica.exeptions;

import org.springframework.web.bind.annotation.RestControllerAdvice;


public class MedicamentNotFoundException extends RuntimeException{

    public MedicamentNotFoundException() {
       super("Medicamento no encontrado");
    }
}
