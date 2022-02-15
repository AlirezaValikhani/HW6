package services;

import model.Employee;
import repositories.CustomerRepository;
import repositories.EmployeeRepository;

import java.sql.SQLException;
import java.util.Random;
import java.util.Scanner;

public class EmployeeService {
    private EmployeeRepository employeeRepository;
    private CustomerRepository customerRepository;
    Scanner scanner = new Scanner(System.in);

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public void createTableEmployee() throws SQLException {
        employeeRepository.creatEmployeeTable();
    }

    public void insertIntoEmployee(String firstName,String lastName,String userName,String password,String nationalCode,String phoneNumber) throws SQLException {
        employeeRepository.insertIntoEmployee(firstName,lastName,userName,password,nationalCode,phoneNumber);
    }

    public void updateEmployee(Integer id ,String firstName,String lastName,String userName,String password,String nationalCode,String phoneNumber,Integer bankId) throws SQLException {
        employeeRepository.update(id,firstName,lastName,userName,password,nationalCode,phoneNumber,bankId);
    }

    public void deleteEmployee(Integer id) throws SQLException {
        employeeRepository.delete(id);
    }

    public Boolean existUserName(String userName) throws SQLException {
        return employeeRepository.findByUserName(userName).equals(userName);
    }

    public Boolean existPassword(String password) throws SQLException {
        return employeeRepository.findByPassword(password).equals(password);
    }

    public Employee findEmployeeByPassword(String userName) throws SQLException {
        return employeeRepository.findByUserNameAndGetObject(userName);
    }
}
