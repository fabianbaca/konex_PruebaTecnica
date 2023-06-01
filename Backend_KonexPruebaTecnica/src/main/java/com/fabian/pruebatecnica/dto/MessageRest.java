package com.fabian.pruebatecnica.dto;


import lombok.Data;

@Data
public class MessageRest {
    public String code;
    public String mensaje;


    public MessageRest() {
    }

    public MessageRest(String code, String mensaje) {
        this.code = code;
        this.mensaje = mensaje;
    }
}
