package com.compass.avaliacao.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
public class ItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nome;
    private LocalDateTime dataDeCriacao;
    private LocalDateTime dataDeValidade;
    private BigDecimal valor;
    private String descricao;
    @ManyToMany(cascade = CascadeType.ALL)
    private List<OfertaEntity> ofertas;

}
