package org.cihan.ordermanagementsystem.customer.mapper;

import org.cihan.ordermanagementsystem.customer.domain.Customer;
import org.cihan.ordermanagementsystem.customer.domain.CustomerRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CustomerMapperTest {

    private CustomerMapper customerMapper;

    @BeforeEach
    void setUp() {
        customerMapper = new CustomerMapper();
    }

    @Test
    public void shouldMapCustomerRequestToCustomer() {
        CustomerRequest customerRequest = new CustomerRequest(
                "John",
                "Doe",
                "john.doe@example.com",
                "123456789"
        );

        Customer customer = customerMapper.toCustomer(customerRequest);

        assertEquals(customerRequest.firstName(), customer.getFirstName());
        assertEquals(customerRequest.lastName(), customer.getLastName());
        assertEquals(customerRequest.email(), customer.getEmail());
        assertEquals(customerRequest.phone(), customer.getPhone());

    }

}