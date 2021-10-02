package com.homecook.homecookstorefront.util;

import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.interfaces.RSAPrivateKey;
import java.time.Instant;
import java.util.Date;

@Component(value = "jwtTokenUtil")
public class JwtTokenUtil
{
    @Value("${jwt.private.key}")
    RSAPrivateKey key;

    public String generateToken(Authentication authentication)
    {
        Instant now = Instant.now();
        long expiry = 7 * 24 * 60 * 60 * 1000; // one week

        JWTClaimsSet claims = new JWTClaimsSet.Builder()
                .issueTime(new Date(now.toEpochMilli()))
                .expirationTime(new Date(now.plusMillis(expiry).toEpochMilli()))
                .subject(authentication.getName())
                .build();

        JWSHeader header = new JWSHeader.Builder(JWSAlgorithm.RS256).build();
        SignedJWT jwt = new SignedJWT(header, claims);
        return sign(jwt).serialize();
    }


    private SignedJWT sign(SignedJWT jwt)
    {
        try
        {
            jwt.sign(new RSASSASigner(this.key));
            return jwt;
        }
        catch (Exception ex)
        {
            throw new IllegalArgumentException(ex);
        }
    }
}
