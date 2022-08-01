package com.compass.avaliacao.service;

import com.compass.avaliacao.entity.ItemEntity;
import com.compass.avaliacao.exception.ItemNotFoundException;
import com.compass.avaliacao.exception.NomeInvalidException;
import com.compass.avaliacao.repository.ItemRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService {
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private ModelMapper modelMapper;

    public void patch(Long id, String nome) {
        ItemEntity itemEntity = itemRepository.findById(id).orElseThrow(ItemNotFoundException::new);
        if (!nome.isBlank()) {
            itemEntity.setNome(nome);
            itemRepository.save(itemEntity);
        } else {
            throw new NomeInvalidException();
        }
    }
}
