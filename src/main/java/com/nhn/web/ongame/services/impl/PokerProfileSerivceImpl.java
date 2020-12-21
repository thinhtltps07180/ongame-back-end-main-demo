package com.nhn.web.ongame.services.impl;

import com.nhn.web.ongame.models.PokerProfile;
import com.nhn.web.ongame.repository.PokerProfileRepository;
import com.nhn.web.ongame.services.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class PokerProfileSerivceImpl implements IService<PokerProfile> {

    @Autowired
    private PokerProfileRepository pokerProfileRepository;

    @Override
    public Collection<PokerProfile> findAll() {
        return pokerProfileRepository.findAll();
    }

    @Override
    public Optional<PokerProfile> findById(Long id) {
        return pokerProfileRepository.findById(id);
    }

    @Override
    public PokerProfile saveOrUpdate(PokerProfile pokerProfile) {
        return pokerProfileRepository.saveAndFlush(pokerProfile);
    }

    @Override
    public String deleteById(Long id) {
         pokerProfileRepository.deleteById(id);
        return "delete success!";
    }
}
