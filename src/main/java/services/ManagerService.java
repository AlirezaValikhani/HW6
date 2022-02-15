package services;

import repositories.ManagerRepository;

import java.sql.SQLException;

public class ManagerService {
    private ManagerRepository managerRepository;

    public ManagerService(ManagerRepository managerRepository) {
        this.managerRepository = managerRepository;
    }

    public void createTableManager() throws SQLException {
        managerRepository.creatManagerTable();
    }

    public void insertIntoManager(String firstName,String lastName,String userName,String password,String nationalCode,String phoneNumber,Integer bankId) throws SQLException {
        managerRepository.insertIntoManager(firstName,lastName,userName,password,nationalCode,phoneNumber,bankId);
    }

    public void updateManager(Integer id ,String firstName,String lastName,String userName,String password,String nationalCode,String phoneNumber,Integer bankId) throws SQLException {
        managerRepository.update(id,firstName,lastName,userName,password,nationalCode,phoneNumber,bankId);
    }

    public void deleteManager(Integer id) throws SQLException {
        managerRepository.delete(id);
    }
}
