package com.fabian.pruebatecnica.controller;


import com.fabian.pruebatecnica.models.Medicament;
import com.fabian.pruebatecnica.services.MedicamentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
@RequestMapping("/api/Medicament")
public class MedicamentController {


    @Autowired
    private MedicamentService medicamentService;

    @GetMapping("/")
    @ResponseBody
    public ResponseEntity<List<Medicament>> getAllMedicament(){
        return ResponseEntity.status(HttpStatus.OK).body( medicamentService.getMedicaments());
    }


    @GetMapping("/{name}")
    @ResponseBody
    public ResponseEntity<Medicament> getMedicamentByName(@PathVariable("name") String name) throws IOException {
        return ResponseEntity.status(HttpStatus.OK).body(medicamentService.getMedicamentByName(name));
    }

    @PostMapping("/")
    @ResponseBody
    public ResponseEntity<String> createMedicament(@RequestBody @Valid Medicament medicament) throws IOException {
        return ResponseEntity.status(HttpStatus.CREATED).body(medicamentService.createMedicament(medicament));
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<String> updateMedicament(@PathVariable("id") Long id, @RequestBody @Validated Medicament medicament) throws IOException {
        return ResponseEntity.status(HttpStatus.OK).body(medicamentService.updateMedicament(medicament, id));
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<String> deleteMedicament(@PathVariable("id") Long id) throws IOException {
        return ResponseEntity.status(HttpStatus.OK).body(medicamentService.deleteMedicament(id));
    }


}
