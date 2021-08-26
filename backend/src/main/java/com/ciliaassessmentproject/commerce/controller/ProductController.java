package com.ciliaassessmentproject.commerce.controller;

import com.ciliaassessmentproject.commerce.model.Product;
import com.ciliaassessmentproject.commerce.repository.ProductRepository;
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
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private SaleRepository saleRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public Product saveOrUpdate(@Valid @RequestBody Product product) {
        return productRepository.save(product);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> find(@PathVariable Long id) {

        Optional<Product> product = productRepository.findById(id);

        if (!product.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(product.get());
    }

    @GetMapping
    public List<Product> listAll() {
        return productRepository.findAllByOrderByIdAsc();
    }

    @DeleteMapping("/{id}")
    public HttpStatus delete(@PathVariable Long id) {

        if (!productRepository.findById(id).isPresent()) {
            return HttpStatus.NOT_FOUND;
        }

        saleRepository.deleteAllByIdProduct(id);

        productRepository.deleteById(id);
        return HttpStatus.OK;
    }
}
