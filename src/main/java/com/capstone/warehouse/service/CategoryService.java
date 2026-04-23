package com.capstone.warehouse.service;

import com.capstone.warehouse.entity.Category;
import com.capstone.warehouse.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Category getCategoryById(Integer id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + id));
    }

    public Category createCategory(Category category) {
        if (categoryRepository.existsByName(category.getName())) {
            throw new RuntimeException("Category already exists: " + category.getName());
        }
        return categoryRepository.save(category);
    }

    public Category updateCategory(Integer id, Category updated) {
        Category existing = getCategoryById(id);
        existing.setName(updated.getName());
        return categoryRepository.save(existing);
    }

    public void deleteCategory(Integer id) {
        categoryRepository.deleteById(id);
    }
}