package com.compass.avaliacao.dto.response;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OfertaDtoResponse {
    private long id;
    private String nome;
    private String dataDeCriacao;
    private String dataDeValidade;
    private BigDecimal desconto;
    private String descricao;
}
