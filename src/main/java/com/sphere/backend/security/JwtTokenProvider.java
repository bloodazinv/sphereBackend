/**
 * FileName: JwtTokenProvider
 * Author: jane
 * Date: 2023/4/9 19:47
 * Description:
 * Version:
 */

package com.sphere.backend.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.impl.DefaultClock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.text.SimpleDateFormat;

@Component
public class JwtTokenProvider {
    private static final Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);

    @Value("${app.jwtSecret}")
    private String jwtSecret;

    @Value("${app.jwtExpirationInMs}")
    private long jwtExpirationInMs;

    @Value("${app.jwtTokenHead}")
    private String tokenHead;

    private Clock clock = DefaultClock.INSTANCE;

    /**
     *
     */
    public String getToken(Authentication authentication) {
        // Date nowDate = new Date();
        // //token过期时间
        // long expireAt=nowDate.getTime() + jwtExpirationInMs * 1000;
        // Date expireDate = new Date(expireAt);
        // Map map=new HashMap<>();
        // map.put("expireAt",expireAt);
        // String token= Jwts.builder()
        //         //放入唯一标识,可以是用户名或者Id
        //         .setSubject(identityId)
        //         .setIssuedAt(nowDate)
        //         .setExpiration(expireDate)
        //         .signWith(SignatureAlgorithm.HS512, jwtSecret)
        //         //自定义属性 放入用户拥有角色和权限
        //         .claim("roleAuthorizes", Authorizes)
        //         .compact();
        // map.put("token",token);
        // return map;
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpirationInMs);

        return Jwts.builder()
                .setSubject(Long.toString(userPrincipal.getId()))
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    /**
     * 根据token获取身份信息
     */
    public Claims getTokenClaim(String token) {
        try {
            return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 判断token是否失效
     */
    public boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(clock.now());
    }

    /**
     * 根据token获取username
     */
    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    public Long getUserIdFromJWT(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();

        return Long.parseLong(claims.getSubject());
    }

    /**
     * 根据token获取失效时间
     */
    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getTokenClaim(token);
        return claimsResolver.apply(claims);
    }

}
