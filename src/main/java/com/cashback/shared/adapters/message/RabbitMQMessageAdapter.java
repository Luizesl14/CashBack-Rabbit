package com.cashback.shared.adapters.message;

import com.cashback.model.records.MessageDto;
import com.cashback.service.queue.IQueueService;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQMessageAdapter implements IMessageAdapter {

    @Autowired
    private IQueueService queueService;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RabbitListener(queues = "#{queueService.getDynamicQueue()}")
    @Override
    public void receiveMessage(String message,@Header(AmqpHeaders.CONSUMER_QUEUE) String queue) {
        System.out.println("Message" + message);
        System.out.println("Header" + queue);
        System.out.println("*****************************************************************");
        System.out.println("*****************************************************************");
        System.out.println("*****************************************************************");

    }
    @Override
    public void receiveMessage(MessageDto message, String queue) {

    }

    @Override
    public void sendMessage(String message) {

    }
    @Override
    public void sendMessage(MessageDto message) {
        Message msg = new Message(message.getMessage().getBytes());
        this.rabbitTemplate.send(message.getQueueId(), msg);

    }
}