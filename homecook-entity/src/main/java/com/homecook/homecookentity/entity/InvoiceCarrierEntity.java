package com.homecook.homecookentity.entity;

import com.homecook.homecookentity.constant.EntityConstant;
import com.homecook.homecookentity.type.CarrierType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name = EntityConstant.Table.InvoiceCarrier)
@Entity
public class InvoiceCarrierEntity extends AbstractBaseEntity
{
    @Column(name = "carrier_type")
    private CarrierType carrierType;

    @Column(name = "barcode")
    private String barcode;

    public CarrierType getCarrierType()
    {
        return carrierType;
    }

    public void setCarrierType(CarrierType carrierType)
    {
        this.carrierType = carrierType;
    }

    public String getBarcode()
    {
        return barcode;
    }

    public void setBarcode(String barcode)
    {
        this.barcode = barcode;
    }
}
