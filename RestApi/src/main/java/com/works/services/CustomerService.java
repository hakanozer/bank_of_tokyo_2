package com.works.services;

import com.works.entities.Customer;
import com.works.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {

    final CustomerRepository customerRepository;
    final HttpServletRequest req;

    @Transactional
    public Customer login( Customer customer ) {
        System.out.println( customer );
        Optional<Customer> optionalCustomer = customerRepository.findByUsernameEqualsAndPasswordEquals(customer.getUsername(), customer.getPassword());
        if (optionalCustomer.isPresent() ) {
            req.getSession().setAttribute("customer", optionalCustomer.get());
            return optionalCustomer.get();
        }
        return null;
    }

}
