package com.fabian.pruebatecnica.services;

import com.fabian.pruebatecnica.models.Sale;

import java.io.IOException;
import java.util.List;

public interface SaleService {

    String createSale(Sale sale) throws IOException;

    List<Sale> getSale();
}
