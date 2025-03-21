package com.engagehub.api.resolver;

import com.engagehub.api.model.Customer;
import com.engagehub.api.service.CustomerService;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsMutation;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@DgsComponent
public class CustomerResolver {

    private final CustomerService customerService;

    @Autowired
    public CustomerResolver(CustomerService customerService) {
        this.customerService = customerService;
    }

    @DgsQuery
    public List<Customer> customers() {
        return customerService.findAllCustomers();
    }

    @DgsQuery
    public Optional<Customer> customer(@InputArgument Long id) {
        return customerService.findCustomerById(id);
    }
    

    @DgsMutation
    public Customer addCustomer(@InputArgument String name,
                                @InputArgument String email,
                                @InputArgument String phoneNumber) {
        Customer customer = new Customer(null, name, email, phoneNumber);
        return customerService.saveCustomer(customer);
    }

    @DgsMutation
    public Customer updateCustomer(@InputArgument Long id,
                                   @InputArgument String name,
                                   @InputArgument String email,
                                   @InputArgument String phoneNumber) {
        Customer customerDetails = new Customer(null, name, email, phoneNumber);
        return customerService.updateCustomer(id, customerDetails);
    }

    @DgsMutation
    public Boolean deleteCustomer(@InputArgument Long id) {
        customerService.deleteCustomer(id);
        return true;
    }

    @DgsQuery
    public List<Customer> searchCustomers(
            @InputArgument String name,
            @InputArgument String email,
            @InputArgument String phoneNumber) {
        return customerService.searchCustomers(name, email, phoneNumber);
    }
}
