package com.compass.avaliacao.validate;

import com.compass.avaliacao.exception.DataInvalidException;
import com.compass.avaliacao.exception.DataVencidaException;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

@Component
public class Validation {

    public String setBrData (LocalDateTime data) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss").withResolverStyle(ResolverStyle.STRICT);
            return data.format(formatter);
        } catch (Exception e) {
            throw new DataInvalidException();
        }
    }
    public LocalDateTime setIso(String data) {
        try {
            DateTimeFormatter formatoBr = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss").withResolverStyle(ResolverStyle.STRICT);
            return LocalDateTime.parse(data, formatoBr);

        } catch (Exception e) {
            throw new DataInvalidException();
        }
    }

    public void validade(String dataCriacao, String dataValidade) {
        LocalDateTime localDateTimeCriacao = setIso(dataCriacao);
        LocalDateTime localDateTimeValidade = setIso(dataValidade);

        if (localDateTimeCriacao.isAfter(localDateTimeValidade)) {
            throw new DataInvalidException();
        }
    }
    public void validate(String dataValidade) {
        LocalDateTime validate = setIso(dataValidade);

        if (LocalDateTime.now().isAfter(validate)) {
            throw new DataVencidaException();
        }
    }
}
