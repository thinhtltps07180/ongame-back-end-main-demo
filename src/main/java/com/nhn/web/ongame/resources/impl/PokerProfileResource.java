package com.nhn.web.ongame.resources.impl;

import com.nhn.web.ongame.message.ResponseMessage;
import com.nhn.web.ongame.models.PokerProfile;
import com.nhn.web.ongame.models.User;
import com.nhn.web.ongame.resources.IResource;
import com.nhn.web.ongame.services.FilesStorageService;
import com.nhn.web.ongame.services.IService;
import org.springframework.core.io.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/poker-profile")
public class PokerProfileResource implements IResource<PokerProfile> {

    private static final Logger log = LoggerFactory.getLogger(UserResource.class);
    @Autowired
    private IService<PokerProfile> pokerProfileIService;

    @Autowired
    FilesStorageService storageService;

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

    @PostMapping("/uploadImg")
    public ResponseEntity<ResponseMessage> saveHaveImg(@RequestParam("file") MultipartFile file, PokerProfile pokerProfile) {
        String message = "";
        try {
            storageService.save(file);
            message = "Uploaded the file successfully: " + file.getOriginalFilename();
            String nameImg = file.getOriginalFilename();
            String url = MvcUriComponentsBuilder
                    .fromMethodName(PokerProfileResource.class, "getFile", nameImg).build().toString();
            System.out.println(url);
            System.out.println("name file is" + file.getOriginalFilename());
            System.out.println("url file" + storageService.load(file.getOriginalFilename()));

            PokerProfile domain = new PokerProfile();
            domain.setOngameId(pokerProfile.getOngameId());
            domain.setNickName(pokerProfile.getNickName());
            domain.setRegDate(pokerProfile.getRegDate());
            domain.setLastDate(pokerProfile.getLastDate());
            domain.setIp(pokerProfile.getIp());
            domain.setName(nameImg);
            domain.setUrl(url);

            pokerProfileIService.saveOrUpdate(domain);

            log.info("data : " + domain);

            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }

    @GetMapping("/files")
    public ResponseEntity<List<PokerProfile>> getListFiles() {
        List<PokerProfile> fileInfos = storageService.loadAll().map(path -> {
            String filename = path.getFileName().toString();
            String url = MvcUriComponentsBuilder
                    .fromMethodName(PokerProfileResource.class, "getFile", path.getFileName().toString()).build().toString();

            return new PokerProfile(filename, url);
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(fileInfos);
    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> getFile(@PathVariable String filename) {
        Resource file = storageService.load(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    @GetMapping("/getFileName/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> getUrl(@PathVariable String filename) throws IOException {
        Resource file = storageService.load(filename);

        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(file);
    }

    @DeleteMapping("/deletePath/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> deletePathByName(@PathVariable String filename, Long id) {
        try {
            Optional<PokerProfile> user = pokerProfileIService.findById(id);
            if(user != null){
                pokerProfileIService.deleteById(id);
                File f = new File("C:\\Users\\LNOVO\\Downloads\\ongame-back-end-main\\uploads\\" + filename);
                if (f.delete())                      //returns Boolean value
                {
                    System.out.println(f.getName() + " deleted");   //getting and printing the file name
                } else {
                    System.out.println("failed");
                }
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }else {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }
}
