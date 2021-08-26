package com.ciliaassessmentproject.commerce.controller;

import com.ciliaassessmentproject.commerce.model.Sale;
import com.ciliaassessmentproject.commerce.repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/sale")
public class SaleController {

    @Autowired
    private SaleRepository saleRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> saveOrUpdate(@Valid @RequestBody Sale sale) {

        if (sale.getProducts().isEmpty() || sale.getProducts().size() == 0) {
            return ResponseEntity.badRequest().body(sale);
        }

        return ResponseEntity.ok(saleRepository.save(sale));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> find(@PathVariable Long id) {

        Optional<Sale> sale = saleRepository.findById(id);

        if (!sale.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(sale.get());
    }

    @GetMapping
    public List<Sale> listAll() {
        return saleRepository.findAllByOrderByIdAsc();
    }

    @DeleteMapping("/{id}")
    public HttpStatus delete(@PathVariable Long id) {

        if (!saleRepository.findById(id).isPresent()) {
            return HttpStatus.NOT_FOUND;
        }

        saleRepository.deleteById(id);
        return HttpStatus.OK;
    }
}
