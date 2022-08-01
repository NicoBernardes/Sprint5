package com.compass.avaliacao.rabbit;

import com.compass.avaliacao.config.AmqpConfig;
import com.compass.avaliacao.config.ConsumidorConfig;
import com.compass.avaliacao.dto.MensagemDtoResponse;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;

public class RabbitMq implements AmqpConfig<MensagemDtoResponse> {

    @Autowired
    private ConsumidorConfig consumidorConfig;

    @Override
    @RabbitListener(queues = "${spring.rabbitmq.request.routing-key.producer}")
    public void consumidor(MensagemDtoResponse mensagemDtoResponse) {
        try {
            consumidorConfig.save(mensagemDtoResponse);
        } catch (Exception e) {
            throw new AmqpRejectAndDontRequeueException(e);
        }
    }

}
