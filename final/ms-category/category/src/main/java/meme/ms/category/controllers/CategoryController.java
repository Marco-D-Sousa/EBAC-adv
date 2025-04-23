package meme.ms.category.controllers;

import jakarta.validation.Valid;
import meme.ms.category.dtos.CategoryDto;
import meme.ms.category.models.CategoryModel;
import meme.ms.category.repositories.CategoryRepository;
import meme.ms.category.services.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class CategoryController {

	final CategoryService categoryService;
	final CategoryRepository categoryRepository;
	private static final Logger log = LoggerFactory.getLogger(CategoryController.class);
	private final RestTemplate restTemplate = new RestTemplate();

	public CategoryController(CategoryService categoryService, CategoryRepository categoryRepository) {
		this.categoryService = categoryService;
		this.categoryRepository = categoryRepository;
	}

	@PostMapping("/categories")
	public ResponseEntity<CategoryModel> saveCategory(@RequestBody @Valid CategoryDto categoryDto) {

		UUID userID = categoryDto.getUserID();
		String userUrl = "http://localhost:8081/users/" + userID;

		Boolean userExists;
		try {
			userExists = restTemplate.getForObject(userUrl, Boolean.class);
		} catch (Exception e) {
			log.error("Erro ao verificar o usuário {}", userID, e);
			return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build();
		}

		if (Boolean.TRUE.equals(userExists)) {

			var categoryModel = new CategoryModel();
			BeanUtils.copyProperties(categoryDto, categoryModel);

			CategoryModel savedCategory = categoryService.save(categoryModel);

			log.info("Categoria criada com sucesso: nome: {} - id: {} - data: {}",
					savedCategory.getName(), savedCategory.getCategoryId(), savedCategory.getCreated_at());
			return ResponseEntity.status(HttpStatus.CREATED).body(savedCategory);
		} else {
			log.warn("Usuário {} não encontrado, categoria não será criada.", userID);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

	@GetMapping("/categories")
	public ResponseEntity<List<CategoryModel>> findCategories() {

		return ResponseEntity.status(HttpStatus.OK).body(categoryRepository.findAll());
	}

	@GetMapping("/categories/{id}")
	public ResponseEntity<Optional<CategoryModel>> categoryExists(@PathVariable UUID id) {

		return ResponseEntity.status(HttpStatus.OK).body(categoryRepository.findById(id));
	}
}
