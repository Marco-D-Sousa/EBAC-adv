package meme.ms.category.models;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "TB_CATEGORY")
@Data
public class CategoryModel implements Serializable {

	private static final long serialVersion = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID categoryId;
	private String name;
	private String description;
	@CreationTimestamp
	@Column(name = "created_at", updatable = false)
	private LocalDateTime created_at;
}
