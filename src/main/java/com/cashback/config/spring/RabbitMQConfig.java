package com.cashback.config.spring;

import com.cashback.repository.queue.IQueueRepository;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Configuration
@Component
public class RabbitMQConfig {

    AtomicInteger version = new AtomicInteger(1);

    @Autowired
    private IQueueRepository queueRepository;

    @Autowired
    private ConnectionFactory connectionFactory;

    @Bean
    public void queuesList() {
        List<String> nameQueues = this.getDynamicQueue();
        if(!nameQueues.isEmpty()){
            nameQueues.stream()
                    .map(Queue::new)
                    .toList()
                    .forEach(q-> this.rabbitAdmin().declareQueue(q));
        }
    }
    @Bean
    public RabbitAdmin rabbitAdmin(){
        return  new RabbitAdmin(connectionFactory);
    }

    @Bean
    public ApplicationListener<ApplicationReadyEvent> applicationListener(RabbitAdmin admin){
        return event -> admin.initialize();
    }

    @Bean
    public SimpleMessageListenerContainer messageListenerContainer() {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        return container;
    }

    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory() {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        return factory;
    }

    public List<String> getDynamicQueue() {
        return this.queueRepository.findAll()
                .stream()
                .map(m-> m.getDomainId() + "." + m.getVersion() + "." + m.getNameQueue())
                .toList();
    }

}
