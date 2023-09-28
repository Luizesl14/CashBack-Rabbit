package com.cashback;

import com.cashback.service.customer.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CashbackApplication {

	public static void main(String[] args) {
		SpringApplication.run(CashbackApplication.class, args);
	}
	

}
