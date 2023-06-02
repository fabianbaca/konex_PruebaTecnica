package com.fabian.pruebatecnica.services;

import com.fabian.pruebatecnica.models.Medicament;
import com.fabian.pruebatecnica.models.Sale;
import com.fabian.pruebatecnica.repositories.SaleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class SaleServiceImplTest {




    @Mock
    private MedicamentService medicamentService;

    @Mock
    private SaleRepository saleRepository;

    @InjectMocks
    private SaleServiceImpl saleService;

    private Medicament medicament;

    private Sale sale;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        medicament = new Medicament(1L, "Paracetamol", "Laboratorio A",
                LocalDate.of(2023, 5, 1), LocalDate.of(2025, 4, 30), 100, 2.5);
        sale = new Sale(1l, LocalDate.of(2023, 5, 1), medicament,  100, new BigDecimal(2.0),  new BigDecimal(100.0));
    }

    @Test
    void testCreateSale() throws IOException {
        MockitoAnnotations.initMocks(this);

        Sale sale = new Sale();
        Medicament medicament = new Medicament(1L, "Paracetamol", "Laboratorio A", null, null, 100, 2.5);
        sale.setMedicament(medicament);

        // Configurar el comportamiento del servicio de medicamento mock
        when(medicamentService.getMedicamentById(medicament.getId())).thenReturn(Optional.of(medicament));
        when(saleRepository.save(any(Sale.class))).thenReturn(sale);

        // Ejecutar el método a probar
        Sale result = saleService.createSale(sale);

        // Verificar el resultado
        assertNotNull(result);
        assertEquals(sale, result);

        // Verificar que los métodos del servicio de medicamento mock fueron llamados correctamente
        verify(medicamentService).getMedicamentById(medicament.getId());
        verify(medicamentService).updateMedicament(sale.getMedicament(), sale.getMedicament().getId());

        // Verificar que el método del repositorio de venta mock fue llamado correctamente
        verify(saleRepository).save(sale);
    }

    @Test
    void getSale() {
        when(saleRepository.findAll()).thenReturn(Arrays.asList(sale));
        assertNotNull(saleService.getSale());
    }

    @Test
    void findAllByDateSaleBetween() {
        LocalDate starDate = LocalDate.of(2023, 5, 1);
        LocalDate endDate = LocalDate.of(2023, 5, 1);

        when(saleRepository.findAllByDateSaleBetween(starDate,endDate )).thenReturn(Arrays.asList(sale));
        assertEquals(Arrays.asList(sale), saleService.findAllByDateSaleBetween(starDate,endDate));
    }
}