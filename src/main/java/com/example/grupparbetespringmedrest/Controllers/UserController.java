package com.example.grupparbetespringmedrest.Controllers;

import com.example.grupparbetespringmedrest.Service.CustomerService;
import com.example.grupparbetespringmedrest.domain.Customer;
import com.example.grupparbetespringmedrest.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {

    private CustomerService customerService;

    @Autowired
    public UserController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("loginUser") User user) {
        Customer currentCustomer = customerService.getCustomerByEmail(user.getEmail());
        if (currentCustomer != null) {
            if (currentCustomer.getPassword().equals(user.getPassword())) {
                if (currentCustomer.isIsAdmin() == true) {
                    return "redirect:/admin";
                } else {
                    customerService.setCurrentCustomer(currentCustomer); // set a currentuser
                    return "redirect:/shop";
                }
            }
        }
        return "error";
    }
}