package com.sboot.SpringBootRabbitMQ.consumer;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.sboot.SpringBootRabbitMQ.config.MessagingConfig;
import com.sboot.SpringBootRabbitMQ.dto.OrderStatus;

@Component
public class User {

    @RabbitListener(queues = MessagingConfig.QUEUE)
    public void consumeMessageFromQueue(OrderStatus orderStatus) {
        System.out.println("Message recieved from queue : " + orderStatus);
    }
}
