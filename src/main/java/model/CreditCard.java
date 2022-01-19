package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CreditCard {
    private Integer id;
    private Integer secondPassword;
    private Integer cardNumber;
    private String holderName;
    private Integer accountBalance;
    private Integer CVV2;
    private Date expirationDate;
    private Integer accountId;
    private Integer transactionId;
    private Account account;
    private List<Transaction> transactions;

    public CreditCard() {
        this.transactions = new ArrayList<Transaction>();
    }

    public CreditCard(Integer id,Integer secondPassword,Integer cardNumber, String holderName, Integer accountBalance,Integer CVV2, Date expirationDate,Integer accountId,Integer transactionId, Account account, List<Transaction> transactions) {
        this.id =id;
        this.secondPassword = secondPassword;
        this.cardNumber = cardNumber;
        this.holderName = holderName;
        this.accountBalance = accountBalance;
        this.CVV2 = CVV2;
        this.expirationDate = expirationDate;
        this.accountId = accountId;
        this.transactionId = transactionId;
        this.account = account;
        this.transactions = new ArrayList<Transaction>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSecondPassword() {
        return secondPassword;
    }

    public void setSecondPassword(Integer secondPassword) {
        this.secondPassword = secondPassword;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public Integer getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }

    public Integer getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(Integer cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getHolderName() {
        return holderName;
    }

    public void setHolderName(String holderName) {
        this.holderName = holderName;
    }

    public Integer getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(Integer accountBalance) {
        this.accountBalance = accountBalance;
    }

    public Integer getCVV2() {
        return CVV2;
    }

    public void setCVV2(Integer CVV2) {
        this.CVV2 = CVV2;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }
}
