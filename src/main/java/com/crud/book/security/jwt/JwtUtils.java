package com.crud.book.security.jwt;


import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;

import org.springframework.stereotype.Component;

import com.crud.book.security.service.UserDetailsImpl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;



@Component
public class JwtUtils  {

	private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

	@Value("${book.app.jwtSecret}")
	private String jwtSecret;

	@Value("${book.app.jwtExpirationMs}")
	private int jwtExpirationMs;

	public String generateJwtToken(Authentication authentication) {

	    UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();
//	    Cara Kedua
	    try {
		    byte[] encodedSecretKey = MessageDigest.getInstance("SHA-256").digest(jwtSecret.getBytes(StandardCharsets.UTF_8));
	        SecretKey secretKey = new SecretKeySpec(encodedSecretKey, 0, encodedSecretKey.length, "HmacSHA384");

	        return Jwts.builder()
	                .setSubject(userPrincipal.getUsername())
	                .setIssuedAt(new Date())
	                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
	                .signWith(secretKey)
	                .compact();	    	
	    } catch (Exception e) {
	    	e.printStackTrace();
		}
	    return null;
	    

//	    Cara Pertama
//	    Key key = Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
//
//	    return Jwts.builder()
//	        .setSubject((userPrincipal.getUsername()))
//	        .setIssuedAt(new Date())
//	        .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
//	        .signWith(key, SignatureAlgorithm.HS384)
//	        .compact();
	    


	  }

	public String getUserNameFromJwtToken(String token) {
//		cara kedua
		try {
			byte[] encodedSecretKey = MessageDigest.getInstance("SHA-256").digest(jwtSecret.getBytes(StandardCharsets.UTF_8));
	        SecretKey secretKey = new SecretKeySpec(encodedSecretKey, 0, encodedSecretKey.length, "HmacSHA384");

	        return Jwts.parserBuilder()
	                .setSigningKey(secretKey)
	                .build()
	                .parseClaimsJws(token)
	                .getBody()
	                .getSubject();			
		} catch (Exception e) {
	        e.printStackTrace();
		}
		 return null;
		
//		Cara Pertama
//		byte[] apiKeySecretBytes = jwtSecret.getBytes(StandardCharsets.UTF_8);
//		SecretKey secretKey = new SecretKeySpec(apiKeySecretBytes, SignatureAlgorithm.HS384.getJcaName());
//
//		return Jwts.parserBuilder()
//				.setSigningKey(secretKey)
//				.build()
//				.parseClaimsJws(token)
//				.getBody()
//				.getSubject();



	}

	public boolean validateJwtToken(String authToken) {
	    try {
	      byte[] encodedSecretKey = MessageDigest.getInstance("SHA-256").digest(jwtSecret.getBytes(StandardCharsets.UTF_8));
	      SecretKey secretKey = new SecretKeySpec(encodedSecretKey, 0, encodedSecretKey.length, "HmacSHA384");

	      Jwts.parserBuilder()
	                .setSigningKey(secretKey)
	                .build()
	                .parseClaimsJws(authToken);
	      
	      return true;
	    } catch (SignatureException e) {
	      logger.error("Invalid JWT signature: {}", e.getMessage());
	    } catch (MalformedJwtException e) {
	      logger.error("Invalid JWT token: {}", e.getMessage());
	    } catch (ExpiredJwtException e) {
	      logger.error("JWT token is expired: {}", e.getMessage());
	    } catch (UnsupportedJwtException e) {
	      logger.error("JWT token is unsupported: {}", e.getMessage());
	    } catch (IllegalArgumentException e) {
	      logger.error("JWT claims string is empty: {}", e.getMessage());
	    } catch (Exception e) {
	    	logger.error("JWT Error: {}", e.getMessage());	
		}

	    return false;
	  }
}
