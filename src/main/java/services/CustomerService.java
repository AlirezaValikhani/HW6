package services;

import model.Customer;
import model.Employee;
import repositories.CustomerRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class CustomerService {
    private CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public void createTableCustomer() throws SQLException {
        customerRepository.creatCustomerTable();
    }

    public Integer insertIntoCustomer(String firstName,String lastName,String userName,String password,String nationalCode,String phoneNumber) throws SQLException {
       return customerRepository.insertIntoCustomer(firstName,lastName,userName,password,nationalCode,phoneNumber);
    }

    public void updateCustomer(Integer id ,String firstName,String lastName,String userName,String password,String nationalCode,String phoneNumber,Integer bankId) throws SQLException {
        customerRepository.update(id,firstName,lastName,userName,password,nationalCode,phoneNumber,bankId);
    }

    public void deleteCustomer(Integer id) throws SQLException {
        customerRepository.delete(id);
    }

    public List<Customer> findAllById(Integer id) throws SQLException {
        return customerRepository.findAll(id);
    }

    public Boolean findById(Integer id) throws SQLException {
        return customerRepository.findById(id);
    }

    public Boolean existsById(Integer id) throws SQLException {
        return customerRepository.existsById(id);
    }

    public Customer existsByUserName(String userName) throws SQLException {
        return customerRepository.findByUserName(userName);
    }

    public Boolean existPassword(String password) throws SQLException {
        return customerRepository.findByPassword(password).equals(password);
    }

    public Customer findEmployeeByUserName(String userName) throws SQLException {
        return customerRepository.findByUserNameAndGetObject(userName);
    }
}
