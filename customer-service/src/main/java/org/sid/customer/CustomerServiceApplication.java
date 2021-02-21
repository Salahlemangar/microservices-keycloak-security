package org.sid.customer;

import org.sid.customer.entities.Customer;
import org.sid.customer.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CustomerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }

    @Bean

    CommandLineRunner start(CustomerRepository customerRepository) {
        return args -> {
            customerRepository.save(new Customer(null,"Othmane","othyrz@gmail.com"));
            customerRepository.save(new Customer(null,"Othy","othy@gmail.com"));
            customerRepository.save(new Customer(null,"Otho","otho@gmail.com"));
            customerRepository.findAll().forEach(c->{
                System.out.println(c.toString());
            });
        };

    }
}
