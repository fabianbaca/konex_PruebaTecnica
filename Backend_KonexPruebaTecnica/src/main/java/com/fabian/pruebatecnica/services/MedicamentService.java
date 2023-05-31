package com.fabian.pruebatecnica.services;

import com.fabian.pruebatecnica.models.Medicament;


import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface MedicamentService {
    String createMedicament(Medicament medicament)throws IOException;

    Optional<Medicament> getMedicamentById(Long id)throws IOException;

    Medicament getMedicamentByName(String name)throws IOException;

    List<Medicament> getMedicaments();

    String deleteMedicament(Long id)throws IOException;

    String updateMedicament(Medicament medicament, Long id) throws IOException;
}
