package com.compass.avaliacao.service;


import com.compass.avaliacao.dto.request.ItemDtoRequest;
import com.compass.avaliacao.dto.request.OfertaDtoRequest;
import com.compass.avaliacao.entity.ItemEntity;
import com.compass.avaliacao.repository.ItemRepository;
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

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
public class ItemServiceTest {

    @Mock
    private ItemRepository itemRepository;
    @Mock
    private ModelMapper modelMapper;
    @InjectMocks
    private ItemService itemService;
    private ItemEntity itemEntity;
    private ItemDtoRequest itemDtoRequest;
    private List<OfertaDtoRequest> ofertas;

    @BeforeEach
    public void setUp() {
        this.itemEntity = new ItemEntity();
        this.ofertas = new ArrayList<>();

        ofertas.add(OfertaDtoRequest.builder()
                .nome("qSDqasd213")
                .dataDeCriacao("11/11/2111 11:11:11")
                .dataDeValidade("11/11/2112 11:11:11")
                .desconto(new BigDecimal("11.1"))
                .descricao("11 desconto").build());


        this.itemDtoRequest = ItemDtoRequest.builder()
                .nome("Lápis")
                .ofertas(ofertas)
                .valor(new BigDecimal(100))
                .descricao("Verde")
                .dataDeValidade("11/11/2112 11:11:11")
                .dataDeCriacao("11/11/2111 11:11:11").build();
    }


    @Test
    void patch() {
        Mockito.when(itemRepository.findById(1L)).thenReturn(Optional.ofNullable(this.itemEntity));

        itemService.patch(1L, "Lápiseira ");
        Mockito.verify(itemRepository).save(this.itemEntity);
    }
}
