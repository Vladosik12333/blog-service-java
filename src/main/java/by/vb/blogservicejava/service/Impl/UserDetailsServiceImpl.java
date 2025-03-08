package by.vb.blogservicejava.service.Impl;

import by.vb.blogservicejava.dao.UserRepository;
import by.vb.blogservicejava.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
	private final UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
		Optional<User> user = userRepository.findByUsername(username);

		return user
				.orElseThrow(() -> new UsernameNotFoundException("User by username not found."));
	}
}
