package com.cashback;

import com.cashback.service.customer.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CashbackApplication {

	@Autowired
	private ICustomerService customerService;


	public static void main(String[] args) {
		SpringApplication.run(CashbackApplication.class, args);
	}

//	@PostConstruct
//	public void init() {
//		Customer c = new Customer();
//		c.setName("acquirer-pro");
//
//		QueueConfig queue1 = new QueueConfig();
//		queue1.setName("first");
//
//		QueueConfig queue2 = new QueueConfig();
//		queue2.setName("last");
//		c.setQueues(new ArrayList<>());
//		c.getQueues().add(queue1);
//		c.getQueues().add(queue2);
//		customerService.save(c);
//	}

}
