package com.raditya.order.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.raditya.order.model.Order;
import com.raditya.order.repository.OrderRepository;
import com.raditya.order.vo.Produk;
import com.raditya.order.vo.ResponseTemplate;

import jakarta.transaction.Transactional;

import org.springframework.cloud.client.discovery.DiscoveryClient;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private RestTemplate restTemplate;

    public List<Order> getAll(){
        return orderRepository.findAll();
    }
    
    @Autowired
    private RabbitTemplate rabbitTemplate;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public Order createOrder(Order order){
        order.setTanggal(LocalDateTime.now().format(formatter));
        Order saveOrder = orderRepository.save(order);
        System.out.println("ID terkirim: " + saveOrder.getId());
        rabbitTemplate.convertAndSend(
            "",
            "order.notification.queue",
            saveOrder);
        return saveOrder;
    }


    @Transactional
    public void update(Long orderId, Integer jumlah, String tanggal, String status) {
        // Auto generated method s
        Order order = orderRepository.findById(orderId).orElseThrow(()
                -> new IllegalStateException("Order tidak ada"));
        if (jumlah != null) {
            order.setJumlah(jumlah);
        } if (tanggal != null && tanggal.length() > 0
                && !Objects.equals(order.getTanggal(), tanggal)) {
            order.setTanggal(tanggal);
        }
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    public List<ResponseTemplate> getOrderWithProdukById(Long id){
        List<ResponseTemplate> resoponseList = new ArrayList<>();
        Order order = getOrderById(id);
        ServiceInstance serviceInstance = discoveryClient.getInstances("PRODUK").get(0);
        Produk produk = restTemplate.getForObject(serviceInstance.getUri() + "/api/produk/"
                + order.getProdukId(), Produk.class);
        ResponseTemplate vo = new ResponseTemplate();
        vo.setOrder(order);
        vo.setProduk(produk);
        resoponseList.add(vo);
        return resoponseList;
    }

    public void deleteOrder(long id){
        orderRepository.deleteById(id);
    }
}
