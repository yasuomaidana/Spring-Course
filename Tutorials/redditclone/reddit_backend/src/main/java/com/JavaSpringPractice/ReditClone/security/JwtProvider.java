package com.JavaSpringPractice.ReditClone.security;

import com.JavaSpringPractice.ReditClone.exceptions.SpringRedditException;
import io.jsonwebtoken.*;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.security.*;
import java.security.cert.CertificateException;
import java.time.Instant;

import static java.util.Date.from;

@Service
public class JwtProvider {
    private KeyStore keyStore;
    @Value("${jwt.expiration.time}") @Getter
    private Long jwtExpirationTimeMillis;
    @PostConstruct
    public void init(){
        try{
            keyStore = KeyStore.getInstance("JKS");
            InputStream resourceAsStream = getClass()
                    .getResourceAsStream("/springblog.jks");
            keyStore.load(resourceAsStream,"secret".toCharArray());//Certificate and its password
        } catch (KeyStoreException|CertificateException|IOException|NoSuchAlgorithmException e) {
            throw new SpringRedditException("Exception occurred while loading keyStore");
        }
    }
    public String generateToken(Authentication authentication){
        User principal = (User) authentication.getPrincipal();
        return Jwts.builder()
                .setSubject(principal.getUsername())
                .setIssuedAt(from(Instant.now()))
                .signWith(getPrivateKey())
                .setExpiration(from(Instant.now().plusMillis(jwtExpirationTimeMillis))).
                 compact();
    }

    public String generateTokenWithUserName(String username){
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(from(Instant.now()))
                .signWith(getPrivateKey())
                .setExpiration(from(Instant.now().plusMillis(jwtExpirationTimeMillis))).
                compact();
    }

    private PrivateKey getPrivateKey(){
        try{
            return (PrivateKey) keyStore.getKey("springblog","secret".toCharArray());
        } catch (UnrecoverableKeyException|KeyStoreException|NoSuchAlgorithmException e) {
            throw new SpringRedditException("Exception occurred while retrieving public key from keystore");
        }
    }
    public boolean validateToken(String jwt){
        try {
            Jwts.parserBuilder().setSigningKey(getPublicKey()).build().parseClaimsJws(jwt);
        }
        catch(ExpiredJwtException expiredJwtException){
            throw new SpringRedditException("Expired token");
        }
        return true;
    }

    private PublicKey getPublicKey() {
        try {
            return keyStore.getCertificate("springblog").getPublicKey();
        } catch (KeyStoreException e) {
            e.printStackTrace();
            throw new SpringRedditException("Exception while getting public key");
        }
    }

    public String getUsernameFromJwt(String token){
        Claims claims = Jwts.parserBuilder().setSigningKey(getPublicKey()).build().parseClaimsJws(token).getBody();
        return claims.getSubject();
    }
}
