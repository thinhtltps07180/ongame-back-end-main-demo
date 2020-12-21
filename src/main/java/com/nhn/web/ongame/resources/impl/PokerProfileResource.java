package com.nhn.web.ongame.resources.impl;

import com.nhn.web.ongame.models.PokerProfile;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collection;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/poker-profile")
public class PokerProfileResource implements IResource<PokerProfile> {

    private static final Logger log = LoggerFactory.getLogger(UserResource.class);
    @Autowired
    private IService<PokerProfile> pokerProfileIService;

    @Override
    public ResponseEntity<Collection<PokerProfile>> findAll() {
        return new ResponseEntity<>(pokerProfileIService.findAll(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PokerProfile> findById(Long id) {
        Optional<PokerProfile> user = pokerProfileIService.findById(id);
        return new ResponseEntity<>(user.get(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PokerProfile> save(PokerProfile pokerProfile) {
        return new ResponseEntity<>(pokerProfileIService.saveOrUpdate(pokerProfile), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<PokerProfile> update(PokerProfile pokerProfile) {
        return new ResponseEntity<>(pokerProfileIService.saveOrUpdate(pokerProfile), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<HttpStatus> deleteById(Long id) {
        try {
            pokerProfileIService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    @RequestMapping("/uploadImage")
//    public ResponseEntity<?> uploadImage(@RequestParam("file")MultipartFile file) {
//        System.out.println("is work");
//        if(file.isEmpty()){
//            throw new RuntimeException("File give is not valid");
//        }
//
//        String folder = ""
//
//        try{
//
//        }catch (IOException e){
//            throw new RuntimeException(e);
//        }
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
}
