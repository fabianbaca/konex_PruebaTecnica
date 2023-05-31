package com.fabian.pruebatecnica.models;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
@Entity
@Data
@Table(name = "sale")
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date_sale")
    @NotEmpty(message = "date Value cannot be null")
    @JsonFormat(pattern ="yyyy-MM-dd")
    private LocalDate fecha;

    @ManyToOne
    @NotEmpty(message = "unit Value cannot be null")
    @JoinColumn(name = "medicamentid")
    private Medicament medicament;

    @NotEmpty(message = "quantity Value cannot be null")
    @Column(name = "quantity")
    private Integer cantidad;

    @NotEmpty(message = "unit value Value cannot be null")
    @Column(name = "unit_value",columnDefinition = "NUMBER")
    private BigDecimal valorUnitario;


    @NotEmpty(message = "total value Value cannot be null")
    @Column(name = "total_value",columnDefinition = "NUMBER")
    private BigDecimal valorTotal;

    public Sale() {
    }

    public Sale(Long id, LocalDate fecha, Medicament medicament, Integer cantidad, BigDecimal valorUnitario, BigDecimal valorTotal) {
        this.id = id;
        this.fecha = fecha;
        this.medicament = medicament;
        this.cantidad = cantidad;
        this.valorUnitario = valorUnitario;
        this.valorTotal = valorTotal;
    }

}
