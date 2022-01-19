package model;

import java.util.ArrayList;

public class BankBranch {
    private Integer id;
    private String name;
    private Integer accountId;
    private Integer customerId;
    private Integer employeeId;
    private ArrayList<Employee> employees;
    private ArrayList<Customer> customers;
    private ArrayList<Account> accounts;

    public BankBranch(Integer id,Integer accountId,Integer customerId,Integer employeeId, String name, ArrayList<Employee> employees, ArrayList<Account> accounts) {
        this.id = id;
        this.accountId = accountId;
        this.customerId = customerId;
        this.employeeId = employeeId;
        this.name = name;
        this.employees = new ArrayList<>();
        this.accounts = new ArrayList<>();
    }

    public BankBranch(int id, String name, int account_id, int customer_id, int employee_id) {
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

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public ArrayList<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(ArrayList<Customer> customers) {
        this.customers = customers;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(ArrayList<Employee> employees) {
        this.employees = employees;
    }

    public ArrayList<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(ArrayList<Account> accounts) {
        this.accounts = accounts;
    }
}
