package com.ciliaassessmentproject.commerce.repository;

import com.ciliaassessmentproject.commerce.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Long> {

    public List<Client> findAllByOrderByIdAsc();
}
