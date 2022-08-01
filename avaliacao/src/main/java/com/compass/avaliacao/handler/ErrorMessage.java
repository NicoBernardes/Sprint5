package com.compass.avaliacao.handler;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorMessage {
    private String msg;
    private List<String> erro;

    public ErrorMessage(List<String> erro) {
        this.erro = erro;
    }

    public ErrorMessage(String msg) {
        this.msg = msg;
    }
}
