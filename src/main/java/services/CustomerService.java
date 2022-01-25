package services;

import model.Customer;
import model.Employee;
import repositories.CustomerRepository;

import java.sql.SQLException;
import java.util.Random;
import java.util.Scanner;

public class CustomerService {
    private CustomerRepository customerRepository;
    Scanner scanner = new Scanner(System.in);

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public void insertCustomer() throws SQLException {
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
        Integer customerId = (int) (Math.random() * (randomIdEnd - randomIdBegin + 1)) +randomIdBegin;
        System.out.print("Your customer id is : " + customerId);
        customerRepository.insertIntoCustomer(new Customer(firstName,lastName,nationalCode,phoneNumber,email,address,customerId));
    }
}
