package org.cihan.ordermanagementsystem.customer.mapper;

import org.cihan.ordermanagementsystem.customer.domain.Customer;
import org.cihan.ordermanagementsystem.customer.domain.CustomerRequest;
import org.cihan.ordermanagementsystem.customer.domain.CustomerResponse;
import org.springframework.stereotype.Service;

@Service
public class CustomerMapper {

    /**
     * CustomerRequest den Customer objesine dönüştürür.
     * @param customerRequest CustomerRequest
     * @return Customer
     */
    public Customer toCustomer(CustomerRequest customerRequest) {
        if (customerRequest == null) {
            return null;
        }
        return Customer.builder()
                .firstName(customerRequest.firstName())
                .lastName(customerRequest.lastName())
                .phone(customerRequest.phone())
                .email(customerRequest.email())
                .build();
    }

    /**
     * Customer objesini CustomerResponse objesine dönüştürür.
     * @param customer Customer
     * @return CustomerResponse
     */
    public CustomerResponse toCustomerResponse(Customer customer) {
        return new CustomerResponse(
                customer.getId(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getEmail(),
                customer.getPhone()
        );
    }
}
