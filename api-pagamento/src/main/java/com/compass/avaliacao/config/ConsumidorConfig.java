package com.compass.avaliacao.config;

import com.compass.avaliacao.dto.MensagemDtoResponse;

public interface ConsumidorConfig {

    void save(MensagemDtoResponse msg) throws Exception;
}
