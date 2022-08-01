package com.compass.avaliacao.controller;

import com.compass.avaliacao.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/item")
public class ItemController {
    @Autowired
    private ItemService service;

    @PatchMapping("/{id}/{nome}")
    public ResponseEntity<Void> patch(@PathVariable Long id, @PathVariable String nome) {
        service.patch(id, nome);
        return ResponseEntity.noContent().build();
    }
}
