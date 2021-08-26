package com.ciliaassessmentproject.commerce.controller;

import com.ciliaassessmentproject.commerce.model.Client;
import com.ciliaassessmentproject.commerce.repository.ClientRepository;
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
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private SaleRepository saleRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public Client saveOrUpdate(@Valid @RequestBody Client client) {
        return clientRepository.save(client);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> find(@PathVariable Long id) {

        Optional<Client> client = clientRepository.findById(id);

        if (!client.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(client.get());
    }

    @GetMapping
    public List<Client> listAll() {
        return clientRepository.findAllByOrderByIdAsc();
    }

    @DeleteMapping("/{id}")
    public HttpStatus delete(@PathVariable Long id) {

        if (!clientRepository.findById(id).isPresent()) {
            return HttpStatus.NOT_FOUND;
        }

        saleRepository.deleteAllByClient_Id(id);
        clientRepository.deleteById(id);

        return HttpStatus.OK;
    }
}
