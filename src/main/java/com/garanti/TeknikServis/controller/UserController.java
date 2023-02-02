package com.garanti.TeknikServis.controller;

import com.garanti.TeknikServis.model.Users;
import com.garanti.TeknikServis.repo.UserRepo;
import com.garanti.TeknikServis.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

@RestController
@AllArgsConstructor
public class UserController
{
    private UserService service;
    private MessageSource messageSource;

    @GetMapping(path = "test")
    @Secured(value = "ROLE_ADMIN")
    public String getByUserName()
    {
        //localhost:9090/test
        return "merhaba";

    }


    @PostMapping(path = "save", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> save( @RequestBody Users users,@RequestHeader(name = "Accept-Language", required = false) Locale locale)
    {
        //localhost:9090/save
        // {"username":"test","password":"1234","user_EMAIL":"test@gmail.com"}
        if (service.userSave(users.getUSERNAME(),users.getPASSWORD(), users.getUSER_EMAIL()))
        {
            return ResponseEntity.status(HttpStatus.CREATED).body(messageSource.getMessage("user.save.success", null,locale));
        }
        else
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(messageSource.getMessage("user.save.fail", null, locale));
        }
    }

}
