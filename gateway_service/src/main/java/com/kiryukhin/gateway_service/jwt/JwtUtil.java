package com.kiryukhin.gateway_service.jwt;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;

    public Claims getAllClaimsFromToken(String token) {
        Claims calims = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
        return calims;
    }

    public void validateToken(final String token) throws JwtException { //JwtTokenMalformedException, JwtTokenMissingException
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
        } catch (SignatureException ex) {
            throw new JwtException("Invalid JWT signature"); // JwtTokenMalformedException
        } catch (MalformedJwtException ex) {
            throw new JwtException("Invalid JWT token"); // JwtTokenMalformedException
        } catch (ExpiredJwtException ex) {
            throw new JwtException("Expired JWT token"); // JwtTokenMalformedException
        } catch (UnsupportedJwtException ex) {
            throw new JwtException("Unsupported JWT token"); // JwtTokenMalformedException
        } catch (IllegalArgumentException ex) {
            throw new JwtException("JWT claims string is empty."); //JwtTokenMissingException
        }
    }

}