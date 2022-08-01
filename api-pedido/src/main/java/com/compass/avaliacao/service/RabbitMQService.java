package com.compass.avaliacao.service;

import com.compass.avaliacao.amqp.AmqpProdutor;
import com.compass.avaliacao.dto.request.MensagemDtoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQService {

    @Autowired
    private AmqpProdutor<MensagemDtoRequest> amqpProdutor;

    public void sendToConsumer(MensagemDtoRequest msgDto) {
        amqpProdutor.producer(msgDto);
    }
}
