package model;

import java.util.ArrayList;
import java.util.List;

public class Account {
    private Integer id;
    private Double balance;
    private Customer customer;
    private CreditCard creditCard;
    private BankBranch bankBranch;

    public Account(Double balance) {
        this.balance = balance;
    }

    public Account(Integer id, Double balance) {
        this.id = id;
        this.balance = balance;
    }

    public Account(Double balance, Customer customer) {
        this.balance = balance;
        this.customer = customer;
    }

    public Account(Integer id, Double balance , Customer customer, CreditCard creditCard, BankBranch bankBranch) {
        this.id = id;
        this.balance = balance;
        this.customer = customer;
        this.creditCard = creditCard;
        this.bankBranch = bankBranch;
    }

    public Account(Double balance, Customer customer,CreditCard creditCard) {
        this.balance = balance;
        this.customer = customer;
        this.creditCard = creditCard;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public CreditCard getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(CreditCard creditCard) {
        this.creditCard = creditCard;
    }

    public BankBranch getBankBranch() {
        return bankBranch;
    }

    public void setBankBranch(BankBranch bankBranch) {
        this.bankBranch = bankBranch;
    }
}
