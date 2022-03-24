package com.csproject.homealoneservice.entity;

import javax.persistence.*;

@Entity
@Table(name = "bank_account", schema = "comsci_homealone", catalog = "")
public class BankAccountEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "bid")
    private int bid;
    @Basic
    @Column(name = "mid")
    private int mid;
    @Basic
    @Column(name = "bank_name")
    private String bankName;
    @Basic
    @Column(name = "account_name")
    private String accountName;
    @Basic
    @Column(name = "account_number")
    private Integer accountNumber;

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public Integer getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Integer accountNumber) {
        this.accountNumber = accountNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BankAccountEntity that = (BankAccountEntity) o;

        if (bid != that.bid) return false;
        if (mid != that.mid) return false;
        if (bankName != null ? !bankName.equals(that.bankName) : that.bankName != null) return false;
        if (accountName != null ? !accountName.equals(that.accountName) : that.accountName != null) return false;
        if (accountNumber != null ? !accountNumber.equals(that.accountNumber) : that.accountNumber != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = bid;
        result = 31 * result + mid;
        result = 31 * result + (bankName != null ? bankName.hashCode() : 0);
        result = 31 * result + (accountName != null ? accountName.hashCode() : 0);
        result = 31 * result + (accountNumber != null ? accountNumber.hashCode() : 0);
        return result;
    }
}
