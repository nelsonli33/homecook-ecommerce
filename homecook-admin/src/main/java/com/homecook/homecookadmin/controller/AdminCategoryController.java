package com.homecook.homecookadmin.controller;

import com.homecook.homecookadmin.dto.CategoryData;
import com.homecook.homecookadmin.facade.AdminCategoryFacade;
import com.homecook.homecookadmin.form.CategoryForm;
import com.homecook.homecookadmin.util.ReqMsgValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/admin/api/v1/categories")
public class AdminCategoryController
{
    private static final Logger log = LoggerFactory.getLogger(AdminCategoryController.class);

    @Resource(name = "adminCategoryFacade")
    private AdminCategoryFacade adminCategoryFacade;


    @GetMapping
    public ResponseEntity<List<CategoryData>> listCategories() {
        List<CategoryData> categories = adminCategoryFacade.getAllCategories();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(categories);
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryData> categoryDetail(@PathVariable("categoryId") Long categoryId) {
        CategoryData categoryData = adminCategoryFacade.getCategoryForId(categoryId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(categoryData);
    }

    @PostMapping
    public ResponseEntity<CategoryData> createCategory(@RequestBody CategoryForm form) {
        ReqMsgValidator.validateCategoryForm(form);
        CategoryData categoryData = adminCategoryFacade.createCategory(fillCategoryFormToData(form));
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(categoryData);
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryData> editCategory(@PathVariable("categoryId") Long categoryId, @RequestBody CategoryForm form) {
        ReqMsgValidator.validateCategoryForm(form);
        CategoryData categoryData = adminCategoryFacade.updateCategory(categoryId, fillCategoryFormToData(form));
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(categoryData);
    }



    @PutMapping("/ordering")
    public ResponseEntity<List<Object>> bulkEditCategories(@RequestBody List<CategoryForm> forms) {
        List<CategoryData> categoryDataList = forms.stream().map(form -> {
            CategoryData categoryData = new CategoryData();
            categoryData.setId(form.getId());
            categoryData.setSortOrder(form.getSortOrder());
            return categoryData;
        }).collect(Collectors.toList());

        adminCategoryFacade.orderingCategories(categoryDataList);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(null);
    }


    @DeleteMapping("/{categoryId}")
    public ResponseEntity<Void> deleteCategory(@PathVariable("categoryId") Long categoryId) {
        adminCategoryFacade.deleteCategory(categoryId);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body(null);
    }


    private CategoryData fillCategoryFormToData(CategoryForm form)
    {
        CategoryData data = new CategoryData();
        data.setName(form.getName());
        data.setSortOrder(form.getSortOrder());
        data.setMetaTitle(form.getMetaTitle());
        data.setMetaDescription(form.getMetaDescription());
        data.setParentId(form.getParentId());
        return data;
    }
}
