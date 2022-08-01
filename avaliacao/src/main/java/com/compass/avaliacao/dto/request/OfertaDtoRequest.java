package com.compass.avaliacao.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OfertaDtoRequest {
    @NotBlank
    private String nome;
    @NotBlank
    private String dataDeCriacao;
    @NotBlank
    private String dataDeValidade;
    @Positive
    private BigDecimal desconto;
    @NotBlank
    private String descricao;
}
