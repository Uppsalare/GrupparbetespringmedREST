package com.example.grupparbetespringmedrest;

import com.example.grupparbetespringmedrest.Repository.DiscRepository;
import com.example.grupparbetespringmedrest.Repository.PurchasedDiscRepository;
import com.example.grupparbetespringmedrest.domain.Customer;
import com.example.grupparbetespringmedrest.Repository.CustomerRepository;
import com.example.grupparbetespringmedrest.domain.Disc;
import com.example.grupparbetespringmedrest.domain.PurchasedDisc;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class GrupparbetespringmedrestApplication {

    public static void main(String[] args) {
        SpringApplication.run(GrupparbetespringmedrestApplication.class, args);
    }

    @Bean
    public CommandLineRunner setUp(CustomerRepository customerRepository, DiscRepository discRepository, PurchasedDiscRepository purchasedDiscRepository) {
        return (args -> {
            customerRepository.deleteAll();
            customerRepository.save(new Customer("Regular", "Regular", "regular@test.com", "test1", false,false));
            customerRepository.save(new Customer("Premium", "Premium", "premium@test.com", "test1", false,true));
            customerRepository.save(new Customer("Admin", "Admin", "admin@test.com", "test1", true,false));
            customerRepository.save(new Customer("Test", "Test", "test@google.com", "test1", false,false));
            customerRepository.save(new Customer("Test", "Test", "test@test.com", "test1", false,false));


            discRepository.deleteAll();
            discRepository.save(new Disc("Innova", "Teebird", 175, 249));
            discRepository.save(new Disc("Discmania", "Method", 171, 199));
            discRepository.save(new Disc("Dynamic Disc", "Truth", 172, 279));
            discRepository.save(new Disc("Discraft", "Buzzz", 174, 499999));
            discRepository.save(new Disc("Innova", "Aviar", 167, 169));
            discRepository.save(new Disc("Discraft", "Luna", 175, 199));
            discRepository.save(new Disc("Dynamic Disc", "Sheriff", 172, 229));
            discRepository.save(new Disc("Discmania", "Essence", 173, 189));
            discRepository.save(new Disc("Mvp", "Volt", 175, 229));
            discRepository.save(new Disc("Discmania", "DDX", 176, 179));
            discRepository.save(new Disc("Discraft", "Zeus", 177, 169));
            discRepository.save(new Disc("Mvp", "Axis", 174, 159));
            discRepository.save(new Disc("Innova", "Mako3", 174, 169));
            discRepository.save(new Disc("Dynamic Disc", "Escape", 178, 219));
            discRepository.save(new Disc("Mvp", "Wave", 170, 179));


/*
            PurchasedDisc regluarPurchased = new PurchasedDisc();
            regluarPurchased.setAmountDisc(2);
            regluarPurchased.setCustomer(customerRepository.findById(1));
            regluarPurchased.setTotalAmount(498);
            List<Disc> cartList = new ArrayList<>();
            cartList.add(discRepository.findDiscsById(1));
            cartList.add(discRepository.findDiscsById(1));

            for (Disc disc:cartList) {
                if (disc.getQuantity() !=1){
                    regluarPurchased.getDiscLongMap().put(disc,(long)disc.getQuantity());
                }
                else{
                    regluarPurchased.getDiscLongMap().put(disc,(long)1);
                }
            }

            purchasedDiscRepository.save(regluarPurchased);

 */

        });
    }
}