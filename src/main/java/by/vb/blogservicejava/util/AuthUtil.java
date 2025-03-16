package by.vb.blogservicejava.util;

import by.vb.blogservicejava.entity.User;
import by.vb.blogservicejava.exception.NotAuthorizedException;
import lombok.experimental.UtilityClass;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Objects;

@UtilityClass
public class AuthUtil {
	public static User getCurrentUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		if (Objects.isNull(auth) || !(auth instanceof UsernamePasswordAuthenticationToken)) {
			throw new NotAuthorizedException();
		}

		return (User) auth.getPrincipal();
	}
}
