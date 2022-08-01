package com.compass.avaliacao.dto;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class MensagemDtoResponse {

    private long idPedido;
    private BigDecimal total;
}
