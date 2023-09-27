package com.cashback.shared.adapters.message;

import com.cashback.model.records.MessageDto;
import org.springframework.stereotype.Component;

@Component
public interface IMessageAdapter {
    void receiveMessage(String message, String queue);
    void sendMessage(String message);
    void receiveMessage(MessageDto message, String queue);
    void sendMessage(MessageDto message);
}
