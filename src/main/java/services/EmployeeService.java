package services;

import model.Employee;
import repositories.EmployeeRepository;

import java.sql.SQLException;

public class EmployeeService {
    private EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public void insertEmployee(Employee employee) throws SQLException {
        employeeRepository.insertIntoEmployee(employee);
    }
}
