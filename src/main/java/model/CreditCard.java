package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CreditCard {
    private Integer id;
    private String password;
    private Long balance;
    private String secondPassword;
    private String cardNumber;
    private String holderName;
    private String CVV2;
    private Date expirationDate;
    private Account account;
    private List<Transaction> transactions;

    /*public CreditCard() {
        this.transactions = new ArrayList<Transaction>();
    }*/

    public CreditCard(Integer id,String password,Long balance,String secondPassword,String cardNumber, String holderName,String CVV2, Date expirationDate, Account account, List<Transaction> transactions) {
        this.id =id;
        this.password =password;
        this.balance =balance;
        this.secondPassword = secondPassword;
        this.cardNumber = cardNumber;
        this.holderName = holderName;
        this.CVV2 = CVV2;
        this.expirationDate = expirationDate;
        this.account = account;
        this.transactions = new ArrayList<Transaction>();
    }

    public CreditCard(int id, long balance, String password, String second_password, String card_number, String holder_name, String cvv2, java.sql.Date expirationDate) {
        this.id =id;
        this.password =password;
        this.balance =balance;
        this.secondPassword = second_password;
        this.cardNumber = card_number;
        this.holderName = holder_name;
        this.CVV2 = cvv2;
        this.expirationDate = expirationDate;
        this.transactions = new ArrayList<Transaction>();
    }

    public CreditCard(String cardNumber) {
        this.cardNumber = cardNumber;
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }

    public String getSecondPassword() {
        return secondPassword;
    }

    public void setSecondPassword(String secondPassword) {
        this.secondPassword = secondPassword;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getHolderName() {
        return holderName;
    }

    public void setHolderName(String holderName) {
        this.holderName = holderName;
    }

    public String getCVV2() {
        return CVV2;
    }

    public void setCVV2(String CVV2) {
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
