package com.example.grupparbetespringmedrest.Controllers;

import com.example.grupparbetespringmedrest.Repository.DiscRepository;
import com.example.grupparbetespringmedrest.Repository.PurchasedDiscRepository;
import com.example.grupparbetespringmedrest.Service.CustomerService;
import com.example.grupparbetespringmedrest.Service.DiscService;
import com.example.grupparbetespringmedrest.domain.Customer;
import com.example.grupparbetespringmedrest.domain.Disc;
import com.example.grupparbetespringmedrest.domain.PurchasedDisc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;

@Controller
public class AdminController {

    private CustomerService customerService;
    private PurchasedDiscRepository purchasedDiscRepository;
    private DiscService discService;

    @Autowired
    public AdminController(CustomerService customerService, PurchasedDiscRepository purchasedDiscRepository, DiscService discService) {
        this.customerService = customerService;
        this.purchasedDiscRepository = purchasedDiscRepository;
        this.discService = discService;
    }

    public List<Customer> getAllCustomersWithoutAdmin() {
        return customerService.getAllCustomersWithoutAdmin();
    }

    public List<PurchasedDisc> getAllPurchasedListByCustomerId(long id) {
        return purchasedDiscRepository.getAllPurchasedListByCustomerId(id);
    }

    @GetMapping("/admin")
    public String adminPage(Model model) {
        List<Customer> listOfCustomers = getAllCustomersWithoutAdmin();
        model.addAttribute("listOfCustomer", listOfCustomers);
        return "admin";
    }


    @GetMapping("/checkuserhistory/{id}")
    public String checkUserHistory(@PathVariable long id, Model model) {
        List<PurchasedDisc> listOfPurchaseHistory = getAllPurchasedListByCustomerId(id);
        if (listOfPurchaseHistory != null) {
            model.addAttribute("listOfPurchaseHistory", listOfPurchaseHistory);
            //model.addAttribute("listOfDisc",listOfPurchaseHistory.stream().collect(Collectors.groupingBy()))
            return "checkuserhistory";
        }
        return "error";
    }

    @GetMapping("/disclist/{id}")
    public String disclist(@PathVariable long id, Model model) {
        List<Disc> listOfDisc = discService.getDiscFromPurchasedId(id);

        if (listOfDisc != null) {
            model.addAttribute("listOfDisc", listOfDisc);
            return "disclist";
        }
        return "error";
    }
}