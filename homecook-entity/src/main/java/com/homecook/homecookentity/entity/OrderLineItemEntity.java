package com.homecook.homecookentity.entity;

import com.homecook.homecookentity.constant.EntityConstant;

import javax.persistence.*;

@Table(name = EntityConstant.Table.OrderLineItem)
@Entity
public class OrderLineItemEntity extends AbstractBaseEntity
{
    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private Double price;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "sku")
    private String sku;

    @Column(name = "item_key")
    private String itemKey;

    @Column(name = "product_id", insertable = false, updatable = false)
    private Long productId;

    @Column(name = "variant_id", insertable = false, updatable = false)
    private Long variantId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private ProductEntity product;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "variant_id")
    private ProductVariantEntity variant;

    @Column(name = "subtotal")
    private Double subtotal;

    @Column(name = "totalDiscounts")
    private Double totalDiscounts;

    @Column(name = "totalPrice")
    private Double totalPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private OrderEntity order;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Double getPrice()
    {
        return price;
    }

    public void setPrice(Double price)
    {
        this.price = price;
    }

    public Integer getQuantity()
    {
        return quantity;
    }

    public void setQuantity(Integer quantity)
    {
        this.quantity = quantity;
    }

    public String getSku()
    {
        return sku;
    }

    public void setSku(String sku)
    {
        this.sku = sku;
    }

    public String getItemKey()
    {
        return itemKey;
    }

    public void setItemKey(String itemKey)
    {
        this.itemKey = itemKey;
    }

    public Long getProductId()
    {
        return productId;
    }

    public Long getVariantId()
    {
        return variantId;
    }

    public ProductEntity getProduct()
    {
        return product;
    }

    public void setProduct(ProductEntity product)
    {
        this.product = product;
    }

    public ProductVariantEntity getVariant()
    {
        return variant;
    }

    public void setVariant(ProductVariantEntity variant)
    {
        this.variant = variant;
    }

    public Double getSubtotal()
    {
        return subtotal;
    }

    public void setSubtotal(Double subtotal)
    {
        this.subtotal = subtotal;
    }

    public Double getTotalDiscounts()
    {
        return totalDiscounts;
    }

    public void setTotalDiscounts(Double totalDiscounts)
    {
        this.totalDiscounts = totalDiscounts;
    }

    public Double getTotalPrice()
    {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice)
    {
        this.totalPrice = totalPrice;
    }

    public OrderEntity getOrder()
    {
        return order;
    }

    public void setOrder(OrderEntity order)
    {
        this.order = order;
    }
}
