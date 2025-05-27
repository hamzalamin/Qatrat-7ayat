package com.wora.qatrat7ayat.services;

import java.util.List;

public interface GenericService<C, U, D, I>{
    D create(C dto);
    D findById(I id);
    D update(U dto, I id);
    List<D> findAll(Integer pageNumber, Integer size);
    void delete(I id);
}