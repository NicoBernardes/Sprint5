package servicetest;

import com.compass.avaliacao.dto.request.ItemDtoRequest;
import com.compass.avaliacao.dto.request.OfertaDtoRequest;
import com.compass.avaliacao.dto.request.PedidoDtoRequest;
import com.compass.avaliacao.entity.PedidoEntity;
import com.compass.avaliacao.repository.PedidoRepository;
import com.compass.avaliacao.service.PedidoService;
import com.compass.avaliacao.validate.Validation;
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
public class PedidoServiceTest {
    @InjectMocks
    private PedidoService pedidoService;
    @Mock
    private PedidoRepository pedidoRepository;
    @Mock
    private ModelMapper modelMapper;
    @Mock
    private Validation validation;
    private PedidoEntity pedidoEntity;
    private PedidoDtoRequest pedidoDtoRequest;
    private List<ItemDtoRequest> itens;
    private List<OfertaDtoRequest> ofertas;


    @BeforeEach
    public void set() {
        this.pedidoEntity = new PedidoEntity();
        this.pedidoEntity.setId(1L);
        List<ItemDtoRequest> itens = new ArrayList<>();
        List<OfertaDtoRequest> ofertas = new ArrayList<>();

        ofertas.add(OfertaDtoRequest.builder()
                .nome("asdasqe")
                .dataDeCriacao("11/11/2111 11:11:11")
                .dataDeValidade("22/22/2222 22:22:22")
                .desconto(new BigDecimal("10.0"))
                .descricao("33 desconto").build());
        itens.add(ItemDtoRequest.builder()
                .nome("Gol")
                .dataDeCriacao("11/11/2111 11:11:11")
                .dataDeValidade("22/2/2222 22:22:22")
                .valor(new BigDecimal("33.0"))
                .descricao("carro").ofertas(ofertas).build());

        this.pedidoDtoRequest = PedidoDtoRequest.builder().cpf("66413286221").itens(itens).build();
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
    void patch() {
        Mockito.when(pedidoRepository.findById(1L)).thenReturn(Optional.ofNullable(this.pedidoEntity));

        pedidoService.patch(1L, new BigDecimal(15));
        Mockito.verify(pedidoRepository).save(this.pedidoEntity);

    }

    @Test
    void delete() {
        Mockito.when(pedidoRepository.findById(1L)).thenReturn(Optional.ofNullable(this.pedidoEntity));

        pedidoService.delete(1L);
        Mockito.verify(pedidoRepository).delete(this.pedidoEntity);

    }
}
