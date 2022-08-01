package com.compass.avaliacao.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemDtoRequest {
    @NotBlank
    private String nome;
    @NotBlank
    private String dataDeCriacao;
    @NotBlank
    private String dataDeValidade;
    @Positive
    private BigDecimal valor;
    @NotBlank
    private String descricao;
    private List<@Valid OfertaDtoRequest> ofertas;
}
