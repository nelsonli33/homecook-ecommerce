package com.homecook.homecookentity.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("2")
public class ShippingAddressEntity extends AddressEntity
{
}
