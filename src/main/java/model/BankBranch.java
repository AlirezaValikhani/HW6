package model;

import java.util.ArrayList;

public class BankBranch {
    private Integer id;
    private String bankName;
    private ArrayList<Employee> employees;
    private ArrayList<Customer> customers;
    private ArrayList<Account> accounts;

    public BankBranch(Integer id, String bankName, ArrayList<Employee> employees, ArrayList<Customer> customers, ArrayList<Account> accounts) {
        this.id = id;
        this.bankName = bankName;
        this.employees = employees;
        this.customers = customers;
        this.accounts = accounts;
    }

    public BankBranch(int id, String bankName) {
        this.id = id;
        this.bankName = bankName;
    }

    public BankBranch(int id) {
        this.id = id;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public ArrayList<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(ArrayList<Employee> employees) {
        this.employees = employees;
    }

    public ArrayList<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(ArrayList<Customer> customers) {
        this.customers = customers;
    }

    public ArrayList<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(ArrayList<Account> accounts) {
        this.accounts = accounts;
    }
}
