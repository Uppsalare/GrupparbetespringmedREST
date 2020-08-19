package com.example.grupparbetespringmedrest.Controllers;

import com.example.grupparbetespringmedrest.Service.CartService;
import com.example.grupparbetespringmedrest.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CartController {

    private CustomerService customerService;
    private CartService cartService;

    @Autowired
    public CartController(CustomerService customerService, CartService cartService) {
        this.customerService = customerService;
        this.cartService = cartService;
    }

    @GetMapping("/cart")
    public String toCart(Model model) {
        model.addAttribute("listInCart", cartService.getCartList());
        model.addAttribute("currentCustomer", customerService.getCurrentCustomer());
        model.addAttribute("totalPrice", cartService.getTotalPriceOfAllItems());
        return "cart";
    }

    @GetMapping("/confirm")
    public String confirm(Model model) {
        if (cartService.getCartList().size() != 0) {
            cartService.confirmPurchase();
        }
        model.addAttribute("currentCustomer", customerService.getCurrentCustomer());
        return "cart";
    }

    @GetMapping("/reset")
    public String reset(Model model) {
        cartService.clearCartList();
        model.addAttribute("currentCustomer", customerService.getCurrentCustomer());
        return "cart";
    }
}