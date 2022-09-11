package com.example.resiliant.rabbitmq;

import com.rabbitmq.client.ShutdownSignalException;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.Connection;
import org.springframework.amqp.rabbit.connection.ConnectionListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfigurator {

    @Value("${rabbit.username}")
    private String rabbitmqUsername;
    @Value("${rabbit.password}")
    private String rabbitmqPassword;
    @Value("${rabbit.addresses}")
    private String rabbitmqAddress;

    public RabbitTemplate rabbitTemplate() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setAddresses(rabbitmqAddress);
        connectionFactory.setUsername(rabbitmqUsername);
        connectionFactory.setPassword(rabbitmqPassword);
        return new RabbitTemplate(connectionFactory);
    }


}
