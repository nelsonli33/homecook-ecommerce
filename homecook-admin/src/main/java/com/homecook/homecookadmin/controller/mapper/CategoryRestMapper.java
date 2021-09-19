package com.homecook.homecookadmin.controller.mapper;

import com.homecook.homecookadmin.dto.CategoryDTO;
import com.homecook.homecookadmin.model.Category;
import com.homecook.homecookadmin.model.CreateCategoryRequest;
import com.homecook.homecookadmin.model.UpdateCategoryRequest;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = "spring"
)
public interface CategoryRestMapper
{
    CategoryDTO convertRequestToDTO(CreateCategoryRequest createCategoryRequest);

    CategoryDTO convertRequestToDTO(UpdateCategoryRequest updateCategoryRequest);

    Category convertDTOtoResponse(CategoryDTO categoryDTO);

    List<Category> convertAllDTOtoResponse(List<CategoryDTO> categoryDTO);
}
