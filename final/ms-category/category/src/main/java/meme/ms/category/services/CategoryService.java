package meme.ms.category.services;

import meme.ms.category.models.CategoryModel;
import meme.ms.category.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

	final CategoryRepository categoryRepository;

	public CategoryService(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}

	public CategoryModel save(CategoryModel categoryModel) {
		return categoryRepository.save(categoryModel);
	}
}
