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
    private LocalDate dateSale;

    @ManyToOne
    @NotEmpty(message = "unit Value cannot be null")
    @JoinColumn(name = "medicamentid")
    private Medicament medicament;

    @NotEmpty(message = "quantity Value cannot be null")
    @Column(name = "quantity")
    private Integer quantity;

    @NotEmpty(message = "unit value Value cannot be null")
    @Column(name = "unit_value",columnDefinition = "NUMBER")
    private BigDecimal unitValue;


    @NotEmpty(message = "total value Value cannot be null")
    @Column(name = "total_value",columnDefinition = "NUMBER")
    private BigDecimal totalValue;

    public Sale() {
    }

    public Sale(Long id, LocalDate dateSale, Medicament medicament, Integer quantity, BigDecimal unitValue, BigDecimal totalValue) {
        this.id = id;
        this.dateSale = dateSale;
        this.medicament = medicament;
        this.quantity = quantity;
        this.unitValue = unitValue;
        this.totalValue = totalValue;
    }
}
