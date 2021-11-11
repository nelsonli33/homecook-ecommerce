package com.homecook.homecookentity.entity;

import com.homecook.homecookentity.constant.EntityConstant;

import javax.persistence.*;
import java.util.Date;

@Table(name = EntityConstant.Table.VoucherBuyRecord)
@Entity
public class VoucherBuyRecordEntity extends AbstractBaseEntity
{
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "voucher_id")
    private VoucherEntity voucher;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private CustomerEntity customer;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private OrderEntity order;

    @Column(name = "use_at")
    private Date useAt;

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

    public OrderEntity getOrder()
    {
        return order;
    }

    public void setOrder(OrderEntity order)
    {
        this.order = order;
    }
}
