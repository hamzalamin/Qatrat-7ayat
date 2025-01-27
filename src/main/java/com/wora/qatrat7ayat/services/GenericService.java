package com.wora.qatrat7ayat.services;

import java.util.List;

public interface GenericService<CREATE_DTO, UPDATE_DTO, DTO, ID>{
    DTO create(CREATE_DTO dto);
    DTO findById(ID id);
    DTO update(UPDATE_DTO dto, ID id);
    List<DTO> findAll(Integer pageNumber, Integer size);
    void delete(ID id);
}