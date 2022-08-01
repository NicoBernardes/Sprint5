package servicetest;

import com.compass.avaliacao.dto.request.ItemDtoRequest;
import com.compass.avaliacao.dto.request.OfertaDtoRequest;
import com.compass.avaliacao.entity.ItemEntity;
import com.compass.avaliacao.repository.ItemRepository;
import com.compass.avaliacao.service.ItemService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
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
class ItemServiceTest {
    @InjectMocks
    private ItemService itemService;
    @Mock
    private ItemRepository itemRepository;
    @Mock
    private ModelMapper modelMapper;
    private ItemEntity itemEntity;

    @BeforeEach
        public void set() {
            ItemEntity itemEntity = new ItemEntity();
        List<OfertaDtoRequest> ofertas = new ArrayList<>();

            ofertas.add(OfertaDtoRequest.builder()
                    .nome("OQWKE212")
                    .dataDeCriacao("11/11/2111 10:10:10")
                    .dataDeValidade("10/10/2025 10:10:10")
                    .desconto(new BigDecimal("10.0"))
                    .descricao("10qasdwqe23").build());


            ItemDtoRequest itemDtoRequest = ItemDtoRequest.builder()
                    .nome("Lápis")
                    .ofertas(ofertas)
                    .valor(new BigDecimal(100))
                    .descricao("Verde")
                    .dataDeValidade("11/11/2111 11:11:11")
                    .dataDeCriacao("11/11/2111 11:11:11").build();
        }


        @Test
        @DisplayName("Deveria alterar o item.")
        void patch() {
            Mockito.when(itemRepository.findById(1L)).thenReturn(Optional.ofNullable(this.itemEntity));

            itemService.patch(1L, "Caneta");
            Mockito.verify(itemRepository).save(this.itemEntity);
        }
}
