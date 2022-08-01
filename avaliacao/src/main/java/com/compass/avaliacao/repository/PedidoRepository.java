package com.compass.avaliacao.repository;

import com.compass.avaliacao.entity.PedidoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<PedidoEntity, Long> {
    Page<PedidoEntity> findByCpf(String cpf, Pageable sort);

}
