package com.example.grupparbetespringmedrest.Controllers;

import com.example.grupparbetespringmedrest.domain.Customer;
import com.example.grupparbetespringmedrest.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CustomerController {

    private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }


    @RequestMapping("/customers")
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @RequestMapping("/customer/{id}")
    public Customer getCustomerById(@PathVariable long id) {
        return customerService.getCustomerById(id);
    }

    @GetMapping("/registeraccount")
    public String addCustomer(Model model) {
        Customer customer = new Customer();
        model.addAttribute("newCustomer", customer);
        return "register_account";
    }

    @PostMapping("/save")
    public String savePerson(Customer customer, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "error";
        customerService.save(customer);
        return "redirect:/";
    }
}