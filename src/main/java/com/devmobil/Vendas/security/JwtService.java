package com.devmobil.Vendas.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.devmobil.Vendas.domain.entity.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtService {
	
	@Value("${security.jwt.expiration}")
	private String expiration;
	@Value("${security.jwt.signature-key}")
	private String signatureKey;
	
	public String createToken(User user) {
		Long exp = Long.valueOf(this.expiration);
		LocalDateTime dateExpiration = LocalDateTime.now().plusMinutes(exp);
		Instant instant = dateExpiration.atZone(ZoneId.systemDefault()).toInstant();
		Date date = Date.from(instant);
		
		return Jwts
				.builder()
				.setSubject(user.getEmail())
				.setExpiration(date)
				.signWith(SignatureAlgorithm.HS512, this.signatureKey)
				.compact();
	}
	
	private Claims getClains(String token) throws ExpiredJwtException {
		return Jwts
				.parser()
				.setSigningKey(this.signatureKey)
				.parseClaimsJws(token)
				.getBody();
	}
	
	
	public  Boolean validToken( String token) {
		try {
			Claims claims = getClains(token);
			Date dateExpiration = claims.getExpiration();
			LocalDateTime localDateTime = dateExpiration	
						.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
			return !LocalDateTime.now().isAfter(localDateTime);
		} catch (Exception ex) {
			return false;
		}
	}
	
	public String getUserAuth(String token) throws ExpiredJwtException {
		return (String) this.getClains(token).getSubject();
	}
}
