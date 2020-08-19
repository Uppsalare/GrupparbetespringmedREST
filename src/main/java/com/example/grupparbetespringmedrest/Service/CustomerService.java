package com.example.grupparbetespringmedrest.Service;

import com.example.grupparbetespringmedrest.domain.Customer;
import com.example.grupparbetespringmedrest.Repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    private CustomerRepository customerRepository;
    private Customer currentCustomer;

    public Customer getCurrentCustomer() {
        return currentCustomer;
    }

    public void setCurrentCustomer(Customer currentCustomer) {
        this.currentCustomer = currentCustomer;
    }

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer getCustomerById(long id) {
        return customerRepository.findById(id);
    }

    public Customer getCustomerByEmail(String email){
        return customerRepository.findByEmail(email);
    }

    public List<Customer> getAllCustomersWithoutAdmin(){
        //return customerRepository.findAll().stream().filter(Customer::isIsAdmin).collect(Collectors.toList());
        return customerRepository.findAll().stream().filter(customer -> !customer.isIsAdmin()).collect(Collectors.toList());
    }

    @Transactional
    public void save(Customer customer) {
        customerRepository.save(customer);
    }
}
