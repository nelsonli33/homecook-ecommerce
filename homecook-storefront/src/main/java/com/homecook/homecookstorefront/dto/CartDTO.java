package com.homecook.homecookstorefront.dto;

import java.math.BigDecimal;
import java.util.List;

public class CartDTO
{
    private Long id;
    private String code;
    private CustomerDTO customer;
    private BigDecimal subtotal;
    private BigDecimal totalDiscounts;
    private BigDecimal totalPrice;
    private List<CartLineItemDTO> items;

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

    public CustomerDTO getCustomer()
    {
        return customer;
    }

    public void setCustomer(CustomerDTO customer)
    {
        this.customer = customer;
    }

    public BigDecimal getSubtotal()
    {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal)
    {
        this.subtotal = subtotal;
    }

    public BigDecimal getTotalDiscounts()
    {
        return totalDiscounts;
    }

    public void setTotalDiscounts(BigDecimal totalDiscounts)
    {
        this.totalDiscounts = totalDiscounts;
    }

    public BigDecimal getTotalPrice()
    {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice)
    {
        this.totalPrice = totalPrice;
    }

    public List<CartLineItemDTO> getItems()
    {
        return items;
    }

    public void setItems(List<CartLineItemDTO> items)
    {
        this.items = items;
    }
}
