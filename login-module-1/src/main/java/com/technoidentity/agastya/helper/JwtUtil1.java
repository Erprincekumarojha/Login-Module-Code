package com.technoidentity.agastya.helper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.technoidentity.agastya.model.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
/*Method for generating token
 * validate token
 * expired token check all in side thi method
 */
@Component
public class JwtUtil1 {
	
	//public static final long serialVersionUID=-123456;
	//public static final long JWT_TOKEN_VALIDITY=5*60*60;
	
	private String SECRET_KEY = "java";

    public String extractPhoneNo(String token) {
        return extractClaim(token, Claims::getSubject);
    }


	public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public String generateToken(User userDetail) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userDetail.getPhoneNo());
    }

    private String createToken(Map<String, Object> claims, String phoneNo) {

        return Jwts.builder().setClaims(claims).setSubject(phoneNo).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
    }

    public Boolean validateToken(String token, User userDetails) {
        final String phoneNo = extractPhoneNo(token);
        return (phoneNo.equals(userDetails.getPhoneNo()) && !isTokenExpired(token));
    }	

}
