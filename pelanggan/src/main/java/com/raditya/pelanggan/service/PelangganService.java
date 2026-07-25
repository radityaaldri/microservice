package com.raditya.pelanggan.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.raditya.pelanggan.model.pelanggane;
import com.raditya.pelanggan.repository.PelangganRepository;

@Service
public class PelangganService {

    @Autowired
    private PelangganRepository pelangganRepository;

    public List<pelanggane> getAllePelangganes() {
        return pelangganRepository.findAll();
    }

    public pelanggane getPelangganbyId(Long id) {
        return pelangganRepository.findById(id).orElse(null);
    }

    public pelanggane createProduk(pelanggane pelanggane) {
        return pelangganRepository.save(pelanggane);
    }

    public void deletePelanggan(Long id) {
        pelangganRepository.deleteById(id);
    }
}