package com.compass.avaliacao.handler;

import com.compass.avaliacao.exceptions.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalHandlerException extends ResponseEntityExceptionHandler {
    private static final String PEDIDO_NOT_FOUND = "Pedido não encontrado.";
    private static final String VALIDADE_EXPIRADA = "Validade expirada.";
    private static final String DESCONTO_INVALID = "Valor total precisa ser positivo.";
    private static final String ITEM_NOT_FOUND = "Item não encontrado.";
    private static final String DATA_INVALID = "Data inválida.";
    private static final String NOME_EMPTY = "Campo nome não pode estar em branco.";


    @ExceptionHandler(value = PedidoNotFoundException.class)
    protected ResponseEntity<ErrorMessage> handlerPedidoNotFound(PedidoNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorMessage(PEDIDO_NOT_FOUND));
    }

    @ExceptionHandler(value = ItemNotFoundException.class)
    protected ResponseEntity<ErrorMessage> handlerItemNotFound(ItemNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorMessage(ITEM_NOT_FOUND));
    }

    @ExceptionHandler(value = DataInvalidException.class)
    protected ResponseEntity<ErrorMessage> handlerDataInvalid(DataInvalidException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorMessage(DATA_INVALID));
    }

    @ExceptionHandler(value = ValidadeExpiradaException.class)
    protected ResponseEntity<ErrorMessage> handlerValidadeExpirada(ValidadeExpiradaException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorMessage(VALIDADE_EXPIRADA));
    }

    @ExceptionHandler(value = DescontoInvalidException.class)
    protected ResponseEntity<ErrorMessage> handlerDescontoInvalid(DescontoInvalidException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorMessage(DESCONTO_INVALID));
    }

    @ExceptionHandler(value = NomeEmptyException.class)
    protected ResponseEntity<ErrorMessage> handlerNomeEmpty(NomeEmptyException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorMessage(NOME_EMPTY));
    }


    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> validationList = ex.getBindingResult().getFieldErrors().stream().map(fieldError ->
                        "O campo: " + fieldError.getField() +
                                " " + fieldError.getDefaultMessage())
                .collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorMessage(validationList));
    }
}
