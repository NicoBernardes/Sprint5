package com.compass.avaliacao.service;

import com.compass.avaliacao.dto.request.ItemDtoRequest;
import com.compass.avaliacao.dto.request.PedidoDtoRequest;
import com.compass.avaliacao.dto.response.PedidoDtoResponse;
import com.compass.avaliacao.entity.PedidoEntity;
import com.compass.avaliacao.exceptions.DescontoInvalidException;
import com.compass.avaliacao.exceptions.PedidoNotFoundException;
import com.compass.avaliacao.repository.PedidoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;

public class PedidoService {
    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ModelMapper modelMapper;

    public PedidoDtoResponse get(Long id) {
        PedidoEntity pedido = pedidoRepository.findById(id).orElseThrow(PedidoNotFoundException::new);
        return modelMapper.map(pedido, PedidoDtoResponse.class);
    }
    public Page<PedidoDtoResponse> getAll(String cpf, Pageable sort) {
        if (cpf == null) {
            Page<PedidoEntity> all = pedidoRepository.findAll(sort);
            return all.map(pedidos -> modelMapper.map(pedidos, PedidoDtoResponse.class));
        }
        Page<PedidoEntity> all = pedidoRepository.findByCpf(cpf, sort);
        return all.map(pedidos -> modelMapper.map(pedidos, PedidoDtoResponse.class));
    }

    public PedidoDtoResponse post(PedidoDtoRequest pedido) {
        BigDecimal total = pedido.getItens().stream().map(ItemDtoRequest::getValor).reduce(BigDecimal.ZERO,BigDecimal::add);
        PedidoEntity pedidoEntity = modelMapper.map(pedido, PedidoEntity.class);

        if (pedido.getTotal() != total) {
            pedidoEntity.setTotal(total);
        }

        PedidoEntity save = pedidoRepository.save(pedidoEntity);
        return modelMapper.map(save, PedidoDtoResponse.class);
    }

    public void patch(Long id,  BigDecimal total) {
        PedidoEntity pedidoEntity = pedidoRepository.findById(id).orElseThrow(PedidoNotFoundException::new);
        if (total.compareTo(new BigDecimal(0)) != -1) {
            pedidoEntity.setTotal(total);
            pedidoRepository.save(pedidoEntity);
        } else {
            throw new DescontoInvalidException();
        }
    }

    public void delete(Long id) {
        PedidoEntity pedidoEntity = pedidoRepository.findById(id).orElseThrow(PedidoNotFoundException::new);
        pedidoRepository.delete(pedidoEntity);
    }

}
