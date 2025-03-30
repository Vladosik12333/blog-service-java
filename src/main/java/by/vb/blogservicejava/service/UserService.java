package by.vb.blogservicejava.service;

import by.vb.blogservicejava.dto.User.AuthRequestDto;
import by.vb.blogservicejava.dto.User.AuthResponseDto;
import by.vb.blogservicejava.dto.User.UserCreateUpdateDto;
import by.vb.blogservicejava.dto.User.UserDto;
import by.vb.blogservicejava.entity.AuditableEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserService {
	/**
	 * Sign up user
	 *
	 * @param userCreateUpdateDto user DTO
	 * @return created {@link UserDto}
	 */
	UserDto signUp(final UserCreateUpdateDto userCreateUpdateDto);

	/**
	 * Sign in User
	 *
	 * @param authRequestDto user authentication DTO
	 * @return authentication DTO of user {@link AuthResponseDto}
	 */
	AuthResponseDto signIn(final AuthRequestDto authRequestDto);

	/**
	 * Retrieves current user
	 *
	 * @return user DTO {@link UserDto}
	 */
	UserDto getUser();

	/**
	 * Check whether current user has the ownership access over the entity.
	 * If userId of the entity equals ID of current user or current user has role Admin
	 * {@link by.vb.blogservicejava.entity.RoleType} returns true, otherwise false
	 *
	 * @param entityId id of entity to check
	 * @param entityRepositoryClazz repository class of entity to check
	 * @return the status of ownership check
	 */
	Boolean hasOwnershipAccess(
			long entityId,
			Class<? extends JpaRepository<AuditableEntity, Long>> entityRepositoryClazz
	);
}
