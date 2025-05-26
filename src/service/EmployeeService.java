package service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.time.LocalDate;

import model.EmployeeModel;
import repository.EmployeeRepo;
import repository.EmployeeRepo.DuplicateFieldException;
import repository.LeaveRepo;
import utils.Constant;

/*
 *********************************************************************************************************
 *  @Class Name      : EmployeeService
 *  @author          : Raja Kumar (raja.kumar@antrazal.com)
 *  @Company         : Antrazal
 *  @Date            : 26-05-2025
 *  @Description     : Contains all core business logic related to employee management including CRUD,
 *                     hierarchy, skill updates, and salary generation.
 *********************************************************************************************************
 */
public class EmployeeService {

    private final EmployeeRepo repo;
    private final LeaveRepo leaveRepo;

    public EmployeeService(Connection conn) {
        this.repo = new EmployeeRepo(conn);
        this.leaveRepo = new LeaveRepo(conn);
    }

    /*
    *********************************************************
     *  @Method Name    :   createEmployee
     *  @author         :   Raja Kumar (raja.kumar@antrazal.com)
     *  @Company        :   Antrazal
     *  @description    :   Adds a new employee to the system.
     *  @param          :   employee - EmployeeModel
     *  @return         :   void
    ********************************************************
    */
    public void createEmployee(EmployeeModel employee) {
        repo.Add(employee);
    }

    /*
    *********************************************************
     *  @Method Name    :   updateEmployee
     *  @author         :   Raja Kumar (raja.kumar@antrazal.com)
     *  @Company        :   Antrazal
     *  @description    :   Updates employee details with duplicate field validation.
     *  @param          :   id - int, employee - EmployeeModel
     *  @return         :   void
    ********************************************************
    */
    public void updateEmployee(int id, EmployeeModel employee) throws DuplicateFieldException {
        repo.update(id, employee);
    }

    /*
    *********************************************************
     *  @Method Name    :   deleteEmployee
     *  @author         :   Raja Kumar (raja.kumar@antrazal.com)
     *  @Company        :   Antrazal
     *  @description    :   Soft deletes an employee.
     *  @param          :   employeeId - int, companyID - int
     *  @return         :   boolean
    ********************************************************
    */
    public boolean deleteEmployee(int employeeId, int companyID) {
        return repo.delete(employeeId, companyID);
    }

    /*
    *********************************************************
     *  @Method Name    :   getEmployeesByCompany
     *  @author         :   Raja Kumar (raja.kumar@antrazal.com)
     *  @Company        :   Antrazal
     *  @description    :   Retrieves all employees of a company.
     *  @param          :   companyId - int
     *  @return         :   List<EmployeeModel>
    ********************************************************
    */
    public List<EmployeeModel> getEmployeesByCompany(int companyId) {
        return repo.Read(companyId);
    }

    /*
    *********************************************************
     *  @Method Name    :   getHierarchy
     *  @author         :   Raja Kumar (raja.kumar@antrazal.com)
     *  @Company        :   Antrazal
     *  @description    :   Returns the reporting hierarchy of an employee.
     *  @param          :   empId - int, companyId - int
     *  @return         :   List<EmployeeModel>
    ********************************************************
    */
    public List<EmployeeModel> getHierarchy(int empId, int companyId) {
        List<EmployeeModel> hierarchy = new ArrayList<>();
        List<EmployeeModel> allEmployees = repo.getAllEmployeesByCompany(companyId);
        Map<Integer, EmployeeModel> employeeMap = new HashMap<>();
        for (EmployeeModel emp : allEmployees) {
            employeeMap.put(emp.getId(), emp);
        }

        EmployeeModel current = employeeMap.get(empId);
        if (current == null) return hierarchy;
        hierarchy.add(0, current);

        while (current.getManagerId() != null) {
            current = employeeMap.get(current.getManagerId());
            if (current == null) break;
            hierarchy.add(0, current);
        }

        return hierarchy;
    }

