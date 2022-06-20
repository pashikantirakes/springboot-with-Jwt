package com.example.done.util;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


@Component
public class Jwt {
	
	@Value("${app.secret}")
	private String secret;

	public String generateToken(String subject) {
		return Jwts.builder()
				.setSubject(subject)
				.setIssuer("Rakesh")
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(20)))
				.signWith(SignatureAlgorithm.HS512,secret.getBytes()).compact();			
	}
	  public Claims getClaims(String token) {
		  return Jwts.parser()
				  .setSigningKey(secret.getBytes())
				  .parseClaimsJws(token).getBody();
	  }
	  public String getUsername(String token) {
		  return getClaims(token).getSubject();
	  }
}
