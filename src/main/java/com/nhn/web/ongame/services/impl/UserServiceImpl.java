package com.nhn.web.ongame.services.impl;

import com.nhn.web.ongame.models.User;
import com.nhn.web.ongame.repository.UserRepository;
import com.nhn.web.ongame.services.IService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class UserServiceImpl implements IService<User> {
    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private UserRepository userRepository;

    @Override
    public Collection<User> findAll() {
        log.info("Get all user");
        return  userRepository.findAll();
    }

    @Override
    public Optional<User> findById(Long id) {
        log.info("Get user by id");
        return userRepository.findById(id);
    }

    @Override
    public User saveOrUpdate(User user) {
        log.info("Save or update user");
        return userRepository.saveAndFlush(user);
    }

    @Override
    public String deleteById(Long id) {
        log.info("Delete user by id");
        userRepository.deleteById(id);
        return "delete success!";
    }


}
