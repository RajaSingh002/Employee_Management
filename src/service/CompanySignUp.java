package service;

import model.CompanyModel;
import model.EmployeeModel;
import repository.CompanyRepo;

public class CompanySignUp {

    private CompanyRepo companyRepo = new CompanyRepo();
    
    public boolean isCompanyRegistered(String CompanyName){
        return companyRepo.isCompanyExits(CompanyName);
    }

    public void registerCompany(CompanyModel company,EmployeeModel employee) {
        companyRepo.insertCompany(company,employee);
    }

    public boolean isEmailAlreadyExits(String email){
        return companyRepo.isEmailExits(email);

    }

    public boolean isUserAlreadyExits(String Uname){
        return companyRepo.isUserNameExits(Uname);
    }
}
