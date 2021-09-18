package com.homecook.homecookentity.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("0")
public class ProductSpecAttributeEntity extends ProductAttributeEntity
{
}
