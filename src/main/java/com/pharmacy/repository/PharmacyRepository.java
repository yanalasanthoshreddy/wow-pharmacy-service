package com.pharmacy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pharmacy.entity.Pharmacy;

@Repository
public interface PharmacyRepository extends JpaRepository<Pharmacy,Long>{

}
