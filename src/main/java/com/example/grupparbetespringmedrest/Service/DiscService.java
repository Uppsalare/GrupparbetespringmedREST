package com.example.grupparbetespringmedrest.Service;

import com.example.grupparbetespringmedrest.Repository.DiscRepository;
import com.example.grupparbetespringmedrest.Repository.PurchasedDiscRepository;
import com.example.grupparbetespringmedrest.domain.Disc;
import com.example.grupparbetespringmedrest.domain.PurchasedDisc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class DiscService {

    private DiscRepository discRepository;
    private PurchasedDiscRepository purchasedDiscRepository;

    @Autowired
    public DiscService(DiscRepository discRepository, PurchasedDiscRepository purchasedDiscRepository) {
        this.discRepository = discRepository;
        this.purchasedDiscRepository = purchasedDiscRepository;
    }

    public List<Disc> getAllDiscs() {
        return discRepository.findAll();
    }

    public Optional<Disc> getDiscById(long id) {
        return discRepository.findById(id);
    }


    public  List<Disc> findByKeyword(String keyword){
        return discRepository.findByKeyword(keyword);
    }

    public List<Disc> getDiscFromPurchasedId(long id) {
        float checkIfSameTotalAmount = 0;
        List<Disc> listOfDisc = new ArrayList<>();
        Map<Long, PurchasedDisc> mapList = purchasedDiscRepository.findAllMap(id);
        for (Map.Entry<Long, PurchasedDisc> entry : mapList.entrySet()) {
            for (Map.Entry<Disc, Long> entry2 : entry.getValue().getDiscLongMap().entrySet()) {
                Disc disc = discRepository.findDiscsById(entry2.getKey().getId());
                disc.setQuantity(Integer.parseInt(entry2.getValue().toString()));
                listOfDisc.add(disc);
                checkIfSameTotalAmount += disc.getPrice() * disc.getQuantity();
            }

            if (checkIfSameTotalAmount != entry.getValue().getTotalAmount()) {
                for (Disc disc : listOfDisc) {
                    disc.setPrice((int) (disc.getPrice() * 0.85));
                }
            }
        }
        return listOfDisc;
    }

}