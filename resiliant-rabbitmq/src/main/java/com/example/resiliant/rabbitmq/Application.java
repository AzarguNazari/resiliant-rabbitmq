package com.example.resiliant.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class Application {

	private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

	@Autowired
	private RabbitMqConfigurator rabbitMqConfigurator;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@GetMapping()
	public void publish(){
		try{
//			if(rabbitMqConfigurator.rabbitTemplate().send()){
//				System.out.println("Yes, the rabbitmq is running");
//			}
//			else System.out.println("No, the rabbitmq is not running");
			rabbitMqConfigurator.rabbitTemplate().convertAndSend("amq.atopic","", "Hello world");
		}
		catch(Exception ex){
			LOGGER.error("Sorry, the rabbitmq connection has issue. Temporarily, we are storing your message locally");
		}
	}
}
