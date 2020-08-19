package com.example.grupparbetespringmedrest.Controllers;

import com.example.grupparbetespringmedrest.Service.CartService;
import com.example.grupparbetespringmedrest.Service.CustomerService;
import com.example.grupparbetespringmedrest.Service.DiscService;
import com.example.grupparbetespringmedrest.domain.Disc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Controller
public class ShopController {

    private DiscService discService;
    private CustomerService customerService;
    private CartService cartService;


    @Autowired
    public ShopController(DiscService discService, CustomerService customerService, CartService cartService) {
        this.discService = discService;
        this.customerService = customerService;
        this.cartService = cartService;
    }

    @GetMapping("/shop")
    public String shopPage(Model model, String keyword) {
        List<Disc> listOfDiscs;
        model.addAttribute("currentCustomer", customerService.getCurrentCustomer()); //
        model.addAttribute("cartSize", cartService.getQuantityOfDisc()); //Quantity in cart

        if (keyword != null) {
            listOfDiscs = discService.findByKeyword(keyword);
        } else {
            listOfDiscs = discService.getAllDiscs();
        }
        model.addAttribute("listOfDiscs", listOfDiscs.stream().sorted(Comparator.comparing(Disc::getName)).collect(Collectors.toList())); // List of all Discs
        return "shop";
    }


    @GetMapping("/addToCart/{id}")
    public String addToCart(@PathVariable long id) {
        Optional<Disc> disc = discService.getDiscById(id);

        if (disc != null) {
            cartService.addToCart(disc.get());
        }
        return "redirect:/shop";
    }

    @GetMapping("/logout")
    public String logout() {
        cartService.clearCartList();
        customerService.setCurrentCustomer(null);
        return "redirect:/";
    }
}