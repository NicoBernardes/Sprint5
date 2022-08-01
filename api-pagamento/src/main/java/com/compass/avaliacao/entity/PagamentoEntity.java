package com.compass.avaliacao.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PagamentoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long idPedido;
    private LocalDateTime dataDeCricacao = LocalDateTime.now();
    private BigDecimal total;
}
