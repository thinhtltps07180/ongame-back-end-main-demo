package com.nhn.web.ongame.resources;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

public interface IResource<T> {
    @GetMapping()
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    ResponseEntity<Collection<T>> findAll();

    @GetMapping("{id}")
    ResponseEntity<T> findById(@PathVariable Long id);

    @PostMapping()
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    ResponseEntity<T> save(@RequestBody T t);

    @PutMapping()
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    ResponseEntity<T> update(@RequestBody T t);

    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    ResponseEntity<HttpStatus> deleteById(@PathVariable Long id);
}
