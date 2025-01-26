package org.example.service;

import org.example.model.Category;

import java.util.List;

public interface CategoryService {
    void add(long userId, Category category);

    List<Category> findAllByUserId(long userId);

    Category findById(long id);

    Category update(Category category);

    Category delete(long id);
}
