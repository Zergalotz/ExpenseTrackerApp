package com.stephenellis.expensetracker.service;

import com.stephenellis.expensetracker.model.Category;
import com.stephenellis.expensetracker.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found with id " + id));
    }

    public Category updateCategory(Long id, Category category) {
        Category existingCategory = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found with id " + id));

        existingCategory.setName(category.getName());
        existingCategory.setDescription(category.getDescription());

        return categoryRepository.save(existingCategory);
    }

    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}
