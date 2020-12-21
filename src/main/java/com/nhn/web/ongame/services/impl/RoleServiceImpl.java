package com.nhn.web.ongame.services.impl;


import com.nhn.web.ongame.models.Role;
import com.nhn.web.ongame.repository.RoleRepository;
import com.nhn.web.ongame.services.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;


@Service
public class RoleServiceImpl implements IService<Role> {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Collection<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public Optional<Role> findById(Long id) {
        return roleRepository.findById(id);
    }

    @Override
    public Role saveOrUpdate(Role role) {
        return roleRepository.saveAndFlush(role);
    }

    @Override
    public String deleteById(Long id) {

        roleRepository.deleteById(id);
        return "delete success!";

    }
}
