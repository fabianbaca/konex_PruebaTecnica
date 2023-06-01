package com.fabian.pruebatecnica.services;

import com.fabian.pruebatecnica.dto.MessageRest;
import com.fabian.pruebatecnica.models.Medicament;


import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface MedicamentService {
    Medicament createMedicament(Medicament medicament)throws IOException;

    Optional<Medicament> getMedicamentById(Long id)throws IOException;

    Medicament getMedicamentByName(String name)throws IOException;

    List<Medicament> getMedicaments();

    MessageRest deleteMedicament(Long id)throws IOException;

    Medicament updateMedicament(Medicament medicament, Long id) throws IOException;
}
