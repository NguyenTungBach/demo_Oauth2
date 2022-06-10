package com.example.demo_auth02.controller;

import com.example.demo_auth02.entity.User;
import com.example.demo_auth02.service.AuthService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Log4j2
@RestController
@RequestMapping(path = "api/v1")

public class AuthApi {
    @Autowired
    private AuthService authService;

    @RequestMapping(path = "/login",method = RequestMethod.POST)
    public ResponseEntity<?> login(@RequestBody User user) throws IOException {
        if (!authService.login(user).isPresent()){
            return new ResponseEntity(
                    "sai mat khau hoac ko ton tai",
                    HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(
                authService.login(user),
                HttpStatus.OK);
    }

    @RequestMapping(path = "/auth",method = RequestMethod.POST)
    public ResponseEntity<?> login(
            @RequestParam(name = "auth") String auth
    ) throws IOException {
        return new ResponseEntity(
                authService.checkAuthorizationCode(auth),
                HttpStatus.OK);
    }

    @RequestMapping(value = "/resource", method = RequestMethod.GET)
    public ResponseEntity<?> getResource(
            @RequestHeader (name="Authorization") String token
    ) throws IOException {
        return new ResponseEntity(
                authService.getResource(token),
                HttpStatus.OK);
    }
}
