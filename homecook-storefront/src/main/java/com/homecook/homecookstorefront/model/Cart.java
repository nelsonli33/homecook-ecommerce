package com.homecook.homecookstorefront.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Cart
{
    @JsonProperty("code")
    private String code;

    @JsonProperty("customer")
    private Customer customer;

    @JsonProperty("subtotal")
    private BigDecimal subtotal;

    @JsonProperty("totalDiscounts")
    private BigDecimal totalDiscounts;

    @JsonProperty("totalPrice")
    private BigDecimal totalPrice;

    @JsonProperty("items")
    private List<CartLineItem> items;

    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

    public Customer getCustomer()
    {
        return customer;
    }

    public void setCustomer(Customer customer)
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

    public List<CartLineItem> getItems()
    {
        return items;
    }

    public void setItems(List<CartLineItem> items)
    {
        this.items = items;
    }
}
