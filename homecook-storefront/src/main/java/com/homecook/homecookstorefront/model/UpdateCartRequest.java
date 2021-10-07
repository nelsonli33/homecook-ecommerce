package com.homecook.homecookstorefront.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UpdateCartRequest
{
    @JsonProperty("productId")
    private Long productId;

    @JsonProperty("variantId")
    private Long variantId;

    @JsonProperty("qty")
    private int qty;

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

    public int getQty()
    {
        return qty;
    }

    public void setQty(int qty)
    {
        this.qty = qty;
    }
}
