package com.compass.avaliacao.dto.response;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class PedidoDtoResponse {
    private long id;
    private String cpf;
    private List<ItemDtoResponse> itens;
    private BigDecimal total;
}
