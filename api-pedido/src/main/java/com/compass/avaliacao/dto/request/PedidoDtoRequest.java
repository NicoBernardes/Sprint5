package com.compass.avaliacao.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PedidoDtoRequest {

    @CPF
    @NotBlank
    private String cpf;
    private List<@Valid ItemDtoRequest> itens;
    @Positive
    private BigDecimal total;
}
