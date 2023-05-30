package com.fabian.konex_PruebaTecnica.models;


import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "medicamento",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"nombre", "laboratorio"})
        })
public class Medicamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String laboratorio;

    @Column(name = "fecha_fabricacion")
    @DateTimeFormat(pattern ="yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    private LocalDateTime fechaFabricacion;

    @Column(name = "fecha_vencimiento")
    @DateTimeFormat(pattern ="yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    private LocalDateTime fechaVencimiento;

    @Column(name = "cantidad_stock")
    private Integer cantidadStock;

    @Column(name = "valor_unitario",columnDefinition = "NUMBER")
    private Double valorUnitario;

    private Integer estado;

    public Medicamento() {}

    public Medicamento(Long id, String nombre, String laboratorio, LocalDateTime fechaFabricacion, LocalDateTime fechaVencimiento, Integer cantidadStock, Double valorUnitario, Integer estado) {
        this.id = id;
        this.nombre = nombre;
        this.laboratorio = laboratorio;
        this.fechaFabricacion = fechaFabricacion;
        this.fechaVencimiento = fechaVencimiento;
        this.cantidadStock = cantidadStock;
        this.valorUnitario = valorUnitario;
        this.estado = estado;
    }

}
