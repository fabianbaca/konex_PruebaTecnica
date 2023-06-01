package com.fabian.pruebatecnica.services;

import com.fabian.pruebatecnica.models.Sale;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface SaleService {

    Sale createSale(Sale sale) throws IOException;

    List<Sale> getSale();

    List<Sale> findAllByDateSaleBetween(LocalDate startDate, LocalDate endDate);

}
