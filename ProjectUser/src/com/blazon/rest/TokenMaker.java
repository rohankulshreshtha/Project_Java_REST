package com.blazon.rest;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import java.security.Key;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date; 

public class TokenMaker  {
	public static void main(String args[]){
		String jwt=TokenMaker.createJWT("1", "rohan", "authntication", 1);
		System.out.println();
		System.out.print(jwt);
		System.out.println();
	TokenMaker.parseJWT("eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxIiwiaWF0IjoxNDY1NDYwNTY3LCJzdWIiOiJhdXRobnRpY2F0aW9uIiwiaXNzIjoicm9oYW4iLCJleHAiOjE0NjU0NjA1Njd9.wVjixWhJGWRlj-QtEgI0FsYfydsvOmZlM2uaCOdajf8");
		System.out.print("complete");
	}
	
	
	private static void parseJWT(String jwt) {
		
		
		//SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
		
		//This line will throw an exception if it is not a signed JWS (as expected)
		//byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary();
		//Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
		 
		Claims claims = Jwts.parser()         
				   .setSigningKey(DatatypeConverter.parseBase64Binary("rohan"))
				   .parseClaimsJws(jwt).getBody();    
		long nowMillis = System.currentTimeMillis();
		Date now = new Date(nowMillis);
		@SuppressWarnings("deprecation")
		int secnow=now.getHours()*3600+now.getMinutes()*60+now.getSeconds();
		@SuppressWarnings("deprecation")
		int secexp=claims.getExpiration().getHours()*3600+claims.getExpiration().getMinutes()*60+claims.getExpiration().getSeconds();
		System.out.println(secnow-secexp);
		if(secnow-secexp>3600)
			System.out.println("token expired");
		   
		System.out.println("ID: " + claims.getId());
		System.out.println("Subject: " + claims.getSubject());
		System.out.println("Issuer: " + claims.getIssuer());
		System.out.println("Expiration: " + claims.getExpiration());
		}
	
	
	
	private static String createJWT(String id, String issuer, String subject, long ttlMillis) {
		 
		//The JWT signature algorithm we will be using to sign the token
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
		
		long nowMillis = System.currentTimeMillis();
		Date now = new Date(nowMillis);
		 
		//We will sign our JWT with our ApiKey secret
		
		byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary("rohan");
		Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
		 
		  //Let's set the JWT Claims
		JwtBuilder builder = Jwts.builder().setId(id)
		                                .setIssuedAt(now)
		                                .setSubject(subject)
		                                .setIssuer(issuer)
		                                .signWith(signatureAlgorithm, signingKey);
		 
		 //if it has been specified, let's add the expiration
		if (ttlMillis >= 0) {
		    long expMillis = nowMillis + ttlMillis;
		    Date exp = new Date(expMillis);
		    builder.setExpiration(exp);
		}
		 
		 //Builds the JWT and serializes it to a compact, URL-safe string
		return builder.compact();
		}
}
