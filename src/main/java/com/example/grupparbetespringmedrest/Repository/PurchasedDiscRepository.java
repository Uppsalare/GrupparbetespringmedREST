package com.example.grupparbetespringmedrest.Repository;

import com.example.grupparbetespringmedrest.domain.Disc;
import com.example.grupparbetespringmedrest.domain.PurchasedDisc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transaction;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public interface PurchasedDiscRepository extends JpaRepository<PurchasedDisc,Long> {

    List<PurchasedDisc> getAllPurchasedListByCustomerId(long customId);

    List<PurchasedDisc> findAllListById(long id);

    default Map<Long, PurchasedDisc> findAllMap(long id) {
        return findAllListById(id).stream().collect(Collectors.toMap(PurchasedDisc::getId, v -> v));
    }


}
