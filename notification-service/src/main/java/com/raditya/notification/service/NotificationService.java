package com.raditya.notification.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.raditya.notification.model.Order;

@Service
public class NotificationService {
    @Autowired
    private JavaMailSender mailSender;

public void sendEmail(Order order){
    SimpleMailMessage message = new SimpleMailMessage();
    message.setTo(order.getEmail());
    message.setSubject("Order berhasil");
    String isiEmail = "Order berhasil di chekh out!\n\n"
        + "ID Order:" + order.getId() + "\n"
        + "ID Prouduk:" + order.getProdukId() + "\n"
        + "Jumlah:" + order.getJumlah() + "\n"
        + "Total:" + order.getTotal() + "\n"
        + "Tanggal:" + order.getTanggal() + "\n";
    
    message.setText(isiEmail);
    mailSender.send(message);

    System.out.print("Email sukses dikirim ke: " + order.getEmail());

    }
    
}
