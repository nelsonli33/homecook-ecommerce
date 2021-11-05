package com.homecook.homecookentity.constant;

public interface EntityConstant
{
    interface Table {
        String _Prefix = "comm_";

        String Category = _Prefix + "category";

        String Product = _Prefix + "product";
        String ProductVariant = _Prefix + "product_variant";
        String ProductImage = _Prefix + "product_image";
        String ProductAttributeCategory = _Prefix + "product_attribute_category";
        String ProductAttribute = _Prefix + "product_attribute";
        String ProductAttributeValue = _Prefix + "product_attribute_value";
        String Product2Category = _Prefix + "product_category_rel";
        String Product2ShippingMode = _Prefix + "product_shippingmode_rel";

        String Customer = _Prefix + "customer";
        String InvoiceSetting = _Prefix + "invoice_setting";
        String InvoiceCarrier = _Prefix + "invoice_carrier";

        String Country = _Prefix + "country";
        String Address = _Prefix + "address";
        String ShippingMode = _Prefix + "shipping_mode";
        String PaymentMode = _Prefix + "payment_mode";

        String Cart = _Prefix + "cart";
        String CartLineItem = _Prefix + "cart_line_item";

        String Checkout = _Prefix + "checkout";
        String CheckoutInvoice = _Prefix + "checkout_invoice";

        String Order = _Prefix + "order";
        String OrderLineItem = _Prefix + "order_line_item";
        String OrderInvoice = _Prefix + "order_invoice";
        String PaymentTransaction = _Prefix + "payment_transaction";

        String CreditCardPaymentInfo = _Prefix + "credit_card_payment_info";
        String Bank = _Prefix + "bank";

    }
}
