package com.compass.avaliacao.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Data
public class PedidoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String cpf;
    @ManyToMany(cascade = CascadeType.ALL)
    private List<ItemEntity> itens;
    private BigDecimal total;

}
