package com.cashback.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "PRO_QUEUE")
public class QueueConfig {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer Id;

    @Column(name = "domain_id")
    private String domainId;

    @Column(name = "version")
    private String version;

    @Column(name = "name_queue")
    private String nameQueue;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private QueueType type;

    @ManyToMany(mappedBy = "queues")
    private List<Customer> customers;

}
