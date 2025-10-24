package com.hackathon.HackathonManagement.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;
import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {
	// Use a base64-encoded string for the key (must be at least 256 bits for HS256)
	private static final String SECRET = "bXlTdXBlclNlY3JldEtleU1ha2VJdFN1ZmZpY2llbnQhQCMkKiY="; // Example, replace with your own
	private final Key SECRET_KEY = Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET));
	private final long EXPIRATION_TIME = 1000 * 60 * 60; // 1 hour

	public String generateToken(String email, String role, Integer userId) {
	return Jwts.builder()
		.setSubject(email)
		.claim("role", role)
		.claim("userId", userId)
		.setIssuedAt(new Date())
		.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
		.signWith(SECRET_KEY)
		.compact();
	}

	public boolean validateToken(String token) {
		try {
			Jwts.parserBuilder().setSigningKey(SECRET_KEY).build().parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public String extractEmail(String token) {
		return Jwts.parserBuilder().setSigningKey(SECRET_KEY).build().parseClaimsJws(token).getBody().getSubject();
	}

	public String extractRole(String token) {
		return Jwts.parserBuilder().setSigningKey(SECRET_KEY).build().parseClaimsJws(token).getBody().get("role", String.class);
	}

	public Integer extractUserId(String token) {
		return Jwts.parserBuilder().setSigningKey(SECRET_KEY).build().parseClaimsJws(token).getBody().get("userId", Integer.class);
	}
}
