package service;

import java.sql.SQLException;
import model.CompanyModel;
import model.EmployeeModel;
import repository.CompanyRepo;

/*
 *********************************************************************************************************
 *  @Class Name      : CompanySignUp
 *  @author          : Raja Kumar (raja.kumar@antrazal.com)
 *  @Company         : Antrazal
 *  @Date            : 26-05-2025
 *  @Description     : Handles company registration and validation tasks like checking
 *                     for existing company, email, and username before signup.
 *********************************************************************************************************
 */
public class CompanySignUp {

    private CompanyRepo companyRepo = new CompanyRepo();

    /*
     *********************************************************
     * @Method Name : isCompanyRegistered
     * 
     * @author : Raja Kumar (raja.kumar@antrazal.com)
     * 
     * @Company : Antrazal
     * 
     * @description : Checks if a company is already registered.
     * 
     * @param : CompanyName - String
     * 
     * @return : boolean - true if exists, false otherwise
     ********************************************************
     */
    public boolean isCompanyRegistered(String CompanyName) {
        try {
            return companyRepo.isCompanyExits(CompanyName);
        } catch (SQLException e) {
            throw new RuntimeException("Error checking if company exists", e);
        }
    }

    /*
     *********************************************************
     * @Method Name : registerCompany
     * 
     * @author : Raja Kumar (raja.kumar@antrazal.com)
     * 
     * @Company : Antrazal
     * 
     * @description : Registers a new company along with its primary employee (CEO).
     * 
     * @param : company - CompanyModel, employee - EmployeeModel
     * 
     * @return : void
     ********************************************************
     */
    public void registerCompany(CompanyModel company, EmployeeModel employee) {
        try {
            companyRepo.insertCompany(company, employee);
        } catch (SQLException e) {
            throw new RuntimeException("Error registering company", e);
        }
    }

    /*
     *********************************************************
     * @Method Name : isEmailAlreadyExits
     * 
     * @author : Raja Kumar (raja.kumar@antrazal.com)
     * 
     * @Company : Antrazal
     * 
     * @description : Checks if the given email is already in use.
     * 
     * @param : email - String
     * 
     * @return : boolean - true if exists, false otherwise
     ********************************************************
     */
    public boolean isEmailAlreadyExits(String email) {
        try {
            return companyRepo.isEmailExits(email);
        } catch (SQLException e) {
            throw new RuntimeException("Error checking if email exists", e);
        }
    }

    /*
     *********************************************************
     * @Method Name : isUserAlreadyExits
     * 
     * @author : Raja Kumar (raja.kumar@antrazal.com)
     * 
     * @Company : Antrazal
     * 
     * @description : Checks if the given username is already taken.
     * 
     * @param : Uname - String
     * 
     * @return : boolean - true if exists, false otherwise
     ********************************************************
     */
    public boolean isUserAlreadyExits(String Uname) {
        try {
            return companyRepo.isUserNameExits(Uname);
        } catch (SQLException e) {
            throw new RuntimeException("Error checking if username exists", e);
        }
    }
}
