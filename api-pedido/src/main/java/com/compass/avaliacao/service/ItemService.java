package com.compass.avaliacao.service;

import com.compass.avaliacao.entity.ItemEntity;
import com.compass.avaliacao.exceptions.ItemNotFoundException;
import com.compass.avaliacao.exceptions.NomeEmptyException;
import com.compass.avaliacao.repository.ItemRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class ItemService {

    @Autowired
    private ItemRepository repository;
    @Autowired
    private ModelMapper modelMapper;

    public void patch(Long id, String nome) {
        ItemEntity itemEntity = repository.findById(id).orElseThrow(ItemNotFoundException::new);
        if (!nome.isBlank()) {
            itemEntity.setNome(nome);
            repository.save(itemEntity);
        } else {
            throw new NomeEmptyException();
        }
    }
}
