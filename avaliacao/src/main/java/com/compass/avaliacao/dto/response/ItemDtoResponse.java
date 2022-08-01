package com.compass.avaliacao.dto.response;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class ItemDtoResponse {
    private long id;
    private String nome;
    private String dataDeCriacao;
    private String dataDeValidade;
    private BigDecimal valor;
    private String descricao;
    private List<OfertaDtoResponse> ofertas;
}
