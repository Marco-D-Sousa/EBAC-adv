package meme.ms.meme.dtos;

import java.util.UUID;

public record MemeDto(String name, String description, String url, UUID categoryId) {
}
