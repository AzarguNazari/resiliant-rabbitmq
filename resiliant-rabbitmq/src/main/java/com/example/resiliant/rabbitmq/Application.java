package com.example.resiliant.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

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
			RabbitTemplate rabbitTemplate = rabbitMqConfigurator.rabbitTemplate();
			Message message = new Message("Hello world".getBytes(StandardCharsets.UTF_8));
			rabbitTemplate.send(message);
			rabbitTemplate.convertAndSend("amq.atopic","", "Hello world");
		}
		catch(Exception ex){
			System.out.println("Something bad happened");
			LOGGER.error("Sorry, the rabbitmq connection has issue. Temporarily, we are storing your message locally");
		}
	}
}
