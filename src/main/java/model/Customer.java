package model;

import java.util.ArrayList;
import java.util.List;

public class Customer extends Person{
    private Integer bankId;
    private List<Account> accounts;

    public Customer() {
        this.accounts = new ArrayList<Account>();
    }

    public Customer(String firstName, String lastName, String nationalCode, String phoneNumber, String email, String address, Integer id,Integer bankId, List<Account> accounts) {
        super(firstName, lastName, nationalCode, phoneNumber, email, address, id);
        this.bankId =bankId;
        this.accounts = new ArrayList<Account>();
    }

    public Integer getBankId() {
        return bankId;
    }

    public void setBankId(Integer bankId) {
        this.bankId = bankId;
    }

    public Customer(String firstName, String lastName, String nationalCode, String phoneNumber, String email, String address, Integer accountId) {
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }
}
