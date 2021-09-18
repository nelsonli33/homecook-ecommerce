package com.homecook.homecookentity.entity;


import com.homecook.homecookentity.type.ProductAttrInputType;
import com.homecook.homecookentity.type.ProductAttrManualAddType;
import com.homecook.homecookentity.type.ProductAttrSelectType;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("1")
public class ProductParamAttributeEntity extends ProductAttributeEntity
{
    // 屬性選取方式：0 單選; 1多選'
    private ProductAttrSelectType selectType;
    // 屬性輸入方式：0 手動輸入; 1 從列表中選取
    private ProductAttrInputType inputType;
    // 可選值列表，使用逗號隔開
    private String inputList;
    // 是否支援手動新增：0 不支援; 1 支援
    private ProductAttrManualAddType manualAddType;

    public ProductAttrSelectType getSelectType()
    {
        return selectType;
    }

    public void setSelectType(ProductAttrSelectType selectType)
    {
        this.selectType = selectType;
    }

    public ProductAttrInputType getInputType()
    {
        return inputType;
    }

    public void setInputType(ProductAttrInputType inputType)
    {
        this.inputType = inputType;
    }

    public String getInputList()
    {
        return inputList;
    }

    public void setInputList(String inputList)
    {
        this.inputList = inputList;
    }

    public ProductAttrManualAddType getManualAddType()
    {
        return manualAddType;
    }

    public void setManualAddType(ProductAttrManualAddType manualAddType)
    {
        this.manualAddType = manualAddType;
    }
}
