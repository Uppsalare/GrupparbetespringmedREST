package com.example.grupparbetespringmedrest.Service;

import com.example.grupparbetespringmedrest.Repository.PurchasedDiscRepository;
import com.example.grupparbetespringmedrest.domain.Disc;
import com.example.grupparbetespringmedrest.domain.PurchasedDisc;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {

    private CustomerService customerService;
    private PurchasedDiscRepository purchasedDiscRepository;
    private List<Disc> cartList = new ArrayList<>();
    private int quantityOfDisc = 0;
    private float totalPriceOfAllItems = 0;
    private LocalDate date = LocalDate.now();


    public CartService(CustomerService customerService, PurchasedDiscRepository purchasedDiscRepository) {
        this.customerService = customerService;
        this.purchasedDiscRepository = purchasedDiscRepository;
    }

    public void confirmPurchase() {
        PurchasedDisc newpurchased = new PurchasedDisc();
        newpurchased.setCustomer(customerService.getCurrentCustomer());

        if (customerService.getCurrentCustomer().isPremium()) {
            newpurchased.setTotalAmount((float) (totalPriceOfAllItems * 0.85));
        } else {
            newpurchased.setTotalAmount(totalPriceOfAllItems);
        }

        for (Disc disc : cartList) {
            if (customerService.getCurrentCustomer().isPremium()) {
                disc.setPrice((int) (disc.getPrice() * 0.85));
            }
            if (disc.getQuantity() != 1) {
                newpurchased.getDiscLongMap().put(disc, (long) disc.getQuantity());
            } else {
                newpurchased.getDiscLongMap().put(disc, (long) 1);
            }
        }
        newpurchased.setAmountDisc(quantityOfDisc);
        save(newpurchased);

        checkIfUserPurchasedOver500000();
        clearCartList();
    }

    public void addToCart(Disc disc) {
        boolean discInCart = false;

        for (int i = 0; i < cartList.size(); i++) {
            if (disc.getId() == cartList.get(i).getId()) {
                cartList.get(i).setQuantity(cartList.get(i).getQuantity() + 1);
                discInCart = true;
            }
        }
        if (!discInCart) {
            cartList.add(disc);
        }
        countTotalSum();
        quantityOfDisc++;
    }

    private void countTotalSum() {
        totalPriceOfAllItems = 0;
        for (int k = 0; k < cartList.size(); k++) {
            totalPriceOfAllItems += (cartList.get(k).getPrice() * cartList.get(k).getQuantity());
        }
    }

    public void clearCartList() {
        cartList.clear();
        setQuantityOfDisc(0);
        setTotalPriceOfAllItems(0);
    }

    public void checkIfUserPurchasedOver500000() {
        List<PurchasedDisc> purchasedDiscList = purchasedDiscRepository.getAllPurchasedListByCustomerId(customerService.getCurrentCustomer().getId());
        float sum = 0;
        for (int i = 0; i < purchasedDiscList.size(); i++) {
            sum += purchasedDiscList.get(i).getTotalAmount();
        }
        if (sum > 500000) {
            customerService.getCurrentCustomer().setPremium(true);
            customerService.save(customerService.getCurrentCustomer());
        }
    }

    public List<Disc> getCartList() {
        return cartList;
    }

    public void setCartList(List<Disc> cartList) {
        this.cartList = cartList;
    }

    public int getQuantityOfDisc() {
        return quantityOfDisc;
    }

    public void setQuantityOfDisc(int quantityOfDisc) {
        this.quantityOfDisc = quantityOfDisc;
    }

    public float getTotalPriceOfAllItems() {
        return totalPriceOfAllItems;
    }

    public void setTotalPriceOfAllItems(float totalPriceOfAllItems) {
        this.totalPriceOfAllItems = totalPriceOfAllItems;
    }

    @Transactional
    public void save(PurchasedDisc purchasedDisc) {
        purchasedDiscRepository.save(purchasedDisc);
    }
}