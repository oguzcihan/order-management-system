package org.cihan.ordermanagementsystem.customer.rest;

import jakarta.validation.Valid;
import org.cihan.ordermanagementsystem.common.utils.PaginationUtil;
import org.cihan.ordermanagementsystem.customer.domain.CustomerRequest;
import org.cihan.ordermanagementsystem.customer.domain.CustomerResponse;
import org.cihan.ordermanagementsystem.customer.service.CustomerService;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerControllerImpl implements CustomerController {

    private final CustomerService customerService;

    public CustomerControllerImpl(CustomerService customerService) {
        this.customerService = customerService;
    }


    @Override
    public ResponseEntity<CustomerResponse> createCustomer(@Valid @RequestBody CustomerRequest customerRequest) throws URISyntaxException {
        CustomerResponse result = customerService.createCustomer(customerRequest);
        return ResponseEntity.created(new URI("/api/v1/customers/" + result.id())).body(result);
    }

    @Override
    public ResponseEntity<List<CustomerResponse>> getAllCustomers(@ParameterObject Pageable pageable) {
        Page<CustomerResponse> customerPage = customerService.getAllCustomers(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), customerPage);

        return ResponseEntity.ok()
                .headers(headers).body(customerPage.getContent());
    }

    @Override
    public ResponseEntity<Optional<CustomerResponse>> getCustomerById(@PathVariable UUID id) {
        Optional<CustomerResponse> result = customerService.getCustomerById(id);
        return ResponseEntity.ok().body(result);
    }

    @Override
    public ResponseEntity<CustomerResponse> updateCustomer(@PathVariable UUID id, @RequestBody @Valid CustomerRequest customerRequest) {
        CustomerResponse result = customerService.updateCustomer(id, customerRequest);
        return ResponseEntity.ok().body(result);
    }

    @Override
    public ResponseEntity<Void> deleteCustomer(@PathVariable UUID id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.accepted().build();
    }
}