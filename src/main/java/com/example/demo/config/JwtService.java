package com.example.demo.config;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

	private static final String SECRET_KEY = "8DB2CF9BFB56FE79E873B6BA3A7358DB2CF9BFB56FE79E873B6BA3A735";
	
	private static final long TOKEN_VALIDITY=5*60*60;
	
	public String extractUsername(String token)
	{
		return extractClaim(token, Claims::getSubject);
	}
	
	public <T> T extractClaim(String token , Function<Claims, T> claimsResolver)
	{
		final Claims claims =extractAllClaims(token);
		return claimsResolver.apply(claims);
	}
	
	public String generateToken(Map<String , Object> extraClaims , UserDetails userDetails)
	{
		return Jwts.builder()
				.setClaims(extraClaims)
				.setSubject(userDetails.getUsername())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + TOKEN_VALIDITY * 1000))
				.signWith(getSignInkey(), SignatureAlgorithm.HS256)
				.compact();
	}
	
	
	public boolean isTokenValid(String token , UserDetails userDetails)
	{
		final String username = extractUsername(token);
		return(username.equals(userDetails.getUsername())) && !isTokenExpired(token);
	}
	
	private boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
		
	}
	
	public String generateToken(UserDetails userDetails) {
	    return generateToken(new HashMap<>(), userDetails);
	  }
	
	public Date extractExpiration(String token)
	{
		return extractClaim(token, Claims::getExpiration);
	}
	
	
	public Claims extractAllClaims(String token)
	{
		return Jwts.parserBuilder()
				.setSigningKey(getSignInkey())
				.build()
				.parseClaimsJws(token)
				.getBody();
				
	}

	private Key getSignInkey() {
		byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
		return Keys.hmacShaKeyFor(keyBytes);
	}
}
