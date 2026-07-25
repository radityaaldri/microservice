package com.raditya.product.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.raditya.product.model.produk;
import com.raditya.product.repository.ProdukRepository;

@Service
public class ProdukService {
    @Autowired
    private ProdukRepository produkRepository;

    public List<produk> getAllProduks() {
        return produkRepository.findAll();
    }

    public produk getProdukbyId(Long id) {
        return produkRepository.findById(id).orElse(null);
    }

    public produk createProduk(produk produk) {
        return produkRepository.save(produk);
    }

    public void deleteProduk(Long id) {
        produkRepository.deleteById(id);
    }
}
