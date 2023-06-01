package com.fabian.pruebatecnica.repositories;

import com.fabian.pruebatecnica.models.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long>{
    List<Sale> findAllByDateSaleBetween(LocalDate dateSaleStart, LocalDate dateSaleEnd);

}
