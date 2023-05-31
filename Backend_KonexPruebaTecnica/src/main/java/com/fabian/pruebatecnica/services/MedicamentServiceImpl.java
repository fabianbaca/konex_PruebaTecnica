package com.fabian.pruebatecnica.services;

import com.fabian.pruebatecnica.models.Medicament;
import com.fabian.pruebatecnica.repositories.MedicamentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class MedicamentServiceImpl implements MedicamentService {

    @Autowired
    private MedicamentRepository repository ;


    @Override
    public String createMedicament(Medicament medicament) throws IOException {
        repository.save(medicament);
        return "se creo el medicamente";
    }

    @Override
    public Optional<Medicament> getMedicamentById(Long id) throws IOException {
        return repository.findById(id);
    }


    @Override
    public Medicament getMedicamentByName(String name) throws IOException {
        Optional<Medicament> medicament = repository.findByName(name);
        if (!medicament.isPresent()) {
            throw new RuntimeException("Not found medicamente with this name");
        }
        return medicament.get();
    }

    @Override
    public List<Medicament> getMedicaments() {
        return repository.findAll();
    }

    @Override
    public String deleteMedicament(Long id) throws IOException {
        repository.deleteById(id);
        return "Delete";
    }

    @Override
    public String updateMedicament(Medicament medicament, Long id) throws IOException {

         Optional<Medicament> oldMedicament = getMedicamentById(id);
         if (!oldMedicament.isPresent()) {
             throw new RuntimeException("Not found medicamente that modified");
         }

         oldMedicament.get().setName(medicament.getName());
         oldMedicament.get().setLaboratorie(medicament.getLaboratorie());
         oldMedicament.get().setDateManufacture(medicament.getDateManufacture());
         oldMedicament.get().setDateExpiration(medicament.getDateExpiration());
         oldMedicament.get().setQuantityStock(medicament.getQuantityStock());
         oldMedicament.get().setUnitValue(medicament.getUnitValue());

        repository.save(oldMedicament.get());
        return "Se actualiso correctamete" ;
    }
}
