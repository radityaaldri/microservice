package com.raditya.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.raditya.product.model.produk;

@Repository
public interface ProdukRepository extends JpaRepository<produk, Long> {

    
}