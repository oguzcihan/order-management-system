package org.cihan.ordermanagementsystem.customer.service;

import org.cihan.ordermanagementsystem.customer.domain.Customer;
import org.cihan.ordermanagementsystem.customer.domain.CustomerRequest;
import org.cihan.ordermanagementsystem.customer.domain.CustomerResponse;
import org.cihan.ordermanagementsystem.customer.mapper.CustomerMapper;
import org.cihan.ordermanagementsystem.customer.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class CustomerServiceTest {

    @InjectMocks
    private CustomerService customerService;
    @Mock
    private CustomerRepository customerRepository;
    @Mock
    private CustomerMapper customerMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void should_successfully_create_customer() {
        //Given
        CustomerRequest customerRequest = new CustomerRequest(
                "John",
                "Doe",
                "john.doe@example.com",
                "123456789"
        );

        Customer customer = new Customer(
                null,
                "John",
                "Doe",
                "john.doe@example.com",
                "123456789"
        );

        Customer savedCustomer = new Customer(
                null,
                "John",
                "Doe",
                "john.doe@example.com",
                "123456789"
        );
        savedCustomer.setId(UUID.randomUUID());

        //Mock the calls
        Mockito.when(customerMapper.toCustomer(customerRequest)).thenReturn(customer);
        Mockito.when(customerRepository.save(customer)).thenReturn(savedCustomer);
        Mockito.when(customerMapper.toCustomerResponse(savedCustomer))
                .thenReturn(new CustomerResponse(null, "John", "Doe", "john.doe@example.com", "123456789"));

        //When
        CustomerResponse customerResponse = customerService.createCustomer(customerRequest);


        //Then
        assertEquals(customerRequest.firstName(), customerResponse.firstName());
        assertEquals(customerRequest.lastName(), customerResponse.lastName());
        assertEquals(customerRequest.phone(), customerResponse.phone());
        assertEquals(customerRequest.email(), customerResponse.email());
    }
}