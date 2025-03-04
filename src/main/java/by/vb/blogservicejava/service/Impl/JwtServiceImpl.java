package by.vb.blogservicejava.service.Impl;

import by.vb.blogservicejava.service.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtServiceImpl implements JwtService {
	@Value("${JWT_SECRET}")
	private String SECRET;

	public String generateToken(String userName) {
		final Map<String, Object> claims = new HashMap<>();
		return createToken(claims, userName);
	}

	private String createToken(final Map<String, Object> claims, final String userName) {
		return Jwts.builder()
				.setClaims(claims)
				.setSubject(userName)
				.setIssuedAt(new Date())
				.setExpiration(new Date(
						System.currentTimeMillis() + (1000L * 60 * 60 * 24 * 30)))
				.signWith(getSignKey(), SignatureAlgorithm.HS256)
				.compact();
	}

	public String extractUsername(final String token) {
		return extractClaim(token, Claims::getSubject);
	}

	public Date extractExpiration(final String token) {
		return extractClaim(token, Claims::getExpiration);
	}

	public Boolean validateToken(final String token) {
		return extractExpiration(token).after(new Date());
	}

	private Key getSignKey() {
		final byte[] keyBytes = Decoders.BASE64.decode(SECRET);
		return Keys.hmacShaKeyFor(keyBytes);
	}

	private <T> T extractClaim(final String token, final Function<Claims, T> claimResolver) {
		final Claims claims =
				Jwts.parserBuilder()
						.setSigningKey(getSignKey())
						.build()
						.parseClaimsJws(token)
						.getBody();

		return claimResolver.apply(claims);
	}
}
