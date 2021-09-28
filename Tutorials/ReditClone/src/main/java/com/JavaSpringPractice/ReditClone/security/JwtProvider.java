package com.JavaSpringPractice.ReditClone.security;

import com.JavaSpringPractice.ReditClone.exceptions.SpringRedditException;
import io.jsonwebtoken.Jwts;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.security.*;
import java.security.cert.CertificateException;

@Service
public class JwtProvider {
    private KeyStore keyStore;
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
        return Jwts.builder().setSubject(principal.getUsername())
                .signWith(getPrivateKey()).compact() ;
    }
    private PrivateKey getPrivateKey(){
        try{
            return (PrivateKey) keyStore.getKey("springblog","secret".toCharArray());
        } catch (UnrecoverableKeyException|KeyStoreException|NoSuchAlgorithmException e) {
            throw new SpringRedditException("Exception occurred while retrieving public key from keystore");
        }
    }
}
