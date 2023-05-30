package com.fabian.konex_PruebaTecnica.models;


import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@Entity
@Data
@Table(name = "ventas")
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fecha")
    @DateTimeFormat(pattern ="yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    private LocalDateTime fecha;

    @ManyToOne
    @JoinColumn(name = "medicamento_id")
    private Medicamento medicamento;

    @Column(name = "cantidad")
    private Integer cantidad;

    @Column(name = "valor_unitario",columnDefinition = "NUMBER")
    private BigDecimal valorUnitario;


    @Column(name = "valor_total",columnDefinition = "NUMBER")
    private BigDecimal valorTotal;

    public Venta() {
    }

    public Venta(Long id, LocalDateTime fecha, Medicamento medicamento, Integer cantidad, BigDecimal valorUnitario, BigDecimal valorTotal) {
        this.id = id;
        this.fecha = fecha;
        this.medicamento = medicamento;
        this.cantidad = cantidad;
        this.valorUnitario = valorUnitario;
        this.valorTotal = valorTotal;
    }

}
