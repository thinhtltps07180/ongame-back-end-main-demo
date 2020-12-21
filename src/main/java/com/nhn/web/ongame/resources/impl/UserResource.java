package com.nhn.web.ongame.resources.impl;

import com.nhn.web.ongame.models.Role;
import com.nhn.web.ongame.models.User;
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
@RequestMapping("/api/user")
public class UserResource implements IResource<User> {
    private static final Logger log = LoggerFactory.getLogger(UserResource.class);

    @Autowired
    private IService<User> userIService;

    @Override
    public ResponseEntity<Collection<User>> findAll() {
        return new ResponseEntity<>(userIService.findAll(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<User> findById(Long id) {
        Optional<User> user = userIService.findById(id);
        return new ResponseEntity<>(user.get(), HttpStatus.OK);

    }

    @Override
    public ResponseEntity<User> save(User user) {
        return new ResponseEntity<>(userIService.saveOrUpdate(user), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<User> update(User user) {
        return new ResponseEntity<>(userIService.saveOrUpdate(user), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<HttpStatus> deleteById(Long id) {
        try {
            userIService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



}
