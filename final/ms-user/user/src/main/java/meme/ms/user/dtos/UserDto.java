package meme.ms.user.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserDto(@NotBlank String name, @NotBlank String address, @NotBlank @Email String email) {
}
