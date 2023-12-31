package com.cashback.service.queue;

import com.cashback.model.queue.QueueConfig;
import com.cashback.repository.queue.IQueueRepository;
import com.rabbitmq.client.Channel;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.listener.DirectMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistry;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class QueueService implements IQueueService{

    @Autowired
    private SimpleMessageListenerContainer messageListenerContainer;

    @Autowired
    private CachingConnectionFactory connectionFactory;

    @Autowired
    SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory;

    @Autowired
    private IQueueRepository queueRepository;

    @Autowired
    private RabbitAdmin rabbitAdmin;

    public void createQueues(){
        List<String> nameQueues = this.getDynamicQueue();
        if(!nameQueues.isEmpty()){
            nameQueues.stream()
                    .map(Queue::new)
                    .toList()
                    .forEach(q-> this.rabbitAdmin.declareQueue(q));
        }
    }

    public List<QueueConfig> findAll() {
        return this.queueRepository.findAll();
    }

    public Optional<QueueConfig> findByQueueName(String name) {
        return this.queueRepository.findByQueueName(name);
    }

    public QueueConfig save(QueueConfig queueConfig){
        return this.queueRepository.save(queueConfig);
    }

    public void deleteQueues() {
        this.findAll().forEach(q-> {
            this.rabbitAdmin
                    .deleteQueue(q.getDomainId() + "." + q.getVersion() + "." + q.getNameQueue());
            System.out.println("Queue deleted: " + q.getNameQueue());
        });
    }

    public void deleteQueuesByName(String name) {
        QueueConfig q = this.findByQueueName(name)
                .orElseThrow(EntityNotFoundException::new);

        this.rabbitAdmin
                .deleteQueue(q.getDomainId() + "." + q.getVersion() + "." + q.getNameQueue());
        System.out.println("Queue deleted: " + q.getNameQueue());
    }

    public void deleteQueuesRabbitMQ(String name) {
        this.rabbitAdmin.deleteQueue(name);
        System.out.println("Queue deleted: " + name);
    }

    @Caching
    public List<String> getDynamicQueue() {
        return this.queueRepository.findAll()
                .stream()
                .map(m-> m.getDomainId() + "." + m.getVersion() + "." + m.getNameQueue())
                .toList();
    }


    public void startConsumers() {
        rabbitListenerContainerFactory.setAutoStartup(true);
        System.out.println("**** Initialize consumers  **** ");
        System.out.println("**** Consumers started     **** ");
    }

    public void stopConsumers() {
//        messageListenerContainer.stop(() -> {
//            messageListenerContainer.setRecoveryInterval(1);
//            System.out.println("**** Consumers stopped. ****");
//        });
        connectionFactory.destroy();
    }


}