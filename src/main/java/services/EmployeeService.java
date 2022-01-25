package services;

import model.Employee;
import repositories.EmployeeRepository;

import java.sql.SQLException;
import java.util.Random;
import java.util.Scanner;

public class EmployeeService {
    private EmployeeRepository employeeRepository;
    Scanner scanner = new Scanner(System.in);

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public void insertEmployee() throws SQLException {
        System.out.println("*Welcome to My Bank*");
        System.out.print("Please enter your first name : ");
        String firstName = scanner.next();
        System.out.print("Please enter your last name : ");
        String lastName = scanner.next();
        System.out.print("Please enter your national_code : ");
        String nationalCode = scanner.next();
        System.out.print("Please enter your phone_number : ");
        String phoneNumber = scanner.next();
        System.out.print("Please enter your email : ");
        String email = scanner.next();
        System.out.print("Please enter your address : ");
        String address = scanner.next();
        Random random = new Random();
        int randomIdBegin = 1000;
        int randomIdEnd = 9999;
        Integer employeeId = (int) (Math.random() * (randomIdEnd - randomIdBegin + 1)) +randomIdBegin;
        System.out.print("Your id is : " + employeeId);
        Employee employee = new Employee(firstName,lastName,nationalCode,phoneNumber,email,address,employeeId);
        employeeRepository.insertIntoEmployee(employee);
    }
}
