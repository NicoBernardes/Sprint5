package com.compass.avaliacao.controller;

import com.compass.avaliacao.dto.request.PedidoDtoRequest;
import com.compass.avaliacao.dto.response.PedidoDtoResponse;
import com.compass.avaliacao.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.net.URI;

@RestController
@RequestMapping("/api/pedido")
public class PedidoController {

    @Autowired
    private PedidoService service;

    @GetMapping
    public ResponseEntity<Page<PedidoDtoResponse>> getAll(@RequestParam(required = false) String cpf,
                                                          @PageableDefault(sort = "id", direction = Sort.Direction.ASC) Pageable sort) {
        Page<PedidoDtoResponse> pedidos = service.getAll(cpf, sort);
        return ResponseEntity.ok(pedidos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoDtoResponse> get(@PathVariable Long id) {
        PedidoDtoResponse responsePedidoDto = service.get(id);
        return ResponseEntity.ok(responsePedidoDto);
    }

    @PostMapping
    public ResponseEntity<PedidoDtoResponse> post(@RequestBody @Valid PedidoDtoRequest pedidoDtoRequest, UriComponentsBuilder componentsBuilder) {
        PedidoDtoResponse pedidoDto = service.post(pedidoDtoRequest);
        URI uri = componentsBuilder.path("/api/pedido/{id}").buildAndExpand(pedidoDto.getId()).toUri();
        return ResponseEntity.created(uri).body(pedidoDto);
    }

    @PatchMapping("/{id}/{total}")

    public ResponseEntity<Void> patch(@PathVariable BigDecimal total, @PathVariable Long id) {
        service.patch(id, total);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
