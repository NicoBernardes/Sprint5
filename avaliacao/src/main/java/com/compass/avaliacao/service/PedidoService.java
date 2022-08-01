package com.compass.avaliacao.service;

import com.compass.avaliacao.dto.request.ItemDtoRequest;
import com.compass.avaliacao.dto.request.PedidoDtoRequest;
import com.compass.avaliacao.dto.response.PedidoDtoResponse;
import com.compass.avaliacao.entity.PedidoEntity;
import com.compass.avaliacao.exception.DescontoInvalidException;
import com.compass.avaliacao.exception.PedidoNotFoundException;
import com.compass.avaliacao.repository.PedidoRepository;
import com.compass.avaliacao.validate.Validation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class PedidoService {
    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private Validation validation;
    @Autowired
    private ModelMapper modelMapper;

    public Page<PedidoDtoResponse> getAll(String cpf, Pageable sort) {
        if (cpf == null) {
            Page<PedidoEntity> all = pedidoRepository.findAll(sort);
            return all.map(pedidos -> modelMapper.map(pedidos, PedidoDtoResponse.class));
        }
        Page<PedidoEntity> all = pedidoRepository.findByCpf(cpf, sort);
        return all.map(pedidos -> modelMapper.map(pedidos, PedidoDtoResponse.class));
    }

    public PedidoDtoResponse get(Long id) {
        PedidoEntity pedido = pedidoRepository.findById(id).orElseThrow(PedidoNotFoundException::new);
        return modelMapper.map(pedido, PedidoDtoResponse.class);
    }

    public PedidoDtoResponse post(PedidoDtoRequest pedido) {
        validaData(pedido);
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

    private void validaData(PedidoDtoRequest pedido) {
        ItemDtoRequest item = pedido.getItens().get(pedido.getItens().size() - 1);
        String dataDeCriacao = item.getOfertas().get(item.getOfertas().size() - 1).getDataDeCriacao();
        String dataDeValidade = item.getOfertas().get(item.getOfertas().size() - 1).getDataDeValidade();

        validation.validade(dataDeCriacao, dataDeValidade);
        validation.validate(dataDeValidade);
    }
}
