package com.compass.avaliacao.config;

import com.compass.avaliacao.amqp.AmqpProdutor;
import com.compass.avaliacao.dto.request.MensagemDtoRequest;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PedidoConfig implements AmqpProdutor<MensagemDtoRequest> {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${spring.rabbitmq.request.routing-key.producer}")
    private String queue;
    @Value("${spring.rabbitmq.request.exchange.producer}")
    private String exchange;

    @Override
    public void producer(MensagemDtoRequest mensagemDtoRequest) {
        try {
            rabbitTemplate.convertAndSend(exchange, queue, mensagemDtoRequest);
        } catch (Exception e) {
            throw new AmqpRejectAndDontRequeueException(e);
        }
    }

}
