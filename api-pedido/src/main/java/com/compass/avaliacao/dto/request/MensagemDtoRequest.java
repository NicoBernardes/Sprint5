package com.compass.avaliacao.dto.request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class MensagemDtoRequest {

    private Long idPedido;
    private BigDecimal total;
}
