package com.ciliaassessmentproject.commerce.repository;

import com.ciliaassessmentproject.commerce.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    public List<Product> findAllByOrderByIdAsc();

}
