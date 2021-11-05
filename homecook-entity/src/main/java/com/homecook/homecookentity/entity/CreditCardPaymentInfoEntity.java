package com.homecook.homecookentity.entity;

import com.homecook.homecookentity.constant.EntityConstant;
import com.homecook.homecookentity.type.CreditCardType;

import javax.persistence.*;

@Table(name = EntityConstant.Table.CreditCardPaymentInfo)
@Entity
public class CreditCardPaymentInfoEntity extends AbstractBaseEntity
{
    @Column(name = "card_bin")
    private String cardBin;

    @Column(name = "card_last_four")
    private String cardLastFour;

    @Column(name = "card_type")
    private CreditCardType cardType;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "transaction_id")
    private PaymentTransactionEntity transaction;


    public String getCardBin()
    {
        return cardBin;
    }

    public void setCardBin(String cardBin)
    {
        this.cardBin = cardBin;
    }

    public String getCardLastFour()
    {
        return cardLastFour;
    }

    public void setCardLastFour(String cardLastFour)
    {
        this.cardLastFour = cardLastFour;
    }

    public CreditCardType getCardType()
    {
        return cardType;
    }

    public void setCardType(CreditCardType cardType)
    {
        this.cardType = cardType;
    }


    public PaymentTransactionEntity getTransaction()
    {
        return transaction;
    }

    public void setTransaction(PaymentTransactionEntity transaction)
    {
        this.transaction = transaction;
    }
}