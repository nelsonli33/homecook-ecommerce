package com.homecook.homecookentity.entity;

import com.homecook.homecookentity.constant.EntityConstant;
import com.homecook.homecookentity.type.VoucherUseStatus;

import javax.persistence.*;
import java.util.Date;

@Table(name = EntityConstant.Table.VoucherReceive)
@Entity
public class VoucherReceiveEntity extends AbstractBaseEntity
{
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private CustomerEntity customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "voucher_id")
    private VoucherEntity voucher;

    @Column(name = "use_status")
    private VoucherUseStatus useStatus;

    @Column(name = "receive_at")
    private Date receiveAt;

    public CustomerEntity getCustomer()
    {
        return customer;
    }

    public void setCustomer(CustomerEntity customer)
    {
        this.customer = customer;
    }

    public VoucherEntity getVoucher()
    {
        return voucher;
    }

    public void setVoucher(VoucherEntity voucher)
    {
        this.voucher = voucher;
    }

    public VoucherUseStatus getUseStatus()
    {
        return useStatus;
    }

    public void setUseStatus(VoucherUseStatus useStatus)
    {
        this.useStatus = useStatus;
    }

    public Date getReceiveAt()
    {
        return receiveAt;
    }

    public void setReceiveAt(Date receiveAt)
    {
        this.receiveAt = receiveAt;
    }
}