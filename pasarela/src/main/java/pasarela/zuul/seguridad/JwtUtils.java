package pasarela.zuul.seguridad;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

public class JwtUtils {
	
	private static final String SECRET = "01u3J81mMk";
	private static final long EXPIRATION_TIME = 3600000; // 1 hora
	private static final String ISSUER = "Pasarela Zuul";
	
	public static String createJWT(String userId, Map<String, String> userInfo) {		
		
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
		byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECRET);
	    Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
	    
	    return Jwts.builder()
	    		   .setId(UUID.randomUUID().toString())
	    		   .setIssuer(ISSUER)
	    		   .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
	    		   .setSubject(userId)
	    		   .claim("usuario", userInfo.get("email"))
	    		   .claim("rol", userInfo.get("rol"))
	    		   .signWith(signatureAlgorithm, signingKey)
	    		   .compact();		
	}
	
	public static Claims validateJWT(String jwt) throws ExpiredJwtException, UnsupportedJwtException, 
													MalformedJwtException, SignatureException, IllegalArgumentException{
		
		// Los errores de validación se notificación con excepción
		
		return Jwts.parser()         
   			   	   .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET))
   			   	   .parseClaimsJws(jwt).getBody();		
	}
}
