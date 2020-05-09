package com.player.music.security;

import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

import com.player.music.exception.AppException;
import com.player.music.util.Util;

@Component
public class JwtTokenProvider {

    private static final Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);

    @Value("${app.jwtSecret}")
    private String jwtSecret;

    @Value("${app.jwtExpirationInMs}")
    private int jwtExpirationInMs;

    @Autowired
    private Util utils;

    public String generateToken(Authentication authentication, Date time) {

        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

        Date expiryDate = new Date(time.getTime() + jwtExpirationInMs);

        return Jwts.builder().setId(Long.toString(userPrincipal.getId())).setSubject(Long.toString(time.getTime()))
                .setIssuedAt(new Date()).setExpiration(expiryDate).signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public Long[] getUserIdFromJWT(String token) {
        Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
        return new Long[] { Long.parseLong(claims.getId()), Long.parseLong(claims.getSubject()) };
    }

    //// get subject has date time in epocch and claims.getID has user_id
    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException ex) {
            logger.error("Invalid JWT signature");
        } catch (MalformedJwtException ex) {
            logger.error("Invalid JWT token");
        } catch (UnsupportedJwtException ex) {
            logger.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            logger.error("JWT claims string is empty.");
        } catch (ExpiredJwtException ex) {
            logger.error("JWT Expired.");
        } catch (Exception ex) {
            logger.error("JWT claims string is empty.");
        }
        return false;
    }
}
