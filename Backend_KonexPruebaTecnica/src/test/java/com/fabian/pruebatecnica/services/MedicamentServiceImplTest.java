package com.fabian.pruebatecnica.services;

import com.fabian.pruebatecnica.dto.MessageRest;
import com.fabian.pruebatecnica.models.Medicament;
import com.fabian.pruebatecnica.repositories.MedicamentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class MedicamentServiceImplTest {

    @Mock
    private MedicamentRepository repository ;

    @InjectMocks
    private  MedicamentServiceImpl medicamentServiceImpl;

    private  Medicament medicament;

    private MessageRest messageRest;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        medicament = new Medicament(1L, "Paracetamol", "Laboratorio A",
                LocalDate.of(2023, 5, 1), LocalDate.of(2025, 4, 30), 100, 2.5);
    }

    @Test
    void createMedicament() throws IOException {
        when(repository.save(any(Medicament.class))).thenReturn(medicament);
        assertEquals( medicament, medicamentServiceImpl.createMedicament(medicament));
    }

    @Test
    void getMedicamentById() throws IOException {
        when(repository.findById(1L)).thenReturn(Optional.of(medicament));
        assertEquals( Optional.of(medicament), medicamentServiceImpl.getMedicamentById(1l));
    }

    @Test
    void getMedicamentByName() throws IOException {
        when(repository.findByName("Paracetamol")).thenReturn(Optional.of(medicament));
        assertEquals( medicament, medicamentServiceImpl.getMedicamentByName(medicament.getName()));
    }

    @Test
    void getMedicamentoByNameNotFount() throws IOException {
        when(repository.findByName("Paracetamol")).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, ()->medicamentServiceImpl.getMedicamentByName(medicament.getName()));
    }

    @Test
    void getMedicaments() {
        when(repository.findAll()).thenReturn(Arrays.asList(medicament));
        assertNotNull(medicamentServiceImpl.getMedicaments());
    }

    @Test
    void deleteMedicament() throws IOException {
        repository.deleteById(medicament.getId());
        Mockito.verify(repository).deleteById(medicament.getId());
        messageRest=new MessageRest("200", "successfully remove");
        assertEquals(messageRest , medicamentServiceImpl.deleteMedicament(medicament.getId()));
    }

    @Test
    void updateMedicament() throws IOException {
        when(repository.findById(medicament.getId())).thenReturn(Optional.of(medicament));
        when(repository.save(medicament)).thenReturn((medicament));
        assertEquals(medicament , medicamentServiceImpl.updateMedicament(medicament,medicament.getId()));
    }

    @Test
    void getMedicamentUpdateNotFount() throws IOException {
        MedicamentService medicamentServiceMock = mock(MedicamentService.class);

        when(repository.findById(medicament.getId())).thenReturn(Optional.empty());
        // Configura el mock para lanzar una RuntimeException al llamar al método updateMedicament
        doThrow(RuntimeException.class).when(medicamentServiceMock).updateMedicament(any(), eq(medicament.getId()));

        // Verifica que se lance la RuntimeException al llamar a updateMedicament
        assertThrows(RuntimeException.class, () -> medicamentServiceImpl.updateMedicament(medicament, medicament.getId()));

    }


}