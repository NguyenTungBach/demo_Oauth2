package com.example.demo_auth02.service;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.demo_auth02.entity.Authorization;
import com.example.demo_auth02.entity.Resource;
import com.example.demo_auth02.entity.User;
import com.example.demo_auth02.entity.dto.Credential;
import com.example.demo_auth02.entity.dto.CredentialDTO;
import com.example.demo_auth02.enumAuth.Scope;
import com.example.demo_auth02.repository.AuthorizationRepository;
import com.example.demo_auth02.repository.ResourceServerRepository;
import com.example.demo_auth02.repository.UserRepository;
import com.example.demo_auth02.util.HelpConvertDate;
import com.example.demo_auth02.util.JwtUtil;
import jdk.nashorn.internal.parser.DateParser;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

import static java.util.Arrays.stream;

@Service
@Log4j2
public class AuthService {

    @Autowired
    private AuthorizationRepository authorizationRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ResourceServerRepository resourceServerRepository;


    public Optional<Authorization> login(User user){
        // kiểm tra đăng nhập
        User userByName = userRepository.findByUsername(user.getUsername()).orElse(null);
        Authorization authorization = new Authorization();

        if (userByName != null){
            authorization.setCode(UUID.randomUUID().toString());
            authorization.setScope(Scope.IMAGE.label);
            authorization.setUserId(1);
            authorization.setClientId(1);
            authorizationRepository.save(authorization);
            return Optional.ofNullable(authorization);
        } else {
            return Optional.empty();
        }

    }

    public Optional<Credential> checkAuthorizationCode(String authCode){

        Authorization authorization = authorizationRepository.findById(authCode).orElse(null);
        Credential credential =new Credential();

        CredentialDTO credentialDTO = new CredentialDTO();
        credentialDTO.setClientId(authorization.getClientId());
        credentialDTO.setUserId(authorization.getUserId());
        credentialDTO.setScope(authorization.getScope());

        if (authorization != null){
            String accessToken =  JwtUtil.generateToken(String.valueOf(credentialDTO.getClientId()),authorization.getScope(),JwtUtil.ONE_DAY * 7);
            credential.setAccessToken(accessToken);
        }
        return Optional.ofNullable(credential);
    }

    public Optional<Collection<Resource>> getResource(String token){
        String tokenDecoded = token.replace("Bearer", "").trim();
        try{
//        log.info("tokenDecoded la " + tokenDecoded);
            DecodedJWT decodedJWT = JwtUtil.getDecodedJwt(tokenDecoded);
            log.info("token DecodedJWT la " + decodedJWT);

//            String scope = getScope(decodedJWT);
//        log.info("roles la " + decodedJWT.getClaim(JwtUtil.ROLE_CLAIM_KEY).asString());
            List<Resource> resourceList = resourceServerRepository.findByScope(decodedJWT.getClaim(JwtUtil.ROLE_CLAIM_KEY).asString());
            if (resourceList != null){
                if (checkTokenIsExpire(decodedJWT)){
                    return Optional.empty();
                }
            }
            return Optional.ofNullable(resourceList);
        } catch (Exception e){
            return Optional.empty();
        }
    }

    public String getScope(String token) {
        String tokenDecoded = token.replace("Bearer", "").trim();
        DecodedJWT decodedJWT = JwtUtil.getDecodedJwt(tokenDecoded);
        return decodedJWT.getClaim(JwtUtil.ROLE_CLAIM_KEY).asString();
    }

    public boolean checkTokenIsExpire(DecodedJWT decodedJWT) {
        Date date = new Date();

        log.info("thoi gian het han " +  decodedJWT.getExpiresAt().getTime());
        if (date.getTime() > decodedJWT.getExpiresAt().getTime()){
            log.info("so sanh hom nay " + date.getTime() + " > thoi gian het han " +  decodedJWT.getExpiresAt().getTime());
            return true;
        } if (date.getTime() < decodedJWT.getExpiresAt().getTime()){
            log.info("so sanh hom nay " + date.getTime() + " < thoi gian het han " +  decodedJWT.getExpiresAt().getTime());
            return false;
        }
        return true;
    }


}
