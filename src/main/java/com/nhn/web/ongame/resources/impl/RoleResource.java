package com.nhn.web.ongame.resources.impl;

import com.nhn.web.ongame.models.Role;
import com.nhn.web.ongame.resources.IResource;
import com.nhn.web.ongame.services.IService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/role")
public class RoleResource implements IResource<Role> {

    private static final Logger log = LoggerFactory.getLogger(RoleResource.class);

    @Autowired
    private IService<Role> roleIService;

    @Override
    public ResponseEntity<Collection<Role>> findAll() {
        return new ResponseEntity<>(roleIService.findAll(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Role> findById(Long id) {
        Optional<Role> book = roleIService.findById(id);
        return new ResponseEntity<>(book.get(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Role> save(Role role) {
        return new ResponseEntity<>(roleIService.saveOrUpdate(role), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Role> update(Role role) {
        return new ResponseEntity<>(roleIService.saveOrUpdate(role), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<HttpStatus> deleteById(Long id) {
        try {
            roleIService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
