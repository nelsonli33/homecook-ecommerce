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

        String Customer = _Prefix + "customer";

        String Cart = _Prefix + "cart";
        String CartLineItem = _Prefix + "cart_line_item";
    }
}
