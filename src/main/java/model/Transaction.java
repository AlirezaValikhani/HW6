package model;

import java.sql.Timestamp;

public class Transaction {
    private Integer id;
    private Timestamp dateTime;
    private Account sourceAccount;
    private Account destinationAccount;
    private TransactionStatus transactionStatus;
    private TransactionType transactionType;
    private CreditCard creditCard;

    public Transaction(Integer id,Timestamp dateTime, Account sourceAccount, Account destinationAccount, TransactionStatus transactionStatus, TransactionType transactionType, CreditCard creditCard) {
        this.id = id;
        this.dateTime = dateTime;
        this.sourceAccount = sourceAccount;
        this.destinationAccount = destinationAccount;
        this.transactionStatus = transactionStatus;
        this.transactionType = transactionType;
        this.creditCard = creditCard;
    }

    public Account getSourceAccount() {
        return sourceAccount;
    }

    public void setSourceAccount(Account sourceAccount) {
        this.sourceAccount = sourceAccount;
    }

    public Account getDestinationAccount() {
        return destinationAccount;
    }

    public void setDestinationAccount(Account destinationAccount) {
        this.destinationAccount = destinationAccount;
    }

    public Timestamp getDateTime() {
        return dateTime;
    }

    public void setDateTime(Timestamp dateTime) {
        this.dateTime = dateTime;
    }

    public TransactionStatus getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(TransactionStatus transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public CreditCard getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(CreditCard creditCard) {
        this.creditCard = creditCard;
    }
}
