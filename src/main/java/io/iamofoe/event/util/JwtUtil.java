package io.iamofoe.event.util;

import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import io.iamofoe.event.domain.model.Role;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;

import java.util.Date;


@Component
public class JwtUtil {

    @Value("${JWT_SECRETE}")
    private String JWT_SECRET;
    @Value("${EMAIL_CLAIM}")
    private String EMAIL_CLAIM;

    public String generateAccessToken(String email) {
        return JWT.create()
                .withClaim(EMAIL_CLAIM, email)
                .withExpiresAt(new Date(System.currentTimeMillis() + (60 * 60 * 12 * 1000)))
                .sign(Algorithm.HMAC256(JWT_SECRET.getBytes()));
    }

    public String validateToken(String token) {
        DecodedJWT decodedJWT = decodeJWT(token);
        return convertStringToClaim(decodedJWT.getClaim(EMAIL_CLAIM));
    }

    private DecodedJWT decodeJWT(String token) throws JWTVerificationException {
        return JWT.require(Algorithm.HMAC256(JWT_SECRET.getBytes()))
                .build().verify(token);
    }

    private String convertStringToClaim(Claim claim) {
        return claim.toString().replace("\"", "");
    }
}
