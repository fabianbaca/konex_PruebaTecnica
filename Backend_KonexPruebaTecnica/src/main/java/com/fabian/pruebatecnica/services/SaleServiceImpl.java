package com.fabian.pruebatecnica.services;

import com.fabian.pruebatecnica.models.Medicament;
import com.fabian.pruebatecnica.models.Sale;
import com.fabian.pruebatecnica.repositories.MedicamentRepository;
import com.fabian.pruebatecnica.repositories.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class SaleServiceImpl implements SaleService {
    @Autowired
    private SaleRepository repository;
    @Autowired
    private MedicamentService serviceMedicament  ;

    @Override
    public Sale createSale(Sale sale) throws IOException {
        Optional<Medicament> medicament = serviceMedicament.getMedicamentById(sale.getMedicament().getId());
        if(!medicament.isPresent())
            throw new RuntimeException("Not found medicamente");

        serviceMedicament.updateMedicament(sale.getMedicament(), sale.getMedicament().getId());
        return repository.save(sale);
    }

    @Override
    public List<Sale> getSale() {
        return repository.findAll();
    }

    @Override
    public List<Sale> findAllByDateSaleBetween(LocalDate startDate, LocalDate endDate) {



        return repository.findAllByDateSaleBetween(startDate, endDate);
    }
}
