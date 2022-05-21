package uz.pdp.appcourse.service.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import uz.pdp.appcourse.exception.JwtExpiredTokenException;
import uz.pdp.appcourse.exception.JwtValidationException;
import uz.pdp.appcourse.model.student.StudentEntity;

import java.util.Date;

public class JwtProvider {


    @Value("${jwt.access.token.secret.key}")
    public String accessTokenSecretKey;

    @Value("${jwt.refresh.token.secret.key}")
    public String refreshTokenSecretKey;

    @Value("${jwt.access.token.expired.time}")
    public long accessTokenExpiredTime;

    @Value("${jwt.refresh.token.expired.time}")
    public long refreshTokenExpiredTime;

    public String generateAccessToken(StudentEntity studentEntity) {
        return "Bearer " + Jwts.builder()
                .signWith(SignatureAlgorithm.HS512, accessTokenSecretKey)
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + accessTokenExpiredTime))
                .setSubject(studentEntity.getPhone())
//                .claim("authorities", studentEntity.getPermission())
                .compact();
    }

    public String generateRefreshToken(StudentEntity studentEntity) {
        return "Bearer " + Jwts.builder()
                .signWith(SignatureAlgorithm.HS512, refreshTokenSecretKey)
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + refreshTokenExpiredTime))
                .setSubject(studentEntity.getPhone())
//                .claim("authorities", userEntity.getPermission())
                .compact();
    }

    public Claims parseAccessToken(String accessToken) throws JwtExpiredTokenException {
        try {
            return Jwts.parser().setSigningKey(accessTokenSecretKey)
                    .parseClaimsJws(accessToken).getBody();
        } catch (ExpiredJwtException ex) {
            throw new JwtExpiredTokenException("ACCESS TOKEN EXPIRED");
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new JwtValidationException("TOKEN VALIDATION ERROR");
        }
    }

    public String getAccessTokenFromRefreshToken(String refreshToken) throws JwtExpiredTokenException {
        Claims body;
        try {
            body = Jwts.parser()
                    .setSigningKey(refreshTokenSecretKey)
                    .parseClaimsJws(refreshToken)
                    .getBody();
        } catch (ExpiredJwtException ex) {
            throw new JwtExpiredTokenException("REFRESH TOKEN EXPIRED");
        } catch (Exception ex) {
            return null;
        }

        return generateAccessToken(body.get("user", StudentEntity.class));
    }
}
