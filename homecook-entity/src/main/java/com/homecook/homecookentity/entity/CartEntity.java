package com.homecook.homecookentity.entity;

import com.homecook.homecookentity.constant.EntityConstant;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Table(name = EntityConstant.Table.Cart, indexes = {
        @Index(name = "idx_cartentity_code", columnList = "code")
})
@Entity
public class CartEntity extends AbstractBaseEntity
{
    @Column(name = "code", unique = true)
    private String code;

    @Column(name = "subtotal")
    private Double subtotal;

    @Column(name = "total_price")
    private Double totalPrice;

    @Column(name = "total_discounts")
    private Double totalDiscounts;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private CustomerEntity customer;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartLineItemEntity> lineItems = new ArrayList<>();


    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

    public Double getSubtotal()
    {
        return subtotal;
    }

    public void setSubtotal(Double subtotal)
    {
        this.subtotal = subtotal;
    }

    public Double getTotalPrice()
    {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice)
    {
        this.totalPrice = totalPrice;
    }

    public Double getTotalDiscounts()
    {
        return totalDiscounts;
    }

    public void setTotalDiscounts(Double totalDiscounts)
    {
        this.totalDiscounts = totalDiscounts;
    }

    public CustomerEntity getCustomer()
    {
        return customer;
    }

    public void setCustomer(CustomerEntity customer)
    {
        this.customer = customer;
    }

    public List<CartLineItemEntity> getLineItems()
    {
        return lineItems;
    }

    public void setLineItems(List<CartLineItemEntity> lineItems)
    {
        this.lineItems = lineItems;
    }
}
