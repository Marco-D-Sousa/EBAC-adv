package meme.ms.category.dtos;

import jakarta.validation.constraints.NotBlank;

import java.util.Date;

public record CategoryDto(@NotBlank String name, @NotBlank String description, Date created_at) {
}
