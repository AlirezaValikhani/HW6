package services;

import model.Customer;
import repositories.CustomerRepository;

import java.sql.SQLException;

public class CustomerService {
    private CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public void insertCustomer(Customer customer) throws SQLException {
        customerRepository.insertIntoCustomer(customer);
    }
}
