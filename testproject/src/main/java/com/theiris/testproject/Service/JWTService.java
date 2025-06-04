package com.theiris.testproject.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.Date;

@Service
public class JWTService {
    @Value("${spring.datasource.algorithmkey}")
    private String algorithmkey;

    @Value("${spring.datasource.issuer}")
    private String issuer;

    @Value("${spring.datasource.expiry-duration}")
    private long expiryduration;

    private Algorithm algorithm;

    @PostConstruct
    public void postConstruct(){
        algorithm = Algorithm.HMAC256(algorithmkey);
    }

    public String generateToken(String username){
       return JWT.create().withClaim("username",username)
                .withExpiresAt(new Date(System.currentTimeMillis()+expiryduration))
                .withIssuer(issuer)
                .sign(algorithm);
    }




}
