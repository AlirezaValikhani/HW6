package model;

public class Employee extends Person{
    private BankBranch bankBranch;

    public Employee(Integer id, String firstName, String lastName, String userName, String password, String nationalCode, String phoneNumber,BankBranch bankBranch) {
        super(id, firstName, lastName, userName, password, nationalCode, phoneNumber);
        this.bankBranch = bankBranch;
    }

    public BankBranch getBankBranch() {
        return bankBranch;
    }

    public void setBankBranch(BankBranch bankBranch) {
        this.bankBranch = bankBranch;
    }
}
