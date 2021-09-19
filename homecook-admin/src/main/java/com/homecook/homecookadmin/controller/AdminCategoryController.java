package com.homecook.homecookadmin.controller;

import com.homecook.homecookadmin.controller.mapper.CategoryRestMapper;
import com.homecook.homecookadmin.dto.CategoryDTO;
import com.homecook.homecookadmin.facade.AdminCategoryFacade;
import com.homecook.homecookadmin.model.Category;
import com.homecook.homecookadmin.model.CreateCategoryRequest;
import com.homecook.homecookadmin.model.UpdateCategoryRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/admin/api/v1/categories")
public class AdminCategoryController
{
    private static final Logger log = LoggerFactory.getLogger(AdminCategoryController.class);

    private AdminCategoryFacade adminCategoryFacade;
    private CategoryRestMapper categoryRestMapper;

    @Autowired
    public AdminCategoryController(AdminCategoryFacade adminCategoryFacade, CategoryRestMapper categoryRestMapper)
    {
        this.adminCategoryFacade = adminCategoryFacade;
        this.categoryRestMapper = categoryRestMapper;
    }

    @GetMapping
    public ResponseEntity<List<Category>> listCategories() {
        List<CategoryDTO> categoryDTOS = adminCategoryFacade.getAllCategories();
        final List<Category> categories = categoryRestMapper.convertAllDTOtoResponse(categoryDTOS);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(categories);
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<Category> getCategoryDetail(@PathVariable("categoryId") Long categoryId) {
        CategoryDTO categoryDTO = adminCategoryFacade.getCategoryForId(categoryId);
        final Category category = categoryRestMapper.convertDTOtoResponse(categoryDTO);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(category);
    }

    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody CreateCategoryRequest createCategoryRequest) {
        CategoryDTO categoryDTO = categoryRestMapper.convertRequestToDTO(createCategoryRequest);
        CategoryDTO categoryData = adminCategoryFacade.createCategory(categoryDTO);
        Category category = categoryRestMapper.convertDTOtoResponse(categoryData);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(category);
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<Category> updateCategory(@PathVariable("categoryId") Long categoryId, @RequestBody UpdateCategoryRequest updateCategoryRequest) {
        final CategoryDTO categoryDTO = categoryRestMapper.convertRequestToDTO(updateCategoryRequest);
        CategoryDTO categoryData = adminCategoryFacade.updateCategory(categoryId, categoryDTO);
        Category category = categoryRestMapper.convertDTOtoResponse(categoryData);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(category);
    }


    @DeleteMapping("/{categoryId}")
    public ResponseEntity<Void> deleteCategory(@PathVariable("categoryId") Long categoryId) {
        adminCategoryFacade.deleteCategory(categoryId);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body(null);
    }



}
