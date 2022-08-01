package com.compass.avaliacao.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
@Slf4j
@RequiredArgsConstructor
public class RabbitConfig {
    @Value("${spring.rabbitmq.request.routing-key.producer}")
    private String queue;
    @Value("${spring.rabbitmq.request.exchange.producer}")
    private String exchangeName;
    @Value("${spring.rabbitmq.request.deadletter.producer}")
    private String deadLetter;

    private final ConnectionFactory connectionFactory;


    @PostConstruct
    public void createdRabbitElement() {
        log.info("Start created Rabbit configuration");
        RabbitAdmin admin = new RabbitAdmin(connectionFactory);
        createdExchange(admin);
        createdDlQueue(admin);
        createdQueue(admin);
        log.info("End Rabbit config");
    }

    private void createdExchange(RabbitAdmin admin) {
        Exchange exchange = ExchangeBuilder.directExchange(exchangeName).durable(true).build();
        admin.declareExchange(exchange);
    }

    private void createdDlQueue(RabbitAdmin admin) {
        Queue DlQueue = QueueBuilder.durable(deadLetter).build();
        Binding binding = new Binding(deadLetter, Binding.DestinationType.QUEUE, exchangeName, deadLetter, null);

        admin.declareQueue(DlQueue);
        admin.declareBinding(binding);
    }

    private void createdQueue(RabbitAdmin admin) {
        Queue queue1 = QueueBuilder
                .durable(queue)
                .withArgument("x-dead-letter-exchange", exchangeName)
                .withArgument("x-dead-letter-routing-key", deadLetter)
                .build();
        Binding binding = new Binding(queue, Binding.DestinationType.QUEUE, exchangeName, queue, null);

        admin.declareQueue(queue1);
        admin.declareBinding(binding);
    }
}
