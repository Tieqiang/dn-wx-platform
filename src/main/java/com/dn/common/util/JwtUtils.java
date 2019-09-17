package com.dn.common.util;

import com.dn.common.config.SystemProperties;
import io.jsonwebtoken.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


@Component
public class JwtUtils {

    private final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

    @Autowired
    private SystemProperties systemProperties;

    /**
     * 生成token
     *
     * @param userDetails
     * @return
     */
    public String createToken(UserDetails userDetails) {
        String username = userDetails.getUsername();
        Map<String, Object> claims = new HashMap<>();
        Iterator<? extends GrantedAuthority> iterator = userDetails.getAuthorities().iterator();
        StringBuilder stringBuilder = new StringBuilder();
        while (iterator.hasNext()) {
            GrantedAuthority authority = iterator.next();
            stringBuilder.append(authority.getAuthority());
            stringBuilder.append(",");
        }
        String value = stringBuilder.toString();
        if (StringUtils.isNotEmpty(value)) {
            value = value.substring(0, value.length() - 1);
        }
        claims.put("authority", value);
        String compact = Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS256, systemProperties.getAuthention().getSceret())
                .setExpiration(new Date(System.currentTimeMillis() + systemProperties.getAuthention().getExpire()))
                .setSubject(username).compact();
        return compact;
    }


    public Jws<Claims> getClaimsJws(String token) {
        try {
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(systemProperties.getAuthention().getSceret()).parseClaimsJws(token);
            return claimsJws;
        } catch (ExpiredJwtException ex) {
            throw ex;
        }
    }

    public String getUserName(String token) {
        String subject = this.getClaimsJws(token).getBody().getSubject();
        return subject;
    }

    /**
     * 过期时间
     *
     * @param claimsJws
     * @return
     */
    public Date getExpireDate(Jws<Claims> claimsJws) {
        Object exp = claimsJws.getBody().get("exp");
        return new Date(Long.parseLong(exp.toString()));
    }


    /**
     * 是否过期
     *
     * @param token
     * @return
     */
    public boolean isExpire(String token) {
        Jws<Claims> claimsJws = getClaimsJws(token);
        return new Date().before(getExpireDate(claimsJws));
    }


    /**
     * 验证tokean
     *
     * @param token
     * @param userDetails
     * @return
     */
    public boolean validToken(String token, UserDetails userDetails) {
        String username = userDetails.getUsername();
        return this.getUserName(token).equals(username) && !isExpire(token);
    }

}
