package model;

import java.util.ArrayList;
import java.util.List;

public class Manager extends Person{
    private BankBranch bankBranch;
    private List<Employee> employees;

    public Manager(Integer id, String firstName, String lastName, String userName, String password, String nationalCode, String phoneNumber, BankBranch bankBranch, List<Employee> employees) {
        super(id, firstName, lastName, userName, password, nationalCode, phoneNumber);
        this.bankBranch = bankBranch;
        this.employees = new ArrayList<>();
    }

    public BankBranch getBankBranch() {
        return bankBranch;
    }

    public void setBankBranch(BankBranch bankBranch) {
        this.bankBranch = bankBranch;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}
