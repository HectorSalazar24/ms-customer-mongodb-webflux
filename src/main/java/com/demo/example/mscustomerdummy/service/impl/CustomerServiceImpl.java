package com.demo.example.mscustomerdummy.service.impl;

import com.demo.example.mscustomerdummy.model.Customer;
import com.demo.example.mscustomerdummy.repository.CustomerRepository;
import com.demo.example.mscustomerdummy.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class CustomerServiceImpl implements CustomerService {

  private final CustomerRepository customerRepository;

  @Override
  public Flux<Customer> listCustomers() {
    return customerRepository.findAll();
  }

  @Override
  public Mono<Customer> getCustomer(String id) {
    return customerRepository.findById(id)
        .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer no encontrado")));
  }

  @Override
  public Mono<Void> deleteCustomer(String id) {
    return customerRepository.deleteById(id);
  }

}
