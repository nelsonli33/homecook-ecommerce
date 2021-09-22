package com.homecook.homecookadmin.facade.mapper;

import com.homecook.homecookadmin.dto.CategoryDTO;
import com.homecook.homecookadmin.service.AdminCategoryService;
import com.homecook.homecookentity.entity.CategoryEntity;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public abstract class AdminCategoryMapper
{
    private AdminCategoryService adminCategoryService;

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(source = "parentId", target = "parent", qualifiedByName = "addParentCategory")
    })
    public abstract CategoryEntity convertToEntity(CategoryDTO categoryDTO);


    @InheritConfiguration(name = "convertToEntity")
    public abstract void updateCategoryEntityFromDTO(CategoryDTO categoryDTO, @MappingTarget CategoryEntity categoryEntity);


    public abstract CategoryDTO convertToDTO(CategoryEntity categoryEntity);

    public abstract List<CategoryDTO> convertAllToDTO(List<CategoryEntity> categoryEntities);

    @Named("addParentCategory")
    public CategoryEntity addParentCategory(Long parentId) {
        if (parentId != null)
        {
            CategoryEntity parentCategory = adminCategoryService.getCategoryForId(parentId);
            parentCategory.getChildren().add(parentCategory);
            return parentCategory;
        }
        return null;
    }

    public AdminCategoryService getAdminCategoryService()
    {
        return adminCategoryService;
    }

    @Autowired
    public void setAdminCategoryService(AdminCategoryService adminCategoryService)
    {
        this.adminCategoryService = adminCategoryService;
    }
}
