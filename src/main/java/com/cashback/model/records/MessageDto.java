package com.cashback.model.records;


import com.cashback.model.enums.QueueType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class MessageDto{
    private String queueId;
    private String Message;
    private QueueType type;
}
