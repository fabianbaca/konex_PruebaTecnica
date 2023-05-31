package com.fabian.pruebatecnica.services;

import com.fabian.pruebatecnica.models.Sale;
import com.fabian.pruebatecnica.repositories.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class SaleServiceImpl implements SaleService {
    @Autowired
    private SaleRepository repository;

    @Override
    public String createSale(Sale sale) throws IOException {
        repository.save(sale);
        return "creacion completada";
    }

    @Override
    public List<Sale> getSale() {
        return repository.findAll();
    }
}
