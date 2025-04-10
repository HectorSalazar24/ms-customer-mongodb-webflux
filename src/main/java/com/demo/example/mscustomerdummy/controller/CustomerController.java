package com.demo.example.mscustomerdummy.controller;

import com.demo.example.mscustomerdummy.model.Customer;
import com.demo.example.mscustomerdummy.service.CustomerService;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/")
public class CustomerController {

  private final CustomerService customerService;

  @GetMapping("/customers")
  public Flux<Customer> viewCustomers(@RequestHeader Map<String, String> headers) {
    return customerService.listCustomers();
  }

  @GetMapping("/customers/{id}")
  public Mono<Customer> viewCustomer(@RequestHeader Map<String, String> headers, @PathVariable String id) {
    return customerService.getCustomer(id)
        .doOnError(throwable -> System.out.println(throwable));
  }

  @DeleteMapping("/customers/{id}")
  public Mono<Void> deleteCustomer(@RequestHeader Map<String, String> headers, @PathVariable String id) {
    return customerService.deleteCustomer(id);
  }

}
