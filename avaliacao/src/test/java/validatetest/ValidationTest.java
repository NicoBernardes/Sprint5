package validatetest;

import com.compass.avaliacao.exception.DataInvalidException;
import com.compass.avaliacao.exception.DataVencidaException;
import com.compass.avaliacao.validate.Validation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;


@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class ValidationTest {
    private Validation validation;

    @BeforeEach
    public void set() {
        this.validation = new Validation();
    }

    @Test
    void dataException() {
        Assertions.assertThrows(DataInvalidException.class,() -> validation.setIso("11/11/2111 11:11:11"));
    }

    @Test
    void checkData() {
        validation.validade("22/22/2222 22:22:22", "22/22/2223 22:22:22");
    }

    @Test
    void checkDataException() {
        Assertions.assertThrows(DataInvalidException.class,() -> validation.validade("11/11/2111 11:11:11", "11/11/2111 11:11:11"));
    }

    @Test
    void validateValidade() {
        LocalDateTime now = LocalDateTime.now().plusHours(1);
        String hora = validation.setBrData(now);

        validation.validate(hora);
    }

    @Test
    void validadeException() {
        LocalDateTime now = LocalDateTime.now().minusHours(1);
        String hora = validation.setBrData(now);
        Assertions.assertThrows(DataVencidaException.class,() -> validation.validate(hora));
    }

}
