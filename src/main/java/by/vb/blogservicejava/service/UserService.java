package by.vb.blogservicejava.service;

import by.vb.blogservicejava.dto.User.AuthRequestDto;
import by.vb.blogservicejava.dto.User.AuthResponseDto;
import by.vb.blogservicejava.dto.User.UserCreateUpdateDto;
import by.vb.blogservicejava.dto.User.UserDto;
import by.vb.blogservicejava.entity.AuditableEntity;
import org.apache.coyote.BadRequestException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserService {
	UserDto signUp(final UserCreateUpdateDto userCreateUpdateDto);

	AuthResponseDto signIn(final AuthRequestDto authRequestDto)
			throws UsernameNotFoundException, BadRequestException;

	UserDto getUser();

	Boolean hasOwnershipAccess(
			long entityId,
			Class<? extends JpaRepository<AuditableEntity, Long>> entityRepositoryClazz
	);
}
