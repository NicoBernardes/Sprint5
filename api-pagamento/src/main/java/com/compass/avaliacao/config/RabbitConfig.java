package com.compass.avaliacao.config;

import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;

public class RabbitConfig {
    @Bean
    public Jackson2JsonMessageConverter jsonConvert() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public SimpleRabbitListenerContainerFactory factory(ConnectionFactory connectionFactory,
                                                        SimpleRabbitListenerContainerFactoryConfigurer config) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        config.configure(factory, connectionFactory);
        factory.setMessageConverter(jsonConvert());
        return factory;
    }

}
