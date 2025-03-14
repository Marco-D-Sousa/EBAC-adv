package meme.ms.meme.repositories;

import meme.ms.meme.models.MemeModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MemeRepository extends JpaRepository<MemeModel, UUID> {
}
