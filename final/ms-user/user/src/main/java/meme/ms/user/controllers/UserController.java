package meme.ms.user.controllers;

import jakarta.validation.Valid;
import meme.ms.user.dtos.UserDto;
import meme.ms.user.models.UserModel;
import meme.ms.user.repositories.UserRepository;
import meme.ms.user.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class UserController {

	final UserService userService;
	final UserRepository userRepository;
	private static final Logger log = LoggerFactory.getLogger(UserController.class);

	public UserController(UserService userService, UserRepository userRepository) {
		this.userService = userService;
		this.userRepository = userRepository;
	}

	@PostMapping("/users")
	//TODO Verificar se o email já está cadastrado em outro usuário
	public ResponseEntity<UserModel> saveUser(@RequestBody @Valid UserDto userDto) {

		var userModel = new UserModel();
		BeanUtils.copyProperties(userDto, userModel);

		try {
			UserModel savedUser = userService.save(userModel);
			log.info("Usuário criado com sucesso: id={}", savedUser.getUserId());
			return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
		} catch (Exception e) {
			log.error("Erro ao salvar novo usuário", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	//Essa rota não deve estar em produção
	@GetMapping("/users")
	public ResponseEntity<List<UserModel>> findUsers() {

		return ResponseEntity.status(HttpStatus.OK).body(userRepository.findAll());
	}


	@GetMapping("/users/{id}")
	public Boolean userExists(@PathVariable UUID id) {

		try {
			var user = userRepository.findById(id);

			if (user.isPresent()) {
				user.ifPresent(userDB -> log.info("Usuário encontrado: id: {}", userDB.getUserId()));
				return true;
			} else {
				log.info("Usuário não encontrado");
				return false;
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
