package model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CreditCard {
    private Integer id;
    private Integer password;
    private Long secondPassword;
    private Long cardNumber;
    private String holderName;
    private Long CVV2;
    private Timestamp expirationDate;
    private Account account;
    private Customer customer;
    private List<Transaction> transactions;

    public CreditCard() {
        this.transactions = new ArrayList<Transaction>();
    }

    public CreditCard(Integer id,Integer password,Long secondPassword,Long cardNumber, String holderName,Long CVV2, Timestamp expirationDate, Account account,Customer customer) {
        this.id =id;
        this.password =password;
        this.secondPassword = secondPassword;
        this.cardNumber = cardNumber;
        this.holderName = holderName;
        this.CVV2 = CVV2;
        this.expirationDate = expirationDate;
        this.account = account;
        this.customer = customer;
    }

    public CreditCard(int id, Integer password, Long second_password, Long card_number, String holder_name, Long cvv2, Timestamp expirationDate) {
        this.id =id;
        this.password =password;
        this.secondPassword = second_password;
        this.cardNumber = card_number;
        this.holderName = holder_name;
        this.CVV2 = cvv2;
        this.expirationDate = expirationDate;
        this.transactions = new ArrayList<Transaction>();
    }

    public CreditCard(Long cardNumber) {
        this.cardNumber = cardNumber;
    }

    public CreditCard(int id, int password, long second_password, long card_number, long cvv2, Timestamp expirationDate) {
        this.id =id;
        this.password =password;
        this.secondPassword = second_password;
        this.cardNumber = card_number;
        this.CVV2 = cvv2;
        this.expirationDate = expirationDate;
    }

    public CreditCard(int id, int password, long second_password, long card_number, long cvv2, Timestamp expirationDate, Account account) {
        this.id =id;
        this.password =password;
        this.secondPassword = second_password;
        this.cardNumber = card_number;
        this.CVV2 = cvv2;
        this.expirationDate = expirationDate;
        this.account =account;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPassword() {
        return password;
    }

    public void setPassword(Integer password) {
        this.password = password;
    }

    public Long getSecondPassword() {
        return secondPassword;
    }

    public void setSecondPassword(Long secondPassword) {
        this.secondPassword = secondPassword;
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

    public Long getCVV2() {
        return CVV2;
    }

    public void setCVV2(Long CVV2) {
        this.CVV2 = CVV2;
    }

    public Timestamp getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Timestamp expirationDate) {
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

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
