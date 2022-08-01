package com.compass.avaliacao.service;

import com.compass.avaliacao.dto.request.ItemDtoRequest;
import com.compass.avaliacao.dto.request.OfertaDtoRequest;
import com.compass.avaliacao.dto.request.PedidoDtoRequest;
import com.compass.avaliacao.entity.PedidoEntity;
import com.compass.avaliacao.repository.PedidoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
class PedidoServiceTest {

    @Mock
    private PedidoRepository pedidoRepository;
    @InjectMocks
    private PedidoService pedidoService;
    @Mock
    private ModelMapper modelMapper;
    private List<ItemDtoRequest> itens;
    private PedidoEntity pedidoEntity;
    private PedidoDtoRequest pedidoDtoRequest;

    private List<OfertaDtoRequest> ofertas;

    @BeforeEach
    public void setUp() {
        this.pedidoEntity = new PedidoEntity();
        this.pedidoEntity.setId(1L);
        this.itens = new ArrayList<>();
        this.ofertas = new ArrayList<>();

        ofertas.add(OfertaDtoRequest.builder()
                .nome("qSDqasd213")
                .dataDeCriacao("11/11/2111 11:11:11")
                .dataDeValidade("11/11/2112 11:11:11")
                .desconto(new BigDecimal("11.1"))
                .descricao("11 desconto").build());
        itens.add(ItemDtoRequest.builder()
                .nome("Lápis")
                .ofertas(ofertas)
                .valor(new BigDecimal(100))
                .descricao("Verde")
                .dataDeValidade("11/11/2112 11:11:11")
                .dataDeCriacao("11/11/2111 11:11:11").build());

        this.pedidoDtoRequest = PedidoDtoRequest.builder().cpf("98765432100").itens(itens).build();
    }

    @Test
    void get() {
        Mockito.when(pedidoRepository.findById(1L)).thenReturn(Optional.ofNullable(this.pedidoEntity));

        pedidoService.get(1L);
        Mockito.verify(pedidoRepository).findById(this.pedidoEntity.getId());
    }

    @Test
    void post() {
        Mockito.when(modelMapper.map(this.pedidoDtoRequest, PedidoEntity.class)).thenReturn(this.pedidoEntity);

        pedidoService.post(this.pedidoDtoRequest);
        Mockito.verify(pedidoRepository).save(this.pedidoEntity);
    }

    @Test
    void delete() {
        Mockito.when(pedidoRepository.findById(1L)).thenReturn(Optional.ofNullable(this.pedidoEntity));

        pedidoService.delete(1L);
        Mockito.verify(pedidoRepository).delete(this.pedidoEntity);

    }

    @Test
    void patch() {
        Mockito.when(pedidoRepository.findById(1L)).thenReturn(Optional.ofNullable(this.pedidoEntity));

        pedidoService.patch(1L, new BigDecimal(15));
        Mockito.verify(pedidoRepository).save(this.pedidoEntity);

    }
}
