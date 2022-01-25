package model;

public class Employee extends Person{
    private Integer bankId;


    public Employee() {
    }

    public Employee(String firstName, String lastName, String nationalCode, String phoneNumber, String email, String address, Integer id, Integer bankId) {
        super(firstName, lastName, nationalCode, phoneNumber, email, address, id);
        this.bankId = bankId;
    }

    public Employee(String firstName, String lastName, String nationalCode, String phoneNumber, String email, String address, Integer employeeId) {
    }

    public Integer getBankId() {
        return bankId;
    }

    public void setBankId(Integer bankId) {
        this.bankId = bankId;
    }
}
