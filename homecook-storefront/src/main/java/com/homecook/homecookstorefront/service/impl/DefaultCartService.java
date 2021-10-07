package com.homecook.homecookstorefront.service.impl;

import com.homecook.homecookentity.entity.CartEntity;
import com.homecook.homecookentity.entity.CartLineItemEntity;
import com.homecook.homecookentity.entity.ProductEntity;
import com.homecook.homecookentity.entity.ProductVariantEntity;
import com.homecook.homecookentity.repository.CartRepository;
import com.homecook.homecookentity.service.ModelService;
import com.homecook.homecookstorefront.dto.SKUProduct;
import com.homecook.homecookstorefront.error.InternalErrorCode;
import com.homecook.homecookstorefront.exception.StorefrontServerRuntimeException;
import com.homecook.homecookstorefront.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.homecook.homecookcommon.util.ServicesUtil.validateParameterNotNullStandardMessage;

@Service(value = "cartService")
public class DefaultCartService implements CartService
{
    private CartRepository cartRepository;
    private CartFactory cartFactory;
    private CustomerService customerService;
    private SKUProductFactory skuProductFactory;
    private ProductService productService;
    private ModelService modelService;
    private CalculationService calculationService;

    @Autowired
    public DefaultCartService(CartRepository cartRepository, CartFactory cartFactory, CustomerService customerService, SKUProductFactory skuProductFactory, ProductService productService, ModelService modelService, CalculationService calculationService)
    {
        this.cartRepository = cartRepository;
        this.cartFactory = cartFactory;
        this.customerService = customerService;
        this.skuProductFactory = skuProductFactory;
        this.productService = productService;
        this.modelService = modelService;
        this.calculationService = calculationService;
    }

    @Override
    public CartEntity getCartForCurrentCustomer()
    {
        final CartEntity cart = cartRepository.findCartEntityByCustomer(customerService.getCurrentCustomer());
        if (cart != null)
        {
            return refreshCart(cart);
        }
        else
        {
            return cartFactory.createCart();
        }
    }

    private CartEntity refreshCart(CartEntity cart)
    {
        validateParameterNotNullStandardMessage("cart", cart);

        boolean updated = false;

        for (CartLineItemEntity lineItem : cart.getLineItems())
        {
            updated = updateCartLineItem(lineItem);
        }

        if (updated)
        {
            modelService.refresh(cart);
            calculationService.calculate(cart);
        }

        return cart;
    }

    @Override
    public CartLineItemEntity addLineItem(CartEntity cart, SKUProduct skuProduct, int qty)
    {
        validateParameterNotNullStandardMessage("cart", cart);
        validateParameterNotNullStandardMessage("skuProduct", skuProduct);

        if (qty <= 0)
        {
            throw new StorefrontServerRuntimeException(InternalErrorCode.INTERNAL_SERVER_ERROR, "Quantity must be a positive non-zero value");
        }

        CartLineItemEntity lineItem = getCartLineItemForSKUProduct(cart, skuProduct);

        if (lineItem != null && lineItem.getItemKey().equals(skuProduct.getKey()))
        {
            lineItem.setQuantity(lineItem.getQuantity() + qty);
            return lineItem;
        }

        if (lineItem != null && !lineItem.getItemKey().equals(skuProduct.getKey()))
        {
            updateCartLineItemInfoForSKUProduct(lineItem, skuProduct);
            lineItem.setQuantity(lineItem.getQuantity() + qty);
            return lineItem;
        }

        lineItem = new CartLineItemEntity();
        lineItem.setName(skuProduct.getName());
        lineItem.setQuantity(qty);
        lineItem.setPrice(skuProduct.getPrice());
        lineItem.setSku(skuProduct.getSku());
        lineItem.setProduct(skuProduct.getProduct());
        lineItem.setVariant(skuProduct.getVariant());
        lineItem.setItemKey(skuProduct.getKey());
        lineItem.setCart(cart);
        cart.getLineItems().add(lineItem);
        return lineItem;
    }

    @Override
    public CartLineItemEntity getCartLineItemForSKUProduct(CartEntity cart, SKUProduct skuProduct)
    {
        validateParameterNotNullStandardMessage("cart", cart);
        validateParameterNotNullStandardMessage("skuProduct", skuProduct);

        final List<CartLineItemEntity> lineItems = cart.getLineItems();
        if (lineItems == null || lineItems.isEmpty() || skuProduct.getProduct() == null)
        {
            return null;
        }

        for (final CartLineItemEntity lineItem : lineItems)
        {
            if (skuProduct.getHasVariant() && lineItem.getProduct().equals(skuProduct.getProduct()) && lineItem.getVariant().equals(skuProduct.getVariant()))
            {
                return lineItem;
            }

            if (!skuProduct.getHasVariant() && lineItem.getProduct().equals(skuProduct.getProduct()))
            {
                return lineItem;
            }
        }
        return null;
    }

    private boolean updateCartLineItem(CartLineItemEntity lineItem)
    {
        final ProductEntity product = productService.getProductForId(lineItem.getProductId());
        ProductVariantEntity variant = null;
        if (lineItem.getVariantId() != null)
        {
            variant = productService.getVariantForProduct(product, lineItem.getVariantId());
        }
        SKUProduct skuProduct = skuProductFactory.createSKUProduct(product, variant);
        return updateCartLineItemInfoForSKUProduct(lineItem, skuProduct);
    }

    private boolean updateCartLineItemInfoForSKUProduct(CartLineItemEntity lineItem, SKUProduct skuProduct)
    {
        validateParameterNotNullStandardMessage("lineItem", lineItem);
        validateParameterNotNullStandardMessage("skuProduct", skuProduct);

        boolean updated = false;

        if (!lineItem.getItemKey().equals(skuProduct.getKey()))
        {
            lineItem.setName(skuProduct.getName());
            lineItem.setPrice(skuProduct.getPrice());
            lineItem.setSku(skuProduct.getSku());
            lineItem.setItemKey(skuProduct.getKey());
            lineItem.setProduct(skuProduct.getProduct());
            lineItem.setVariant(skuProduct.getVariant());
            updated = true;
            modelService.save(lineItem);
        }

        return updated;
    }
}
