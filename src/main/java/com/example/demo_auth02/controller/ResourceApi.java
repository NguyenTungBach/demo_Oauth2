package com.example.demo_auth02.controller;


import com.example.demo_auth02.service.AuthService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Log4j2
@RestController
@RequestMapping(path = "api/v1/google")
@CrossOrigin(origins = "*")
public class ResourceApi {
    @Autowired
    private AuthService authService;

    @RequestMapping(value = "/image", method = RequestMethod.GET)
    public ResponseEntity<?> getImage(
            @RequestHeader(name="Authorization") String token
    ) throws IOException {
        if (!authService.getScope(token).equals("image")){
            return new ResponseEntity("Bạn không được quyền",HttpStatus.BAD_REQUEST);
        }
        if (!authService.getResource(token).isPresent()){
            return new ResponseEntity("Token hết hạn",HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity(
                    "Thông tin ảnh",
                    HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public ResponseEntity<?> getUser(
            @RequestHeader(name="Authorization") String token
    ) throws IOException {
        if (!authService.getScope(token).equals("user")){
            return new ResponseEntity("Bạn không được quyền",HttpStatus.BAD_REQUEST);
        }
        if (!authService.getResource(token).isPresent()){
            return new ResponseEntity("Token hết hạn",HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity(
                    "Thông người dùng",
                    HttpStatus.OK);
        }
    }
}
