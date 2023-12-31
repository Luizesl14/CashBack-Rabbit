package com.cashback.service.queue;

import com.cashback.model.queue.QueueConfig;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface IQueueService {
    void createQueues();
    List<QueueConfig> findAll();

    Optional<QueueConfig> findByQueueName(String name);

    QueueConfig save(QueueConfig queueConfig);
    void deleteQueues();

    void deleteQueuesByName(String name);
    void deleteQueuesRabbitMQ(String name);
    List<String> getDynamicQueue();

}
