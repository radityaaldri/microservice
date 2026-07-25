package com.raditya.notification.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.raditya.notification.model.Order;
import com.raditya.notification.service.NotificationService;

@Component
public class OrderListener {
    @Autowired
    private NotificationService notificationService;

    @RabbitListener(queues = "order.notification.queue")
    public void receiveOrder(Order order){
        System.out.println("Barang berhasil di check out");
        System.out.println("ID"+ order.getId());
        System.out.println("Email"+ order.getEmail());
        notificationService.sendEmail(order);
    }

}
