package org.cihan.ordermanagementsystem.customer.service;

import lombok.RequiredArgsConstructor;
import org.cihan.ordermanagementsystem.common.exception.NotFoundException;
import org.cihan.ordermanagementsystem.common.exception.ValidationException;
import org.cihan.ordermanagementsystem.common.utils.Constants;
import org.cihan.ordermanagementsystem.customer.domain.Customer;
import org.cihan.ordermanagementsystem.customer.domain.CustomerRequest;
import org.cihan.ordermanagementsystem.customer.domain.CustomerResponse;
import org.cihan.ordermanagementsystem.customer.mapper.CustomerMapper;
import org.cihan.ordermanagementsystem.customer.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final Logger log = LoggerFactory.getLogger(CustomerService.class);

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerResponse createCustomer(CustomerRequest customerRequest) {
        log.info("createCustomer called");
        if(customerRepository.existsByEmail(customerRequest.email())) {
            throw new ValidationException(String.format(Constants.CUSTOMER_EMAIL_ALREADY_EXISTS, customerRequest.email()));
        }
        Customer customer = customerRepository.save(customerMapper.toCustomer(customerRequest));
        return customerMapper.toCustomerResponse(customer);
    }

    public Page<CustomerResponse> getAllCustomers(Pageable pageable) {
        log.info("getAllCustomers called");
        Page<Customer> customerPage = customerRepository.findAll(pageable);
        return customerPage.map(customerMapper::toCustomerResponse);
    }

    public Optional<CustomerResponse> getCustomerById(UUID id) {
        log.info("getCustomerById called");
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format(Constants.CUSTOMER_NOT_FOUND, id)));
        return Optional.ofNullable(customerMapper.toCustomerResponse(customer));
    }

    public CustomerResponse updateCustomer(UUID id, CustomerRequest customerRequest) {
        log.info("updateCustomer called");

        Customer existingCustomer = customerRepository.findById(id).
                orElseThrow(() -> new NotFoundException(String.format(Constants.CUSTOMER_NOT_FOUND, id)));

        existingCustomer.setFirstName(customerRequest.firstName());
        existingCustomer.setLastName(customerRequest.lastName());
        existingCustomer.setPhone(customerRequest.phone());
        existingCustomer.setEmail(customerRequest.email());

        Customer customer = customerRepository.save(existingCustomer);
        return customerMapper.toCustomerResponse(customer);
    }

    public void deleteCustomer(UUID id) {
        if (!customerRepository.existsById(id)) {
            throw new NotFoundException(String.format(Constants.CUSTOMER_NOT_FOUND, id));
        }
        customerRepository.deleteById(id);
    }

    public boolean isAnyCustomerExists() {
        return customerRepository.count() > 0;
    }
}