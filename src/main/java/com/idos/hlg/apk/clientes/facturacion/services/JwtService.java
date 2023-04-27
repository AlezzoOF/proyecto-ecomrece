package com.idos.hlg.apk.clientes.facturacion.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    private static final String SECRET_KEY = "50655368566D597133743677397A24432646294A404E635266546A576E5A7234";


    //extraer datos del token
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSingInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    //Extraer key
    private @NotNull Key getSingInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    //Extraer una sola claim
    public <T> T extractClaim(String token, @NotNull Function<Claims, T> claimsResolve) {
        final Claims claims = extractAllClaims(token);
        return claimsResolve.apply(claims);
    }

    //Extraer nombre usuario

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }


    //generar token con extra claims
    public String generateToken(Map<String, Object> extraClaims, @NotNull UserDetails user) {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(user.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))//tiempo de expiracion 24H
                .signWith(getSingInKey(), SignatureAlgorithm.HS256)
                .compact();

    }

    //generar token solo de usuario

    public String generateToken(UserDetails user) {
        return generateToken(new HashMap<>(), user);
    }

    //metodo para validar
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    //Comprobar la fecha d expiracion del token
    private boolean isTokenExpired(String token) {
        return extractExpirationDate(token).before(new Date());
    }

    //Extraer la fecha de expiracion
    private Date extractExpirationDate(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
}
