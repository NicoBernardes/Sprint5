package com.compass.avaliacao.amqp;

import com.compass.avaliacao.dto.request.MensagemDtoRequest;

public interface AmqpServico {
    void sendToConsumer(MensagemDtoRequest mensagemDtoRequest);
}