    /*
    *********************************************************
     *  @Method Name    :   getEmployeeById
     *  @author         :   Raja Kumar (raja.kumar@antrazal.com)
     *  @Company        :   Antrazal
     *  @description    :   Retrieves employee details by ID and company.
     *  @param          :   empId - int, companyId - int
     *  @return         :   EmployeeModel
    ********************************************************
    */
    public EmployeeModel getEmployeeById(int empId, int companyId) {
        return repo.getById(empId, companyId);
    }

    public EmployeeModel getIdAndComapny(int empId, int companyId) {
        return repo.getById(empId, companyId);
    }

    /*
    *********************************************************
     *  @Method Name    :   isEmailUsedByFiredEmployee
     *  @author         :   Raja Kumar (raja.kumar@antrazal.com)
     *  @Company        :   Antrazal
     *  @description    :   Checks if a fired employee had the given email.
     *  @param          :   email - String, companyId - int
     *  @return         :   boolean
    ********************************************************
    */
    public boolean isEmailUsedByFiredEmployee(String email, int companyId) {
        return repo.isEmailUsedByFiredEmployee(email, companyId);
    }

    public boolean isEmailAlreadyUsed(String email, int companyId) {
        return repo.isEmailAlreadyUsed(email, companyId);
    }

    public EmployeeModel getEmployeeByUsernameAndPassword(String username, String password) {
        return repo.findEmployeeByUsernameAndPassword(username, password);
    }

    public List<EmployeeModel> getManagers(int companyId) {
        return repo.getManagers(companyId);
    }

    /*
    *********************************************************
     *  @Method Name    :   generateSalarySlip
     *  @author         :   Raja Kumar (raja.kumar@antrazal.com)
     *  @Company        :   Antrazal
     *  @description    :   Calculates and saves salary slip based on leave deduction.
     *  @param          :   empId - int, companyID - int
     *  @return         :   String - salary slip content or error message
    ********************************************************
    */
    public String generateSalarySlip(int empId, int companyID) {
        LocalDate currentDate = LocalDate.now();
        int year = currentDate.getYear();
        int month = currentDate.getMonthValue();

        try {
            EmployeeModel emp = repo.getActiveEmployeeById(empId, companyID);
            if (emp == null) {
                return "Employee doesn't exist.";
            }

            double baseSalary = emp.getBaseSalary();
            String name = emp.getFirstName() + " " + emp.getLastName();
            String role = emp.getPosition();

            long leaveDays = leaveRepo.countApprovedLeavesInMonth(empId, year, month);
            int totalDays = YearMonth.of(year, month).lengthOfMonth();
            double perDay = baseSalary / totalDays;
            double deduction = leaveDays * perDay;
            double net = baseSalary - deduction;

            String slip = String.format("""
                    ===== Salary Slip =====
                    Name          : %s
                    Role          : %s
                    Month         : %s
                    Base Salary   : %.2f
                    Leave Days    : %d
                    Deduction     : %.2f
                    Net Salary    : %.2f
                    ========================
                    """, name, role, YearMonth.of(year, month), baseSalary, leaveDays, deduction, net);

            String fileName = "SalarySlip_" + name.replace(" ", "_") + "_" + year + "_" + month + ".txt";
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
                writer.write(slip);
            } catch (IOException e) {
                return "Failed to save salary slip file: " + e.getMessage();
            }

            return "Salary slip generated successfully.\n\n" + slip + "\nSaved to file: " + fileName;

        } catch (SQLException e) {
            throw new RuntimeException("Database error while generating salary slip", e);
        }
    }

    public String getEmployeeSkills(int empId) {
        return repo.getSkillsById(empId);
    }

    public EmployeeModel getActiveEmployeeById(int empId, int companyID) {
        return repo.getActiveEmployeeById(empId, companyID);
    }

    public List<String> getInvalidSkills(List<String> skillsToAdd) {
        List<String> allowed = Constant.SKILLS;
        List<String> invalid = new ArrayList<>();

        for (String skill : skillsToAdd) {
            if (!allowed.contains(skill)) {
                invalid.add(skill);
            }
        }
        return invalid;
    }

    public boolean addSkillsToEmployee(int empId, List<String> skillsToAdd) {
        return repo.addSkills(empId, skillsToAdd);
    }
}
