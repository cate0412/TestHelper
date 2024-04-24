package com.example.testhelper.config;

import com.example.testhelper.service.user.UserDetailsServiceImpl;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenProvider {

    @Autowired
    private final UserDetailsServiceImpl userDetailsService;
    private final Key key;

    @Value("${jwt.access_valid_hour}")
    private String tokenValidTime;

    public JwtTokenProvider(UserDetailsServiceImpl userDetailsService,
                            @Value("${jwt.secret}") String secretKey) {
        this.userDetailsService = userDetailsService;
        this.key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
    }

    public String createToken(String email) {
        Claims claims = Jwts.claims().setSubject(email);

        Date now = new Date();

        return Jwts.builder()
                .signWith(key, SignatureAlgorithm.HS256)
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + Long.parseLong(tokenValidTime) * 1000))
                .compact();
    }

    public Authentication getAuthentication(String token) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(this.getUsername(token));

        return new UsernamePasswordAuthenticationToken(userDetails, "",
                userDetails.getAuthorities());


    }

    public String getUsername(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody()
                .getSubject();
    }

    public String resolveToken(HttpServletRequest request) {
        String header = request.getHeader("Authorization");

        if (header != null) {
            return header.substring("Bearer ".length());
        } else {
            return null;
        }
    }

    public boolean validateToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);

            if (!claims.getBody().getExpiration().before(new Date())) {
                return !claims.getBody().getExpiration().before(new Date());
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

}
