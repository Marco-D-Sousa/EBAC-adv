package meme.ms.category.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
public class CategoryDto{
	@NotBlank
	private String name;

	@NotBlank
	private String description;

	private UUID userID;
}

