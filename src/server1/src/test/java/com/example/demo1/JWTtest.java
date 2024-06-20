package com.example.demo1;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JWTtest {
    @Test
    public void testGen(){
        Map<String,Object> chaims= new HashMap<>();
        chaims.put("id","18025160328");
        chaims.put("fakename","adas");
        String token=JWT.create()
                .withClaim("user",chaims)
                .withExpiresAt(new Date(System.currentTimeMillis()+1000*60*12))
                .sign(Algorithm.HMAC256("ada"));
        System.out.print(token+'\n');
    }
    @Test
    public void testParse(){
        String token="eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyIjp7ImlkIjoiMTgwMjUxNjAzMjgiLCJmYWtlbmFtZSI6ImFkYXMifSwiZXhwIjoxNzE3NTYwMzE2fQ.NOqxhk3w4c9DwkxEuX8W4l6H45a6B5BgaT7_MeP1_fk";
        JWTVerifier jwtVerifier=JWT.require(Algorithm.HMAC256("ada")).build();
        DecodedJWT decodedJWT=jwtVerifier.verify(token);
        Map<String, Claim> cliams=decodedJWT.getClaims();
        System.out.print(cliams.get("user"));

    }


}
