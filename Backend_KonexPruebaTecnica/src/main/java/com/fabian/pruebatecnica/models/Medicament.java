package com.fabian.pruebatecnica.models;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "medicament",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"name"})
        })
public class Medicament {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank(message = "name cannot be null")
    @Column(name ="name")
    private String name;

    @NotBlank(message = "laboratorie cannot be null")
    @Column(name ="laboratorie")
    private String laboratorie;

    @NotBlank(message = "date Manufacture cannot be null")
    @Column(name = "date_manufacture")
    @JsonFormat(pattern ="yyyy-MM-dd")
    private LocalDate  dateManufacture;

    @NotBlank(message = "date Expiration cannot be null")
    @Column(name = "date_expiration")
    @JsonFormat(pattern ="yyyy-MM-dd")
    private LocalDate dateExpiration;

    @NotBlank(message = "quantity Stock cannot be null")
    @Column(name = "quantity_stock")
    private Integer quantityStock;

    @NotBlank(message = "unit Value cannot be null")
    @Column(name = "unit_value",columnDefinition = "NUMBER")
    private Double unitValue;

    public Medicament() {}

    public Medicament(Long id, String name, String laboratorie, LocalDate dateManufacture, LocalDate dateExpiration, Integer quantityStock, Double unitValue) {
        this.id = id;
        this.name = name;
        this.laboratorie = laboratorie;
        this.dateManufacture = dateManufacture;
        this.dateExpiration = dateExpiration;
        this.quantityStock = quantityStock;
        this.unitValue = unitValue;
    }
}
