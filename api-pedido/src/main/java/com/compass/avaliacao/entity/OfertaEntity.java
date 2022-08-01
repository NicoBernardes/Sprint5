package com.compass.avaliacao.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
public class OfertaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nome;
    private LocalDateTime dataDeCriacao;
    private LocalDateTime dataDeValidade;
    private BigDecimal desconto;
    private String descricao;
}
