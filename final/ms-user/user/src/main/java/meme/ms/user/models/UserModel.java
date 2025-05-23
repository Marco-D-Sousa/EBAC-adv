package meme.ms.user.models;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "TB_USERS")
@Data
public class UserModel implements Serializable {

	private static final long serialVersion = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID userId;
	private String name;
	private String address;
	private String email;
}
