package com.compass.avaliacao.controller;

import com.compass.avaliacao.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/item")
@RestController
public class ItemController {
    @Autowired
    private ItemService itemService;

    @PatchMapping("/{id}/{nome}")
    public ResponseEntity<Void> patch(@PathVariable Long id, @PathVariable String nome) {
        itemService.patch(id, nome);
        return ResponseEntity.noContent().build();
    }
}
