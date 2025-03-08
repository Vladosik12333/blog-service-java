package by.vb.blogservicejava.dto.Reaction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReactionFilterDto {
	Map<ReactionFilterField, String> filterFields = new HashMap<>();
}
