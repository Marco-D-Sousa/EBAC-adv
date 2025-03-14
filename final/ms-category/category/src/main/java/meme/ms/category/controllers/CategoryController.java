package meme.ms.category.controllers;

import jakarta.validation.Valid;
import meme.ms.category.dtos.CategoryDto;
import meme.ms.category.models.CategoryModel;
import meme.ms.category.repositories.CategoryRepository;
import meme.ms.category.services.CategoryService;
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

	public CategoryController(CategoryService categoryService, CategoryRepository categoryRepository) {
		this.categoryService = categoryService;
		this.categoryRepository = categoryRepository;
	}

	@PostMapping("/categories")
	public ResponseEntity<CategoryModel> saveCategory(@RequestBody @Valid CategoryDto categoryDto) {

		RestTemplate restTemplate = new RestTemplate();
		String userUrl = "http://localhost:8081/users/d8b70d55-d97b-458e-8350-6cd59e3794be"; //d8b70d55-d97b-458e-8350-6cd59e3794be
		var userExist = restTemplate.getForObject(userUrl, Boolean.class);

		if (Boolean.TRUE.equals(userExist)) {
			System.out.println("Usuário Encontrado");

			var categoryModel = new CategoryModel();
			BeanUtils.copyProperties(categoryDto, categoryModel);

			return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.save(categoryModel));
		} else {
			System.out.println("Usuário Não Encontrado");
		}

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
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
