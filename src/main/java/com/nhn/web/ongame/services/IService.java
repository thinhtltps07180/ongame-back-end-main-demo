package com.nhn.web.ongame.services;

import com.nhn.web.ongame.models.User;

import java.util.Collection;
import java.util.Optional;

public interface IService<T> {

    Collection<T> findAll();

    Optional<T> findById(Long id);

    T saveOrUpdate(T t);

    String deleteById(Long id);
    
}
