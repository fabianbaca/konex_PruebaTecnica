package com.fabian.pruebatecnica.controller;

import com.fabian.pruebatecnica.models.Sale;
import com.fabian.pruebatecnica.services.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
@RequestMapping("/api/Sale")
@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
public class SaleController {

    @Autowired
    private SaleService saleService;


    @GetMapping("/")
    @ResponseBody
    public ResponseEntity<List<Sale>> getSale() {
        return ResponseEntity.status(HttpStatus.OK).body( saleService.getSale());
    }

    @PostMapping("/")
    @ResponseBody
    public ResponseEntity<Sale> createSale(@RequestBody Sale sale) throws IOException {
        return ResponseEntity.status(HttpStatus.CREATED).body(saleService.createSale(sale));
    }

    @GetMapping("/betweenDate")
    public ResponseEntity<List<Sale>> betweenDate(@RequestParam("start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate, @RequestParam("end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate  ) throws IOException {
        return ResponseEntity.status(HttpStatus.OK).body(saleService.findAllByDateSaleBetween(startDate, endDate));
    }

}
