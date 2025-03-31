package by.vb.blogservicejava.service.Impl;

import by.vb.blogservicejava.dao.UserRepository;
import by.vb.blogservicejava.dto.User.AuthRequestDto;
import by.vb.blogservicejava.dto.User.AuthResponseDto;
import by.vb.blogservicejava.dto.User.UserCreateUpdateDto;
import by.vb.blogservicejava.dto.User.UserDto;
import by.vb.blogservicejava.entity.AuditableEntity;
import by.vb.blogservicejava.entity.RoleType;
import by.vb.blogservicejava.entity.User;
import by.vb.blogservicejava.mapper.Impl.UserCreateUpdateMapper;
import by.vb.blogservicejava.mapper.Impl.UserMapper;
import by.vb.blogservicejava.service.JwtService;
import by.vb.blogservicejava.service.UserService;
import by.vb.blogservicejava.util.AuthUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
	private final UserRepository userRepository;
	private final UserCreateUpdateMapper userCreateUpdateMapper;
	private final UserMapper userMapper;
	private final JwtService jwtService;
	private final AuthenticationManager authenticationManager;
	private final ApplicationContext applicationContext;

	@Transactional
	@Override
	public UserDto signUp(final UserCreateUpdateDto userCreateUpdateDto) {
		return Optional.of(userCreateUpdateDto)
				.map(userCreateUpdateMapper::mapTo)
				.map(userRepository::save)
				.map(userMapper::mapTo)
				.orElseThrow();
	}

	@Override
	public AuthResponseDto signIn(final AuthRequestDto authRequestDto) {
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authRequestDto.getUsername(),
						authRequestDto.getPassword()));

		String token = jwtService.generateToken(authRequestDto.getUsername());
		return AuthResponseDto.builder().accessToken(token).build();
	}

	@Override
	public UserDto getUser() {
		User user = AuthUtil.getCurrentUser();

		return Optional.of(user).map(userMapper::mapTo).orElseThrow();
	}

	@Override
	public Boolean hasOwnershipAccess(
			final long entityId,
			final Class<? extends JpaRepository<AuditableEntity, Long>> entityRepositoryClazz
	) {
		log.info("UserServiceImpl#hasOwnershipAccess. entityId={} entityRepositoryClazz={}",
				entityId,
				entityRepositoryClazz);

		User user = AuthUtil.getCurrentUser();

		log.info("UserServiceImpl#hasOwnershipAccess. user={}", user);

		if (user.getRole() == RoleType.ADMIN) {
			return true;
		}

		JpaRepository<AuditableEntity, Long> entityRepository = applicationContext.getBean(
				entityRepositoryClazz);

		Optional<AuditableEntity> entity = entityRepository.findById(entityId);

		log.info("UserServiceImpl#hasOwnershipAccess. entity={}", entity);

		if (entity.isEmpty()) {
			return false;
		}

		User entityUser = getUserFromEntity(entity.get());

		log.info("UserServiceImpl#hasOwnershipAccess. user from entity={}", entityUser);

		if (Objects.isNull(entityUser)) {
			return false;
		}

		return user.getId().equals(entityUser.getId());
	}

	private User getUserFromEntity(final AuditableEntity auditableEntity) {
		Field field = ReflectionUtils.findField(auditableEntity.getClass(), "user");

		if (Objects.isNull(field)) {
			return null;
		}

		ReflectionUtils.makeAccessible(field);

		return (User) ReflectionUtils.getField(field, auditableEntity);
	}
}
