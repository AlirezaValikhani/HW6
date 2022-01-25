package model;

import java.util.ArrayList;
import java.util.List;

public class Account {
    private Integer id;
    private Long balance;
    private Integer bankId;
    private Integer customerId;
    private Integer creditCardId;
    private Customer customer;
    private CreditCard creditCard;
    private BankBranch bankBranch;

    public Account(Integer id, Long balance) {
        this.id = id;
        this.balance = balance;
    }

    public Account(Integer id, Long balance , Integer bankId, Integer customerId, Integer creditCardId, Customer customer, CreditCard creditCard, BankBranch bankBranch) {
        this.id = id;
        this.balance = balance;
        this.bankId = bankId;
        this.customerId =customerId;
        this.creditCardId = creditCardId;
        this.customer = customer;
        this.creditCard = creditCard;
        this.bankBranch = bankBranch;
    }

    public Account(int id, long balance, String account_type, int customer_id, int credit_card_id) {
    }

    public Account(String firstName, String lastName, String nationalCode, String phoneNumber, String email, String address, Integer accountId) {
    }

    public Account(Long balance, Integer accountId) {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }

    public Integer getBankId() {
        return bankId;
    }

    public void setBankId(Integer bankId) {
        this.bankId = bankId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getCreditCardId() {
        return creditCardId;
    }

    public void setCreditCardId(Integer creditCardId) {
        this.creditCardId = creditCardId;
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
