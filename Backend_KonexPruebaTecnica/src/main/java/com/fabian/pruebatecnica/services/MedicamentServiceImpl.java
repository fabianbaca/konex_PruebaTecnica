package com.fabian.pruebatecnica.services;

import com.fabian.pruebatecnica.dto.MessageRest;
import com.fabian.pruebatecnica.exeptions.MedicamentNotFoundException;
import com.fabian.pruebatecnica.models.Medicament;
import com.fabian.pruebatecnica.repositories.MedicamentRepository;
import jakarta.transaction.Transactional;
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
    public Medicament createMedicament(Medicament medicament) throws IOException {
        return repository.save(medicament);
    }

    @Override
    public Optional<Medicament> getMedicamentById(Long id) throws IOException {
        return repository.findById(id);
    }


    @Override
    public Medicament getMedicamentByName(String name) throws IOException {
        Optional<Medicament> medicament = repository.findByName(name);
        if (!medicament.isPresent()) {
            throw new MedicamentNotFoundException();
        }
        return medicament.get();
    }

    @Override
    public List<Medicament> getMedicaments() {
        return repository.findAll();
    }

    @Override
    @Transactional
    public MessageRest deleteMedicament(Long id) throws IOException {
        repository.deleteById(id);
        return new MessageRest("200", "successfully remove");
    }

    @Override
    public Medicament updateMedicament(Medicament medicament, Long id) throws IOException {

         Optional<Medicament> oldMedicament = getMedicamentById(id);
         if (!oldMedicament.isPresent()) {
             throw new MedicamentNotFoundException();
         }

         oldMedicament.get().setName(medicament.getName());
         oldMedicament.get().setLaboratorie(medicament.getLaboratorie());
         oldMedicament.get().setDateManufacture(medicament.getDateManufacture());
         oldMedicament.get().setDateExpiration(medicament.getDateExpiration());
         oldMedicament.get().setQuantityStock(medicament.getQuantityStock());
         oldMedicament.get().setUnitValue(medicament.getUnitValue());


        return repository.save(oldMedicament.get());
    }
}
