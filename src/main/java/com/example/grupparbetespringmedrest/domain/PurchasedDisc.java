package com.example.grupparbetespringmedrest.domain;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

@Entity
public class PurchasedDisc {

    @GeneratedValue
    @Id
    private Long id;
    private float totalAmount;
    @ManyToOne(fetch = FetchType.EAGER)
    private Customer customer;
    @ElementCollection
    private Map<Disc,Long> discLongMap = new TreeMap<>(Comparator.comparing(Disc::getId));

    private int amountDisc = 0;
    private LocalDate date = LocalDate.now();

    public PurchasedDisc() {
    }

    public int getAmountDisc() {
        return amountDisc;
    }

    public void setAmountDisc(int amountDisc) {
        this.amountDisc = amountDisc;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Map<Disc, Long> getDiscLongMap() {
        return discLongMap;
    }

    public void setDiscLongMap(Map<Disc, Long> discList) {
        this.discLongMap = discList;
    }

    public float getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(float totalAmount) {
        this.totalAmount = totalAmount;
    }

}
