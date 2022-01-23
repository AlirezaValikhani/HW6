package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CreditCard {
    private Integer id;
    private Long password;
    private Long balance;
    private Long secondPassword;
    private Long cardNumber;
    private String holderName;
    private Integer CVV2;
    private Date expirationDate;
    private Integer accountId;
    private Integer transactionId;
    private Account account;
    private List<Transaction> transactions;

    public CreditCard() {
        this.transactions = new ArrayList<Transaction>();
    }

    public CreditCard(Integer id,Long password,Long balance,Long secondPassword,Long cardNumber, String holderName,Integer CVV2, Date expirationDate,Integer accountId,Integer transactionId, Account account, List<Transaction> transactions) {
        this.id =id;
        this.password =password;
        this.balance =balance;
        this.secondPassword = secondPassword;
        this.cardNumber = cardNumber;
        this.holderName = holderName;
        this.CVV2 = CVV2;
        this.expirationDate = expirationDate;
        this.accountId = accountId;
        this.transactionId = transactionId;
        this.account = account;
        this.transactions = new ArrayList<Transaction>();
    }

    public CreditCard(int i, int i1, int i2, int i3, long l, String alirezaValikhani, int i4, int i5) {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getPassword() {
        return password;
    }

    public void setPassword(Long password) {
        this.password = password;
    }

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }

    public Long getSecondPassword() {
        return secondPassword;
    }

    public void setSecondPassword(Long secondPassword) {
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

    public Long getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(Long cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getHolderName() {
        return holderName;
    }

    public void setHolderName(String holderName) {
        this.holderName = holderName;
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
