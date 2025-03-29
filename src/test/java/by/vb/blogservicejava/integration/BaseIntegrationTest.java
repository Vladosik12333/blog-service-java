package by.vb.blogservicejava.integration;

import by.vb.blogservicejava.dao.PostRepository;
import by.vb.blogservicejava.dao.ReactionRepository;
import by.vb.blogservicejava.dao.UserRepository;
import by.vb.blogservicejava.entity.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

@SpringBootTest
@AutoConfigureMockMvc
public class BaseIntegrationTest {
	@Autowired
	private ReactionRepository reactionRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PostRepository postRepository;

	protected User user;
	protected Post post;
	protected Reaction reaction;

	@BeforeEach
	public void setupTestData() {
		user = User.builder()
				.username("test login")
				.password("test password")
				.role(RoleType.USER)
				.build();
		userRepository.saveAndFlush(user);

		post = Post.builder().title("title of the test post").build();
		post.setUser(user);
		postRepository.saveAndFlush(post);

		reaction = Reaction.builder().type(ReactionType.LIKE).build();
		reaction.setUser(user);
		reaction.setPost(post);
		reactionRepository.saveAndFlush(reaction);

		UsernamePasswordAuthenticationToken auth =
				new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(auth);
	}

	@AfterEach
	public void cleanupTestData() {
		reactionRepository.deleteAll();
		postRepository.deleteAll();
		userRepository.deleteAll();

		SecurityContextHolder.clearContext();
	}
}
