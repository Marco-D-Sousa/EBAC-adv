package meme.ms.user.controllers;

import jakarta.validation.Valid;
import meme.ms.user.dtos.UserDto;
import meme.ms.user.models.UserModel;
import meme.ms.user.repositories.UserRepository;
import meme.ms.user.services.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@RestController
public class UserController {

	final UserService userService;
	final UserRepository userRepository;

	public UserController(UserService userService, UserRepository userRepository) {
		this.userService = userService;
		this.userRepository = userRepository;
	}

	@PostMapping("/users")
	public ResponseEntity<UserModel> saveUser(@RequestBody @Valid UserDto userDto) {

		var userModel = new UserModel();
		BeanUtils.copyProperties(userDto, userModel);

		return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(userModel));
	}

	@GetMapping("/users")
	public ResponseEntity<List<UserModel>> findUsers() {

		return ResponseEntity.status(HttpStatus.OK).body(userRepository.findAll());
	}

	@GetMapping("/users/{id}")
	public Boolean userExists(@PathVariable UUID id) {

		var response = userRepository.findById(id);
		return response.isPresent();
	}
}
