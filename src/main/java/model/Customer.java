package model;

import java.util.ArrayList;
import java.util.List;

public class Customer extends Person{
    private BankBranch bankBranch;
    private List<Account> accounts;
    private List<CreditCard> creditCards;

    public Customer(BankBranch bankBranch) {
        this.bankBranch =bankBranch;
        this.accounts = new ArrayList<Account>();
    }

    public Customer(int id, String first_name, String last_name, String national_code, String phone_number, String user_name, String password,BankBranch bankBranch,List<CreditCard> creditCards) {
        super(id,first_name,last_name,user_name,password,national_code,phone_number);
        this.bankBranch =bankBranch;
        this.accounts = new ArrayList<Account>();
        this.creditCards = new ArrayList<CreditCard>();
    }

    public Customer(Integer id, String firstName, String lastName, String userName, String password, String nationalCode, String phoneNumber, BankBranch bankBranch, List<Account> accounts, List<CreditCard> creditCards) {
        super(id, firstName, lastName, userName, password, nationalCode, phoneNumber);
        this.bankBranch = bankBranch;
        this.accounts = accounts;
        this.creditCards = creditCards;
    }

    public Customer(Integer id, String firstName, String lastName, String userName, String password, String nationalCode, String phoneNumber, BankBranch bankBranch) {
        super(id, firstName, lastName, userName, password, nationalCode, phoneNumber);
        this.bankBranch = bankBranch;
    }

    public Customer(Integer id) {
        super(id);
    }

    public BankBranch getBankBranch() {
        return bankBranch;
    }

    public void setBankBranch(BankBranch bankBranch) {
        this.bankBranch = bankBranch;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }
}
