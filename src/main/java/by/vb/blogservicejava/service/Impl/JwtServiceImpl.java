package by.vb.blogservicejava.service.Impl;

import by.vb.blogservicejava.service.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Slf4j
@Service
public class JwtServiceImpl implements JwtService {
	@Value("${JWT_SECRET}")
	private String SECRET;

	@Value("${JWT_PERIOD}")
	private long PERIOD;

	public String generateToken(String userName) {
		log.info("Generating token for user={}", userName);

		final Map<String, Object> claims = new HashMap<>();
		String token = createToken(claims, userName);

		log.info("Created token={}", token);

		return token;
	}

	public String extractUsername(final String token) {
		log.info("Extracting username from token={}", token);

		String username = extractClaim(token, Claims::getSubject);

		log.info("Extracted username={}", username);

		return username;
	}

	public Date extractExpiration(final String token) {
		log.info("Extracting expiration date from token={}", token);

		Date expirationDate = extractClaim(token, Claims::getExpiration);

		log.info("Extracted expirationDate={}", expirationDate);

		return expirationDate;
	}

	public Boolean validateToken(final String token) {
		return extractExpiration(token).after(new Date());
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

	private String createToken(final Map<String, Object> claims, final String userName) {
		return Jwts.builder()
				.setClaims(claims)
				.setSubject(userName)
				.setIssuedAt(new Date())
				.setExpiration(new Date(
						System.currentTimeMillis() + PERIOD))
				.signWith(getSignKey(), SignatureAlgorithm.HS256)
				.compact();
	}

	private Key getSignKey() {
		final byte[] keyBytes = Decoders.BASE64.decode(SECRET);
		return Keys.hmacShaKeyFor(keyBytes);
	}
}
