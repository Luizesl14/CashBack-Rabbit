package com.cashback.resource.messager;

import com.cashback.model.queue.QueueConfig;
import com.cashback.model.records.MessageDto;
import com.cashback.service.queue.IQueueService;
import com.cashback.service.message.SendMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/message")
public class MessagerResource implements IMessageResource<QueueConfig> {

    @Autowired
    private IQueueService messageService;

    @Autowired
    private SendMessageService sendMessage;

    @GetMapping(value = "/{id}", produces="application/json")
    @Override
    public ResponseEntity<?> findById(String t) {
        return null;
    }

    @GetMapping(value = "/all", produces="application/json")
    @Override
    public ResponseEntity<?> findAll() {
        return null;
    }

    @PostMapping( "/create")
    @Override
    public ResponseEntity<?> create() {
        this.messageService.createQueues();
        return ResponseEntity.ok().build();
    }

    @PostMapping( "/create-message")
    public ResponseEntity<?> createMessage(@RequestBody MessageDto messageDto) {
        this.sendMessage.sendingMessage(messageDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "/delete", produces="application/json")
    @Override
    public ResponseEntity<?> delete() {
        this.messageService.deleteQueues();
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value="/name/{name}" )
    public ResponseEntity<?> deleteByChannel(@PathVariable(name = "name") String name) {
        this.messageService.deleteQueuesByName(name);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value="/broker/{nameQueue}" )
    public ResponseEntity<?> deleteByChannelRabbit(@PathVariable(name = "nameQueue") String nameQueue) {
        this.messageService.deleteQueuesRabbitMQ(nameQueue);
        return ResponseEntity.ok().build();
    }
}
