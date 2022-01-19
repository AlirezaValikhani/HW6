package model;

import java.util.ArrayList;
import java.util.List;

public class Customer extends Person{
    private Integer accountId;
    private Integer customerId;
    private List<Account> accounts;
    private List<Customer> customers;

    public Customer() {
        this.accounts = new ArrayList<Account>();
        this.customers = new ArrayList<Customer>();
    }

    public Customer(Integer accountId,Integer customerId,String firstName, String lastName, String nationalCode, String phoneNumber, String email, String address, Integer id, List<Account> accounts,List<Customer> customers) {
        super(firstName, lastName, nationalCode, phoneNumber, email, address, id);
        this.accountId = accountId;
        this.customerId = customerId;
        this.accounts = new ArrayList<Account>();
        this.customers = new ArrayList<Customer>();
    }

    public Customer(int id, String first_name, String last_name, String national_code, String phone_number, String email, String address, int account_id, int customer_id) {
    }


    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }
}
