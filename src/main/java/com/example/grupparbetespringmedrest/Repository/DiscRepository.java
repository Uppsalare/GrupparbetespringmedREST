package com.example.grupparbetespringmedrest.Repository;

import com.example.grupparbetespringmedrest.domain.Disc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface DiscRepository extends JpaRepository<Disc,Long> {

    //select * from Disc e where e.name like %:keyword%, nativeQuery=true)

    @Query(value="select * from Disc e where lower(e.name) like %:keyword% or lower(e.brand) like %:keyword%", nativeQuery=true)
    List <Disc> findByKeyword(@Param("keyword") String keyword);

    Disc findDiscsById (long id);
}
