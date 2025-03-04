package by.vb.blogservicejava.service;

import java.util.Date;

public interface JwtService {
	String generateToken(final String username);
	String extractUsername(final String token);
	Date extractExpiration(final String token);
	Boolean validateToken(final String token);
}
