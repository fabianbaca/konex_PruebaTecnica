package com.fabian.pruebatecnica.repositories;

import com.fabian.pruebatecnica.models.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long>{ }
