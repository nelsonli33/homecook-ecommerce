package com.homecook.homecookstorefront.error;

import com.homecook.homecookstorefront.error.type.CartLineItemInvalidType;

public class CartLineItemError
{
    private Long lineItemId;
    private Long productId;
    private Long variantId;
    private CartLineItemInvalidType invalidType;
    private String errorMessage;

    public Long getLineItemId()
    {
        return lineItemId;
    }

    public void setLineItemId(Long lineItemId)
    {
        this.lineItemId = lineItemId;
    }

    public Long getProductId()
    {
        return productId;
    }

    public void setProductId(Long productId)
    {
        this.productId = productId;
    }

    public Long getVariantId()
    {
        return variantId;
    }

    public void setVariantId(Long variantId)
    {
        this.variantId = variantId;
    }

    public CartLineItemInvalidType getInvalidType()
    {
        return invalidType;
    }

    public void setInvalidType(CartLineItemInvalidType invalidType)
    {
        this.invalidType = invalidType;
    }

    public String getErrorMessage()
    {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage)
    {
        this.errorMessage = errorMessage;
    }
}
