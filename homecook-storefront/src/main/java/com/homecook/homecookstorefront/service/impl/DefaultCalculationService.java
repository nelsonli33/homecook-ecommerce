package com.homecook.homecookstorefront.service.impl;

import com.homecook.homecookentity.entity.CartEntity;
import com.homecook.homecookentity.entity.CartLineItemEntity;
import com.homecook.homecookentity.service.ModelService;
import com.homecook.homecookstorefront.service.CalculationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service(value = "calculationService")
public class DefaultCalculationService implements CalculationService
{
    private ModelService modelService;

    @Autowired
    public DefaultCalculationService(ModelService modelService)
    {
        this.modelService = modelService;
    }

    @Override
    public void calculate(CartEntity cart)
    {
        // -------------------------------
        // first calc all line items
        calculateLineItems(cart);
        // -------------------------------
        // now calculate all totals
        calculateTotals(cart);
    }

    private void calculateLineItems(CartEntity cart)
    {
        double subtotal = 0.0;
        for (CartLineItemEntity lineItem : cart.getLineItems())
        {
            calculateTotals(lineItem);
            subtotal += lineItem.getTotalPrice();
        }
        cart.setSubtotal(subtotal);
    }


    private void calculateTotals(CartEntity cart)
    {

        // subtotal
        final double subtotal = cart.getSubtotal();

        // discounts
//        final double totalDiscounts = calculateDiscountValues(order, recalculate);
        final double roundedTotalDiscounts = 0;
        cart.setTotalDiscounts(roundedTotalDiscounts);

        // set total
//        final double totalPrice = subtotal + cart.getDeliveryCost()
//                - roundedTotalDiscounts;
        final double totalPrice = subtotal - roundedTotalDiscounts;
        cart.setTotalPrice(totalPrice);

        modelService.save(cart);
    }

    private void calculateTotals(CartLineItemEntity lineItem)
    {

        final double totalPriceWithoutDiscount =
                BigDecimal.valueOf(lineItem.getPrice() * lineItem.getQuantity()).setScale(2, RoundingMode.HALF_UP).doubleValue();

        lineItem.setSubtotal(totalPriceWithoutDiscount);

        /*
         * apply discounts (will be rounded each) convert absolute discount values in case their currency doesn't match
         * the order currency
         */
        //YTODO : use CalculatinService methods to apply discounts
//        final List appliedDiscounts = DiscountValue.apply(quantity, totalPriceWithoutDiscount, digits,
//                convertDiscountValues(order, entry.getDiscountValues()), curr.getIsocode());
//        entry.setDiscountValues(appliedDiscounts);
//
        double totalPrice = totalPriceWithoutDiscount;
//        for (final Iterator it = appliedDiscounts.iterator(); it.hasNext();)
//        {
//            totalPrice -= ((DiscountValue) it.next()).getAppliedValue();
//        }

        // set total discounts
        lineItem.setTotalDiscounts(0.0);

        // set total price
        lineItem.setTotalPrice(totalPrice);

        modelService.save(lineItem);
    }


}
