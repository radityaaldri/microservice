package com.raditya.pelanggan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.raditya.pelanggan.model.pelanggane;
import com.raditya.pelanggan.service.PelangganService;

@RestController
@RequestMapping("/api/pelanggan")
public class PelangganController{
   @Autowired
    private PelangganService pelangganService; //nama class dan objek

    @GetMapping
    public List<pelanggane> getAllPelangganes() {
        return pelangganService.getAllePelangganes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<pelanggane> getProdukById(@PathVariable Long id){
        pelanggane pelanggane = pelangganService.getPelangganbyId(id);
        return pelanggane != null ? ResponseEntity.ok(pelanggane) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public pelanggane createProduk(@RequestBody pelanggane pelanggane) {
        return pelangganService.createProduk(pelanggane);
    }

    @DeleteMapping
    public ResponseEntity<?> deletePelanggan(@PathVariable Long id){
        pelangganService.deletePelanggan(id);
        return ResponseEntity.ok().build();
    }
}
