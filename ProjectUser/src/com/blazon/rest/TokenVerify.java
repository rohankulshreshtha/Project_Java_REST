package com.blazon.rest;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import java.util.Date;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.DatatypeConverter;
@Path("/Token")
public class TokenVerify {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Object getUserById(@HeaderParam("authorization") String authString){
    	
       // if(!isUserAuthenticated(authString)){
           // return "{\"error\":\"User not authenticated\"}";
      //  }
        
        //User ord = new User("success","success","success","success");
        return "{\"id\":"+parseToken(authString)+"}";
    }
     
public String parseToken(String jwt) {
		
		
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
		if(secnow-secexp>3600)
			System.out.println("token expired");
		   
		return claims.getId();
		//System.out.println("ID: " + claims.getId());
		//System.out.println("Subject: " + claims.getSubject());
		//System.out.println("Issuer: " + claims.getIssuer());
		//System.out.println("Expiration: " + claims.getExpiration());
		}
}



