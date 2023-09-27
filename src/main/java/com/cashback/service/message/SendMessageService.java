package com.cashback.service.message;

import com.cashback.model.queue.QueueConfig;
import com.cashback.model.enums.QueueType;
import com.cashback.model.records.MessageDto;
import com.cashback.repository.queue.IQueueRepository;
import com.cashback.shared.adapters.message.IMessageAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SendMessageService{

    @Autowired
    private IQueueRepository queueRepository;

    @Autowired
    private IMessageAdapter adapter;

    public Optional<QueueConfig> findByType(String name) {
        return this.queueRepository.findByType(name);
    }

    public String sortedMessage(QueueType type){
        String result = null;
        for (QueueConfig q: this.getQueue()){

            if(QueueType.IN.name().equals(type.name()))
               result=q.getDomainId() + "." + q.getVersion() + "." + q.getNameQueue();

            if(QueueType.OUT.name().equals(type.name()))
                result= q.getDomainId() + "." + q.getVersion() + "." + q.getNameQueue();
        }
        return result;
    }


    public void sendingMessage(MessageDto messageDto){
        messageDto.setQueueId(this.sortedMessage(messageDto.getType()));
        this.adapter.sendMessage(messageDto);
    }

    @Cacheable(value = "queuesCacheable")
    public List<QueueConfig> getQueue() {
        return this.queueRepository.findAll();
    }

}