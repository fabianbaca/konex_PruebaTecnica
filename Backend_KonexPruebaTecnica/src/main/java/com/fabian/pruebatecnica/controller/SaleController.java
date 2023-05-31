package com.fabian.pruebatecnica.controller;

import com.fabian.pruebatecnica.models.Sale;
import com.fabian.pruebatecnica.services.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
@RequestMapping("/api")
@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
public class SaleController {

    @Autowired
    private SaleService saleService;


    @GetMapping("/venta")
    @ResponseBody
    public ResponseEntity<List<Sale>> getSale() {
        return ResponseEntity.status(HttpStatus.OK).body( saleService.getSale());
    }

    @PostMapping("/venta")
    @ResponseBody
    public ResponseEntity<String> createSale(@RequestBody Sale sale) throws IOException {
        return ResponseEntity.status(HttpStatus.CREATED).body(saleService.createSale(sale));
    }
}
