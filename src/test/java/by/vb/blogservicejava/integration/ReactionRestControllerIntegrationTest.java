package by.vb.blogservicejava.integration;

import by.vb.blogservicejava.dto.Reaction.ReactionCreateUpdateDto;
import by.vb.blogservicejava.entity.ReactionType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class ReactionRestControllerIntegrationTest extends BaseIntegrationTest {
	@Autowired
	private ObjectMapper objectMapper;

	@Test
	public void getAllReactions() throws Exception {
		mockMvc.perform(get("/reactions")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().is2xxSuccessful())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.metadata.totalElements").value(1));
	}

	@Test
	public void createReaction() throws Exception {
		ReactionCreateUpdateDto reactionDto =
				ReactionCreateUpdateDto.builder()
						.reactionType(ReactionType.LIKE)
						.postId(post.getId())
						.build();

		mockMvc.perform(post("/reactions")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(reactionDto)))
				.andExpect(status().is2xxSuccessful())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.data.type").value(reactionDto.getReactionType().name()));
	}

	@Test
	public void deleteReactionById() throws Exception {
		mockMvc.perform(delete("/reactions/{id}", reaction.getId()))
				.andExpect(status().is2xxSuccessful());
	}

	@Test
	public void updateReactionById() throws Exception {
		ReactionCreateUpdateDto reactionDto =
				ReactionCreateUpdateDto.builder()
						.reactionType(ReactionType.DISLIKE)
						.postId(post.getId())
						.build();

		mockMvc.perform(put("/reactions/{id}", reaction.getId())
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(reactionDto)))
				.andExpect(status().is2xxSuccessful())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.data.type").value(reactionDto.getReactionType().name()));
	}
}
