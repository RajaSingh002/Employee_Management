package service;

import java.sql.SQLException;
import model.CompanyModel;
import model.EmployeeModel;
import repository.CompanyRepo;

public class CompanySignUp {

    private CompanyRepo companyRepo = new CompanyRepo();

    public boolean isCompanyRegistered(String CompanyName) {
        try {
            return companyRepo.isCompanyExits(CompanyName);
        } catch (SQLException e) {
            throw new RuntimeException("Error checking if company exists", e);
        }
    }

    public void registerCompany(CompanyModel company, EmployeeModel employee) {
        try {
            companyRepo.insertCompany(company, employee);
        } catch (SQLException e) {
            throw new RuntimeException("Error registering company", e);
        }
    }

    public boolean isEmailAlreadyExits(String email) {
        try {
            return companyRepo.isEmailExits(email);
        } catch (SQLException e) {
            throw new RuntimeException("Error checking if email exists", e);
        }
    }

    public boolean isUserAlreadyExits(String Uname) {
        try {
            return companyRepo.isUserNameExits(Uname);
        } catch (SQLException e) {
            throw new RuntimeException("Error checking if username exists", e);
        }
    }
}
