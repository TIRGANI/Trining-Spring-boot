package com.example.demo.services;

import java.util.List;
import java.util.Optional;

public interface BaseService<T> {
    public T  save(T obj);


    public T  update(long id,T obj);

    public void delete(Long id);

    public Optional<T> findByIdT(Long id);

    public List<T> getAll();
}
