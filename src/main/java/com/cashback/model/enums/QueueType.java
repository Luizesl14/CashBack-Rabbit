package com.cashback.model.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum QueueType {

    IN("Entrada"),
    OUT("Saida");

    private final String desc;
    QueueType(String desc){
      this.desc = desc;
    }
}
