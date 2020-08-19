package com.example.grupparbetespringmedrest.Repository;

import com.example.grupparbetespringmedrest.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface LoginRepository extends JpaRepository<Customer,Long> {

 Customer findByEmail(String email);

}
