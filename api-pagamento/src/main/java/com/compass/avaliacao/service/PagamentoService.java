package com.compass.avaliacao.service;

import com.compass.avaliacao.config.ConsumidorConfig;
import com.compass.avaliacao.dto.MensagemDtoResponse;
import com.compass.avaliacao.entity.PagamentoEntity;
import com.compass.avaliacao.repository.PagamentoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class PagamentoService implements ConsumidorConfig {

    @Autowired
    ModelMapper modelMapper;
    @Autowired
    PagamentoRepository pagamentoRepository;

    @Override
    public void save(MensagemDtoResponse msg) throws Exception {
        PagamentoEntity entity = modelMapper.map(msg, PagamentoEntity.class);
        pagamentoRepository.save(entity);
    }

}
