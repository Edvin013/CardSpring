package org.example.service;

import org.example.model.Category;
import org.example.model.User;
import org.example.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setCategoryRepository(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void add(long userId, Category category) {
        User user = userService.get(userId);
        category.setUser(user);
        try {
            this.categoryRepository.save(category);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("Такая категория уже есть в базе");
        }
    }

    @Override
    public List<Category> findAllByUserId(long userId) {
       return this.categoryRepository.findAllByUserId(userId);
    }

    @Override
    public Category findById(long id) {
        return this.categoryRepository.findById(id)
                .orElseThrow(() ->new IllegalArgumentException("Не удалось найти категорию"));
    }

    @Override
    public Category update(Category category) {
        Category old = findById(category.getId());
        old.setName(category.getName());
        try {
            this.categoryRepository.save(old);
            return category;
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("Такая категория уже есть в базе");
        }
    }

    @Override
    public Category delete(long id) {
        Category category = findById(id);
        this.categoryRepository.deleteById(id);
        return category;
    }
}
