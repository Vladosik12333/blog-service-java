package by.vb.blogservicejava.service;

import java.util.Date;

public interface JwtService {
	/**
	 * Retrieve JWT token as String
	 *
	 * @param username name of the user
	 * @return JWT token
	 */
	String generateToken(final String username);

	/**
	 * Extract name of the user from JWT token
	 *
	 * @param token JWT token
	 * @return extracted name of the user
	 */
	String extractUsername(final String token);

	/**
	 * Extract expiration date of the JWT token
	 *
	 * @param token JWT token
	 * @return extracted date of the user
	 */
	Date extractExpiration(final String token);

	/**
	 * Return true if token validated and false if token not validated
	 *
	 * @param token JWT token
	 * @return the status of validation
	 */
	Boolean validateToken(final String token);
}
