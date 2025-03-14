package meme.ms.meme.models;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "TB_MEME")
@Data
public class MemeModel implements Serializable {

	private static final long serialVersion = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID memeId;
	private String name;
	private String description;
	private String url;
	@CreationTimestamp
	@Column(name = "created_at", updatable = false)
	private LocalDateTime created_at;
	private UUID categoryId;
}

