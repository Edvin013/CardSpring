package org.example.controller;

import org.example.dto.ResponseResult;
import org.example.model.Category;
import org.example.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    private CategoryService categoryService;

    @Autowired
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping(path = "/{userId}")
    public ResponseEntity<ResponseResult<Category>> add(@PathVariable long userId,
                                                        @RequestBody Category category) {
        try {
            this.categoryService.add(userId, category);
            return new ResponseEntity<>(new ResponseResult<>(null, category), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = "/search/{userId}")
    public ResponseEntity<ResponseResult<List<Category>>> getByUserId(
            @PathVariable long userId) {
        List<Category> categories = this.categoryService.findAllByUserId(userId);
        return new ResponseEntity<>(new ResponseResult<>(null, categories), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ResponseResult<Category>> getById(@PathVariable long id) {
        Category category = this.categoryService.findById(id);
        return new ResponseEntity<>(new ResponseResult<>(null, category), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<ResponseResult<Category>> update(
            @RequestBody Category category) {
        try {
            Category res = this.categoryService.update(category);
            return new ResponseEntity<>(new ResponseResult<>(null, res), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<ResponseResult<Category>> delete(@PathVariable long id) {
        try {
            Category category = this.categoryService.delete(id);
            return new ResponseEntity<>(new ResponseResult<>(null, category), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null),
                    HttpStatus.BAD_REQUEST);
        }
    }
}
