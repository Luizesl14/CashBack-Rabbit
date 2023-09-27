package com.cashback.model.customer;


import com.cashback.model.queue.QueueConfig;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "PRO_CUSTOMER")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer Id;

    @Column(name = "tax_id")
    private String taxId = UUID.randomUUID().toString();

    @Column(name = "name")
    private String name;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "pro_customer_and_queue",
            joinColumns = @JoinColumn(name = "customer_id"),
            inverseJoinColumns = @JoinColumn(name = "queue_id")
    )
    private List<QueueConfig> queues;

    public Customer(String name, List<QueueConfig> queues) {
        this.name = name;
        this.queues = queues;
    }

    public Customer() {
    }
}
