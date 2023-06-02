package com.fabian.pruebatecnica.repositories;

import com.fabian.pruebatecnica.models.Medicament;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MedicamentRepository extends JpaRepository<Medicament, Long>{

    public Optional<Medicament> findByName(@Param("name") String name);

}
